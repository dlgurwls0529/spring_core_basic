package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // 모든 객체의 주입은 이 클래스에서 담당하고, 의존 관계 역시 이 클래스에서 결정한다.
    // 즉, 모든 어플리케이션 작동?을 여기에서 전체적으로 볼 수 있다.
    // 왜 작동이 다 여기서 볼 수 있냐면
    // 모든 책임들의 조합들이 여기로 넘겨져서 수행되기 때문이다.

    // IOC 란 제어를 메소드 혹은 동작이 쓰여진 클래스가 아니라 다른 클래스를 통해서 wrapping 되어 수행되는 것을 말한다.
    // 가령 템플릿 콜백 패턴이 그러하다
    // 콜백을 Callback 인터페이스의 구현체가 구현하나, 그것을 감싸는 Template 에서 그것을 호출(제어) 한다.
    // (물론 callbackImpl.call() 이럴 수도 있지만, 제어가 꼭 다른 곳에서 되는 것이 아니라 그럴 수도 있다 그런 말이다.)
    // (아무튼 핵심은 다른 곳에서 감싸서 호출하게 되면, 특정 관심사가 감싸는 클래스(Template)에 응집되니까 변경이 쉬워진다.)
    // (그래서 직접 호출보다는 래핑 클래스를 통해 호출하는 IOC 방식을 통해 동작하는 것이 좋다.)
    // (다른 한편으로는 캡슐화를 유지하는 방법이라 볼 수 있겠다. 마치 getter setter 처럼 접근을 캡슐화하는 느낌이다.)
    // DI 도 마찬가지이다.
    // 특정 클래스에서 쓰여진 new A(); 이런 생성 작업을
    // 이런거를 A.getInstance() 이라고 보자.(아니어도 설명이 되긴 한다.)
    // 아무튼 이 메소드를 A.getInstance()가 아니라 래핑클래스.a()로 호출하는 것이고,
    // 제어권을 역전하여 호출하는 것이다. 생각해보면 기존 메소드를 제한자로 잠그는 것도 좋은 생각인 듯 하다.
    // 꼭 그렇지 않더라도 new A()나 new A(new B)이런 생성 작업을 다른 클래스에서 감싸서 호출(다른 클래스에서 메소드를 제어)
    // 하면 DI의 장점처럼 적절한 캡슐화가 전제되었을 때 높은 응집도의 장점을 가질 수 있다.
    // 정리하면, IOC 는 래핑(캡슐화, 위임)을 통해 메소드들의 제어권을 넘겨서 캡슐화의 장점을 얻는 것이고, DI는 그 중
    // 객체의 생성과 의존 관계와 관련된 메소드(작업?)을 Injector 가 감싸서 호출(=Injector 에 넘겨서 대신 호출=Injector 를 거쳐서 호출)
    // https://hudi.blog/inversion-of-control/

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
