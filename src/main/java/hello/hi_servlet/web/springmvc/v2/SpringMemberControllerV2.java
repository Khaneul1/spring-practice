package hello.hi_servlet.web.springmvc.v2;

import hello.hi_servlet.domain.member.Member;
import hello.hi_servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//컨트롤러 통합!!!!!
//하나의 컨트롤러에 작성했던 걸 전부 넣을 수 있음
@Controller
@RequestMapping("/springmvc/v2/members")
//requestmapping을 클래스 레벨에 해 주면!!
//일일이 /springmvc/v2/members/new-form 등으로 안 해도 됨
//알아서 매핑됨!!
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        System.out.println("SpringMemberControllerV2.newForm");
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse
            response) {
        System.out.println("SpringMemberControllerV2.save");
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);
        ModelAndView mav = new ModelAndView("save-result");
        mav.addObject("member", member);
        return mav;
    }

    //더할 게 없기 때문에 /springmvc/v2/members 이대로 호출됨
    @RequestMapping
    public ModelAndView members() {
        System.out.println("SpringMemberControllerV2.members");
        List<Member> members = memberRepository.findAll();
        ModelAndView mav = new ModelAndView("members");
        mav.addObject("members", members);
        return mav;
    }
}

//계속 모델뷰 만들어야 되고 반환해야 되고 넘 귀찮잖아여 ~~