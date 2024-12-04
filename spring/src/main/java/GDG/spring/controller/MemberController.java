package GDG.spring.controller;

import GDG.spring.domain.Member;
import GDG.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired       //SpringContainer가 memberService를 가져와 연결시켜줌.(dependency Injection; 의존성주입) But, memberService는 SpringContainer안에 없음.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") //데이터를 조회할때 사용
    public String creatForm(){
        return "members/createMemberForm"; // createMemberForm으로 이동(templates\members에 있음.)
    }

    @PostMapping("/members/new") //post 방식으로 넘어오면 (data를 form tag에 넣어서 전달할때, post를 주로 사용)
    public String create(MemberForm form){ //MemberForm 객체 들어옴(MemberForm.java)
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 홈 화면으로 보냄.
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

}
