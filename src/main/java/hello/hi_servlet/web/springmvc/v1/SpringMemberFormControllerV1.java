package hello.hi_servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Controller
 * 스프링이 자동으로 스프링 빈 등록
 * >> @Component가 있으면 @ComponentScan의 대상이 돼서 자동으로 스프링 빈이 등록됐었음
 * >> @Controller에 들어가 보면 이 @Component 애노테이션이 붙어 있음!
 * >> 그래서 스프링이 인식을 하고 ComponentScan의 대상이 되어서 자동으로 스프링 빈이 등록되는 것
 * 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식
 * >> RequestMappingHandlerMapping에서 핸들러 정보로 인지하고 꺼낼 수 있는 대상이 되는 것
 *
 * 따라서 @Controller가 하는 일
 * 1) 컴포넌트 스캔의 대상이 됨
 * 2) 리퀘스트 매핑, 핸들러 매핑
 */
@Controller
public class SpringMemberFormControllerV1 {

    //requestMapping : 요청 정보 매핑
    //해당 url이 호출되면 이 메서드 호출
    //애노테이션 기반으로 동작하기 때문에 메서드의 이름은 임의로 지으면 됨!!
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        //모델과 뷰 정보 담아서 반환
        return new ModelAndView("new-form"); //view name을 넘김
    }
}

//RequestMappingHandlerMapping은 스프링 빈 중에서
//@RequestMapping 또는 @Controller가 클래스 레벨에 붙어 있는 경우에 매핑 정보로 인식함!!
//아래의 코드로도 동작 가능
/**
@Component //컴포넌트 스캔의 대상이 됨 (스프링 빈 자동 등록)
@RequestMapping
public class SpringMemberFormControllerV1 {
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
*/