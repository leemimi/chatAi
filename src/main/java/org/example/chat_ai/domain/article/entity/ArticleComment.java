package org.example.chat_ai.domain.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.chat_ai.base.BaseEntity;
import org.example.chat_ai.domain.member.entity.Member;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class ArticleComment extends BaseEntity {
    @ManyToOne
    private Article article;

    @ManyToOne
    private Member author;

    private String body;
}
