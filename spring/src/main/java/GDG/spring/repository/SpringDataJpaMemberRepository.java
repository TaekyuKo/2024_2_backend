package GDG.spring.repository;

import GDG.spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>,MemberRepository {
    // Interface만 만들어 놓으면, Spring Jpa가 구현체를 자동으로 만들어서 스프링빈에 등록함.

    @Override
    Optional<Member> findByName(String name);
}
