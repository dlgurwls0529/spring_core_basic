package hello.core.member;

import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        Member member = new Member(1L, "맹제이", Grade.NORMAL);
        MemberService memberService = new MemberServiceImpl();
        memberService.join(member);

        System.out.println("member name not inserted yet : " + member.getName());
        System.out.println("member name inserted in repository : " + memberService.findMember(1L).getName());
    }
}
