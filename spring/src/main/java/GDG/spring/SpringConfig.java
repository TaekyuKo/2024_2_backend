package GDG.spring;


import GDG.spring.aop.TimeTraceAop;
import GDG.spring.repository.JdbcTemplateMemberRepository;
import GDG.spring.repository.JpaMemberRepository;
import GDG.spring.repository.MemberRepository;
import GDG.spring.repository.MemoryMemberRepository;
import GDG.spring.service.MemberService;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration // 일차적으로 Configuration을 읽고 ,@Bean을 보고 스프링 빈에 등록하라는 것으로 받아들임
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean //스프링 빈을 등록할거다! -> MemberService가 스프링 빈에 등록됨.
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
//    @Bean
//    public TimeTraceAop TimeTraceAop() {
//        return new TimeTraceAop();
//    }


//    @Bean // 해당 부분만 바꾸면, 구현체를 바꾸면서 사용할 수 있음.
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

}
