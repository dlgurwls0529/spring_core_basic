package hello.core.discount;

import hello.core.member.domain.Member;

public interface DiscountPolicy {
    // 회원 등급 안받는 이유는 확장성을 고려해서 그런 것
    // 나중에 다른 정보가 또 필요할 수 있으니까
    // 또 itemPrice 가 Integer 가 아닌 이유는 db에 갔다오는 필드가 아니다보니
    // 굳이 null 넣을 필요가 없나보다. 애초에 계산 로직이니까 null 이 들어오는 건 말이 안됨
    public int discount(Member member, int itemPrice);
}
