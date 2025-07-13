package hello.hi_servlet.web.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//@Controller XX
//예전 버전에서는 핸들러 매핑을 따로 해 줘야 함
@Component("/springmvc/old-controller") //스프링 빈의 이름을 url pattern으로 맞춘 건
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
//        return null;
        return new ModelAndView("new-form");
    }
}

/**
 * 핸들러 매핑(HandlerMapping)에서 이 컨트롤러(OldController)를 찾아와야 함!
 * -> 스프링 빈의 이름으로 핸들러를 찾을 수 있는 특수한 핸들러 매핑 구현체가 필요함
 *
 * 핸들러 매핑을 통해서 찾은 핸들러를 실행할 수 있는 핸들러 어댑터(HandlerAdapter)가 필요함
 * -> Controller 인터페이스(애노테이션 X)를 실행할 수 있는 어댑터를 찾고 실행해야 함
 *
 * 스프링은 이미 필요한 핸들러 매핑과 핸들러 어댑터를 대부분 구현해 둠!
 * 스프링 부트는 자동으로 핸들러 매핑과 어댑터를 여러가지 등록해 준다
 */