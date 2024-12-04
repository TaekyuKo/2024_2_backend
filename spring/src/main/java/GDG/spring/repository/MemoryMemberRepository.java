package GDG.spring.repository;

import GDG.spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;



public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // Id Setting
        store.put(member.getId(),member); // Store
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // Optional.ofNullable() 이용하여 반환되는 id가 NULL인경우 대비
    }

    @Override
    public Optional<Member> findByName(String name) {
         return store.values().stream() // Execute Loop
                .filter(member -> member.getName().equals(name)) //member.getName이 parameter로 넘어온 Name과 동일한지 확인
                .findAny(); // 같은 경우 아무거나 하나 찾음
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
