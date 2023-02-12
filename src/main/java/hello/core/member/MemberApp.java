package hello.core.member;

import hello.core.AppConfig;
import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "맹제이", Grade.NORMAL);
        memberService.join(member);

        System.out.println("member name not inserted yet : " + member.getName());
        System.out.println("member name inserted in repository : " + memberService.findMember(1L).getName());
    }
}
