package org.example.chat_ai.domain.article.service;


import lombok.AllArgsConstructor;
import org.example.chat_ai.global.base.RsData;
import org.example.chat_ai.domain.article.entity.Article;
import org.example.chat_ai.domain.article.entity.ArticleComment;
import org.example.chat_ai.domain.article.repository.ArticleRepository;
import org.example.chat_ai.domain.member.entity.Member;
import org.example.chat_ai.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository ;
    private final MemberRepository memberRepository;
    @Transactional
    public RsData<Article> write (Long memberId, String title, String content ) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        Article save = articleRepository.save(Article.builder()
                        .title(title)
                        .member(Member.builder().id(memberId).build())
                        .body(content)
                        .member(member)
                        .build());

        return RsData.of("200", "게시글 작성 완료", save);
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    @Transactional
    public void modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setBody(content);

        articleRepository.save(article);
    }
    @Transactional
    public void modifyComment(ArticleComment comment, String body) {
        comment.setBody(body);
    }



}
