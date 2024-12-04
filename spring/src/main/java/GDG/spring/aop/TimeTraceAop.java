package GDG.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component // 스프링빈으로 등록 해도 되나, SpringConfig.java에 스프링빈에 직접 등록하는 것이 더 확인하기 쉬움.
public class TimeTraceAop {

    @Around("execution(* GDG.spring..*(..))") // @Around -> 공통 관심 사항을 어디에 적용시킬지에 대한 에노테이션,execution() -> GDG.spring 패키지 하위에는 다 적용하라는 뜻.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: "+joinPoint.toString());
        try{
            Object result = joinPoint.proceed(); // 다음메소드로 진행됨.
            return result;
        }finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+joinPoint.toString()+" "+timeMs+"ms");
        }

    }
}
