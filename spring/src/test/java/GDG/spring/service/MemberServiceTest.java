package GDG.spring.service;

import GDG.spring.domain.Member;
import GDG.spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() { // 같은 메모리 멤버 리포지토리 사용을 위한 메소드
        memberRepository = new MemoryMemberRepository(); //MemoryMemberRepository 생성
        memberService = new MemberService(memberRepository); // memberSevice.java에 MemberService메소드에 데이터 기입
    }

    @AfterEach
    public void afterEach(){ // 메소드를 돌때마다 DB의 값을 clear
        memberRepository.clearStore();
    }

    @Test
    @Commit
    void 회원가입() { // 리포지토리에서 추출한 데이터와 저장한 데이터가 동일한지 확인
        //given -> 해당 데이터 기반으로
        Member member = new Member();
        member.setName("spring");

        //when -> 이러한 상황이 온다면
        Long saveId = memberService.join(member);

        //then -> 검증부
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){ //예외플로우가 작동되는 것을 검증하기 위한 메소드
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 예외가 터짐.

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


//        try{
//            memberService.join(member2); //exception이 터지면 catch부분으로 이동
//            fail();  //해당 부분으로 오면 exception이 작동되지 않은 것.(구현실패)
//        }catch(IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}