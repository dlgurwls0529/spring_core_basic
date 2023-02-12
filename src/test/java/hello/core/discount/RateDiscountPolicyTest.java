package hello.core.discount;

import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // DisplayName 한글로 이름쓰기
    @Test
    @DisplayName("VIP 는 10% 할인이 적용되어야 한다.")
    public void vip_o() {
        // given
        Member member = new Member(1L, "맹제칠", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // when
        Assertions.assertEquals(1000, discount);
    }

    // 실패 테스트
    @Test
    @DisplayName("VIP 가 아니면 할인을 적용하지 않아야 한다.")
    public void vip_x() {
        // given
        Member member = new Member(1L, "맹제칠", Grade.NORMAL);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        Assertions.assertEquals(0, discount);
    }
}