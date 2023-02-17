package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.member.domain.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

// required 는 필요한 인자, 즉 final 키워드를 전부 생성자로 개방해준다.
// 롬복 쓰는 이유는 아마 POJO 때문인 듯 하다.
// 이렇게 하고 필요할 때 생성자 만들면 될 듯
// 이거 막 필드 추가할 때 편하다.
// 필드 추가하고 생성자 안건드려도 되기 때문
// 코드 깔끔, final 생성자 주입 쓸 수 있음. 매우 많이 쓴다고 함.
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // 할인되는 돈 계산
        int discountAmount = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountAmount);
    }
}
