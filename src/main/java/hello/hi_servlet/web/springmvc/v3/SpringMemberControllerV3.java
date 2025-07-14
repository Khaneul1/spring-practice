package hello.hi_servlet.web.springmvc.v3;

import hello.hi_servlet.domain.member.Member;
import hello.hi_servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

//    @RequestMapping(value = "new-form", method = RequestMethod.GET)
    //이것도 너무 길어서 @GetMapping과 같은 애노테이션을 만듦..
    //효율 지대 따진다 개굿ㅋㅋ
    @GetMapping("/new-form") //요 안에 @RequestMapping이 담겨 있음
    public String newForm() {
        System.out.println("SpringMemberControllerV3.newForm");
        return "new-form";
    }
    //v2에 비해 엄~청 단순해짐!!
    //유연하게 설계되어 있기 때문에 문자로 반환해도 됨

//    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @PostMapping("/save")
    //애노테이션 기반의 컨트롤러가 유연한 이유!!
    //HttpServletRequest/HttpServletResponse뿐만 아니라 파라미터를 직접 받을 수 있음
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {
        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        System.out.println("SpringMemberControllerV3.save");
        return "save-result";
    }

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        System.out.println("SpringMemberControllerV3.members");
        return "members";
    }
}

//단점!! 지금까지의 코드는 get/post 같은 HTTP 메서드를 전혀 신경 쓰지 않고 구분하지 않았음
//HTTP 메서드를 넣어서 구분하는 게 훨씬 나음!
//요런 거 테스트하려면 포스트맨으로 ~~!!