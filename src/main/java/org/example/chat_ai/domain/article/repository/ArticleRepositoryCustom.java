package org.example.chat_ai.domain.article.repository;

import org.example.chat_ai.domain.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleRepositoryCustom {
    Page<Article> search(List<String> kwTypes, String kw, Pageable pageable);
}
