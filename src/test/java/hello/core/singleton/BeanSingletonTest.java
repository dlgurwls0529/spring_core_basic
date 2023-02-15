package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanSingletonTest {
    private final ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // 1. 싱글턴은 private 생성자 때문에 상속을 못하게 한다. => 다형성 못씀
    // 2. 상태가 공유되어 (글로벌 상태) 불안정한 시스템
    // 3. 모든 곳에 다 싱글턴 코드 쳐야 함

    // 스프링은 어차피 객체 생성을 제어해주니까, 하는 김에 싱글턴도 하는 것
    // 역제어 덕분에 싱글턴도 직접 안쳐도 된다.

    // 스프링을 쓰더라도 1번 문제는 그냥 new 해서 객체 가져오면 싱글턴 깨뜨릴 수 있는 거 아닌가?
    // 생각할 수 있다. 하지만 그렇지 않다. 가령 레포지토리를 생각해보자.
    // 얘가 다른 서비스에서 필드로 new 를 통해 생성하려 한다고 해보자.
    // 기존에 올바르게 DI를 사용했다면, 서비스의 필드인 레포지토리를 생성자로 개방하도록 Config 에 설정되어있을 것이다.
    // 만약 이 상태에서 서비스에서 필드인 레포지토리를 new 로 초기화한다면, (생성자가 아니라)

    @Test
    @DisplayName("싱글턴 빈의 생성자를 접근하면?")
    public void accessSingletonDeCapsuled() {
        MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
        MemberService memberService1 = new MemberServiceImpl(new MemoryMemberRepository());

        Assertions.assertEquals(memberService, memberService1);
    }

}
