package org.example.chat_ai.domain.member.controller;

import lombok.AllArgsConstructor;
import org.example.chat_ai.global.base.RsData;
import org.example.chat_ai.domain.member.entity.Member;
import org.example.chat_ai.domain.member.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;
    @PostMapping("/join")
    public RsData<Member> join(String username, String password) {
        return RsData.of("200", "회원가입 완료", memberService.join(username, password).getData());
    }

}
