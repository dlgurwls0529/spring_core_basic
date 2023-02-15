package hello.core.member.service;

import hello.core.member.domain.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    // Component 로 빈으로 등록을 하면
    // 생성자로 들어올 의존 관계를 설정할 수가 없다.
    // 그래서 Autowired 를 해줘야 한다.
    // 그러면 빈에서 생성자 입력 타입으로 (타입 스캔)
    // 등록된 Component (빈)을 찾아서 넣어준다.
    // getBean 이랑 규칙이 똑같다.
    // 컴포턴트 애노테이션이 붙은 클래스 = 빈으로 등록된 클래스
    // 중에서 검색하는 타입의 자식을 포함해서 다 긁어온다.
    // 만약 해당되는 자식이 두개거나 하면 오류 뜨겠지?

    // 그리고 컴포넌트 스캔 시 빈 이름은 클래스의 이름 맨 앞을 소문자로 바꿔 사용한다.
    // 이름 부여할려면
    // @Component("memberService") 이러면 된다.

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id);
    }
}
