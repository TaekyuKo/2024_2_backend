package GDG.spring.service;

import GDG.spring.domain.Member;
import GDG.spring.repository.MemberRepository;
import GDG.spring.repository.MemoryMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { // 외부에서 넣어주도록 설정
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    @Transactional //Jpa를 쓰기위해 항상 필요한 에노테이션
    public Long join(Member member) {
        //같은 이름의 중복회원 대비
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member); // 검증 후 통과시 저장
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // Optional 안에 Member 객체 존재
            .ifPresent(m ->{                          //result가 NULL이 아니면 동작
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
            return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
