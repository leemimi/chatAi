package org.example.chat_ai.domain.article.controller;

import lombok.AllArgsConstructor;
import org.example.chat_ai.base.RsData;
import org.example.chat_ai.domain.article.entity.Article;
import org.example.chat_ai.domain.article.service.ArticleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/article")
@AllArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/write")
    public RsData<Article> write(String title, String content, Long memberId) {
        RsData<Article> write = articleService.write(title, content, memberId);
        return RsData.of("200", "게시글 작성 완료", write.getData());
    }
}
