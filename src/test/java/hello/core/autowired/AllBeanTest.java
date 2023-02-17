package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {
    // 이런식으로 모든 전략 빈을 검색해 저장해놓고, 동적으로 전략을 전환할 수 있다.
    // 뭔가 이상하지 싶지만, 정책을 빈으로 등록한 상태에서 전환하는 게 쉽지 않다.
    // 1. setter 주입을 사용할 수 있지만 역시 이거는 좀 위험하다
    // 2. 생성자 주입을 쓰면 discount 인자로 ac.getBean 을 하고
    // 두 빈의 이름을 vipPolicy 랑 normalPolicy 이렇게 둬도 되겠다.
    // 하지만 역시 생성의 제어를 위임하지 않았기에 관리가 어려워질 수 있다.
    // (테스트, 제품으로 분리된 빈을 통합하는 경우 getBean 있는 부분 다 바꿔야 함)
    // 3. 모든 빈들 등록해놓고 전환하면, 새로운 정책 생겨도 빈만 등록해도 되고
    // 전환도 용이하고, 실제로 getBean 하는 부분이 없다.
    // 가장 역제어를 잘 활용하여 상태/전략 패턴을 잘 활용할 수 있는 방법인 듯 하다.

    @Test
    public void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DiscountService.class, RateDiscountPolicy.class, FixedDiscountPolicy.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        Assertions.assertInstanceOf(DiscountService.class, discountService);

        int discountPrice = discountService.discount(member, 20000, "fixedDiscountPolicy");
        Assertions.assertEquals(1000, discountPrice);

        discountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        Assertions.assertEquals(2000, discountPrice);
    }

    public static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap) {
            this.policyMap = policyMap;
            System.out.println("policyMap = " + policyMap);
        }

        public int discount(Member member, int i, String policyCode) {
            return policyMap.get(policyCode).discount(member, i);
        }
    }
}
