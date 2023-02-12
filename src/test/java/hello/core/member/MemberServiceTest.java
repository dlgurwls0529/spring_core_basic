package hello.core.member;

import hello.core.AppConfig;
import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberServiceTest {
    MemberService memberService;

    // 테스트 쓰는 이유는 의존 관계에 의한 변경의 전파 때문인 듯 하다
    // 하나 고치면 여러 곳이 바뀌나
    // 테스트 다 돌려보면 어디가 잘못되었는지 딱 바로 알 수 있기 때문이다.
    // 테스트 없으면 컴파일 타입에 잡히지 않지만, 의도에 따라 동작하지 않는 부분을 찾기 어렵다.

    @BeforeEach
    public void beforeEach() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = applicationContext.getBean("memberService", MemberService.class);
    }

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
