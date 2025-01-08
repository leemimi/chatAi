package org.example.chat_ai.domain.article.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.chat_ai.base.RsData;
import org.example.chat_ai.domain.article.entity.Article;
import org.example.chat_ai.domain.article.repository.ArticleRepository;
import org.example.chat_ai.domain.member.entity.Member;
import org.example.chat_ai.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository ;
    private final MemberRepository memberRepository;

    public RsData<Article> write (String title, String content, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        Article save = articleRepository.save(Article.builder()
                .title(title)
                .body(content)
                .member(member)
                .build());

        return RsData.of("200", "게시글 작성 완료", save);
    }
}
