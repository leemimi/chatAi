package org.example.chat_ai.domain.article.repository;

import org.example.chat_ai.domain.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
    List<Article> findByOrderByIdDesc();
}
