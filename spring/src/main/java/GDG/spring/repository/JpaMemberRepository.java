package GDG.spring.repository;

import GDG.spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; //Jpa는 EntityManager(Jpa 라이브러리 추가시, 스프링부트가 자동으로 생성해줌.)로 모든 것이 동작됨.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) { // Jpa가 자동으로 Insert 쿼리를 생성하며 save 수행
        em.persist(member); //persist -> 영구 저장한다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member); //return 값이 없을 수도 있으므로, .ofNullable 사용
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member m", Member.class) // JPQL(객체지향 쿼리 언어) 사용
                .getResultList();
        return result;
    }
}
