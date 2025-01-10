package org.example.chat_ai.domain.article.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.example.chat_ai.domain.article.entity.Article;
import org.example.chat_ai.domain.article.entity.QArticle.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static org.example.chat_ai.domain.article.entity.QArticle.article;

public class ArticleRepositoryImpl implements ArticleRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public ArticleRepositoryImpl (JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<Article> search(List<String> kwTypes, String kw, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        if (kwTypes.contains("authorUsername") && kwTypes.contains("title") && kwTypes.contains("body")) {
            builder.and(
                    article.title.containsIgnoreCase(kw)
                            .or(article.body.containsIgnoreCase(kw))
                            .or(article.member.name.containsIgnoreCase(kw))
            );
        }

        JPAQuery<Article> articlesQuery = jpaQueryFactory
                .select(article)
                .from(article)
                .where(builder);

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(article.getType(), article.getMetadata());
            articlesQuery.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(o.getProperty())));
        }

        articlesQuery.offset(pageable.getOffset()).limit(pageable.getPageSize());

        JPAQuery<Long> totalQuery = jpaQueryFactory
                .select(article.count())
                .from(article)
                .where(builder);

        return PageableExecutionUtils.getPage(articlesQuery.fetch(), pageable, totalQuery::fetchOne);
    }
}
