package org.example.chat_ai.domain.member.service;

import jakarta.transaction.Transactional;
import org.example.chat_ai.base.RsData;
import org.example.chat_ai.domain.article.entity.Article;
import org.example.chat_ai.domain.article.entity.ArticleComment;
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
@ActiveProfiles({"test","dev"})
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
        RsData<Article> writeRs = articleService.write(2L, "제목", "내용");
        Article article = writeRs.getData();

        assertThat(article.getId()).isGreaterThan(0L);
    }

    @DisplayName("1번 글을 가져온다.")
    @Test
    void t22() {
        Article article = articleService.findById(1L).get();
        assertThat(article.getTitle()).isEqualTo("제목1");
    }

    @DisplayName("1번 글의 작성자의 username 은 user1 이다.")
    @Test
    void t3() {
        Article article = articleService.findById(1L).get();
        Member author = article.getMember();

        assertThat(author.getName()).isEqualTo("user1");
    }
    @DisplayName("1번 글의 제목을 수정한다.")
    @Test
    void t4() {
        Article article = articleService.findById(1L).get();

        articleService.modify(article, "수정된 제목", "수정된 내용");

        Article article_ = articleService.findById(1L).get();

        assertThat(article_.getTitle()).isEqualTo("수정된 제목");
    }

    @DisplayName("1번 글의 댓글들을 수정한다.")
    @Test
    void t6() {
        Article article = articleService.findById(2L).get();

        article.getComments().forEach(comment -> {
            articleService.modifyComment(comment, comment.getBody() + "!!");
        });
    }

    @DisplayName("1번 글의 댓글 중 마지막 것을 삭제한다.")
    @Test
    void t7() {
        Article article = articleService.findById(2L).get();

        ArticleComment lastComment = article.getComments().getLast();

        article.removeComment(lastComment);
    }

}
