package org.example.chat_ai.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.chat_ai.base.RsData;
import org.example.chat_ai.domain.member.entity.Member;
import org.example.chat_ai.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;


    public RsData<Member> join (String username, String password) {
        Member member = memberRepository.save(Member.builder()
                .name(username)
                .password(password)
                .build());
        return RsData.of("200", "회원가입 완료", member);
    }
}
