package hello.core.discount;

import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;

public class FixedDiscountPolicy implements DiscountPolicy {
    private final int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int itemPrice) {
        // enum 은 equals 말고 == 쓰면 된단다.
        if(member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }
        else {
            return 0;
        }
    }
}
