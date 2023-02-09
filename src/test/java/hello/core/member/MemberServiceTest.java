package hello.core.member;

import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given 이런이런게 주어졌을 때
        Member member = new Member(1L, "맹제이", Grade.NORMAL);

        // when 이렇게 했을 때
        memberService.join(member);

        // then 이렇게 된다.
        Member foundMember = memberService.findMember(1L);
        Assertions.assertEquals(member, foundMember);
    }
}
