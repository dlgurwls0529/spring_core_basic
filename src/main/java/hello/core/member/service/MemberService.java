package hello.core.member.service;

import hello.core.member.domain.Member;

public interface MemberService {
    public void join(Member member);
    public Member findMember(Long id);
}
