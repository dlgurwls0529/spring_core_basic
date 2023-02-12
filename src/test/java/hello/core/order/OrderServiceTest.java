package hello.core.order;

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

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = applicationContext.getBean("memberService", MemberService.class);
        orderService = applicationContext.getBean("orderService", OrderService.class);
    }

    @Test
    public void  createOrder() {
        // given
        Long memberId = 1L;
        String itemName = "청진기";
        int itemPrice = 10000;
        int discountPrice = 1000;
        int calculatedPrice = 9000;
        memberService.join(new Member(memberId, "맹제사", Grade.VIP));

        // when
        Order order = orderService.createOrder(memberId, "청진기", 10000);
        int actualCalculatedPrice = order.calculatePrice();

        // then
        org.assertj.core.api.Assertions
                .assertThat(calculatedPrice)
                .isEqualTo(actualCalculatedPrice);
    }
}
