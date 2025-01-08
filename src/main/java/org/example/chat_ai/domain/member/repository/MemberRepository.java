package org.example.chat_ai.domain.member.repository;

import org.example.chat_ai.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByName (String author);
}
