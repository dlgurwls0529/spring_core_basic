package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // inject by Injector
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "맹제삼", Grade.VIP);
        memberService.join(member);

        // 맹제삼이 상품 하나를 산다.
        Order order = orderService.createOrder(memberId, "청진기", 10000);

        System.out.println(order.toString());
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
