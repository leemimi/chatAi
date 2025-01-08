package org.example.chat_ai.domain.member.service;

import jakarta.transaction.Transactional;
import org.example.chat_ai.base.RsData;
import org.example.chat_ai.domain.article.entity.Article;
import org.example.chat_ai.domain.article.service.ArticleService;
import org.example.chat_ai.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@ExtendWith(SpringExtension.class) //스프링 컨텍스트 통합
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private ArticleService articleService;

    @Test
    @DisplayName("회원가입 테스트")
    void t1() {
        RsData<Member> joinRs = memberService.join("newUser", "1234");
        Member member = joinRs.getData();
        assertThat(member.getId()).isGreaterThan(0L);
    }


    @Test
    @DisplayName("글 쓰기")
    void t2() {
        RsData<Article> writeRs = articleService.write("제목", "내용", 2L);
        Article article = writeRs.getData();

        assertThat(article.getId()).isGreaterThan(0L);
    }
}
