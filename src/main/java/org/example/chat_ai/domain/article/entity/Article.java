package org.example.chat_ai.domain.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.chat_ai.base.BaseEntity;
import org.example.chat_ai.domain.member.entity.Member;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "article")
public class Article extends BaseEntity {

    private String title;
    private String body;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
