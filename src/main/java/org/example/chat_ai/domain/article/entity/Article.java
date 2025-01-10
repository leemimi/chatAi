package org.example.chat_ai.domain.article.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.chat_ai.base.BaseEntity;
import org.example.chat_ai.domain.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter
@Table(name = "article")
public class Article extends BaseEntity {

    private String title;
    private String body;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    private List<ArticleComment> comments = new ArrayList<>();

    public void addComment(Member memberAuthor, String commentBody) {
        ArticleComment articleComment = ArticleComment.builder()
                .article(this)
                .author(memberAuthor)
                .body(commentBody)
                .build();
        comments.add(articleComment);
    }

    public void removeComment(ArticleComment comment) {
        comments.remove(comment);
    }
}
