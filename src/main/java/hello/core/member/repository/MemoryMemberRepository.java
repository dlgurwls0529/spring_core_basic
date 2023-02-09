package hello.core.member.repository;

import hello.core.member.domain.Member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {
    private static final Map<Long, Member> map = new HashMap<>();

    @Override
    public void save(Member member) {
        map.put(member.getId(), member);
    }

    @Override
    public Member findById(Long id) {
        return map.get(id);
    }
}
