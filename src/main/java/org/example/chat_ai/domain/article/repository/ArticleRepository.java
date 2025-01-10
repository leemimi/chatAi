package org.example.chat_ai.domain.article.repository;

import org.example.chat_ai.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
