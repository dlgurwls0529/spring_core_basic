package hello.core.member.repository;

import hello.core.member.domain.Member;

public interface MemberRepository {
    public void save(Member member);
    // Long 쓰는 이유는 null 이 들어와도 잘 동작하기 때문.
    // 그래서 Dto 에는 Integer 이나 Long 같은 참조 타입을 쓰는데
    // 박싱 언박싱에 의한 속도 저하나 비교를 따로 해줘야 해서 번거롭기에,
    // Dto 같이 null 들어오는 게 아니면 원시 타입을 쓰자.
    public Member findById(Long id);
}
