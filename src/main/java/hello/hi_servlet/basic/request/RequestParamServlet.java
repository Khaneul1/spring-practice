package hello.hi_servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 * 
 */

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("RequestParamServlet.service");
        //먼저 잘 동작하는지 위의 코드로 확인해 보기!

        //전체 파라미터 조회하는 법 (Request parameter 꺼내는 법!)
        System.out.println("[전체 파라미터 조회] - start");

        //Enumeration<String> parameterNames = request.getParameterNames();
        //위의 코드를 요즘 방식으로! asIterator() 사용!!
        //paramName은 key값(username, age)이 되고, request.getParameter(key)는 value값(hello, 20)이 됨
        request.getParameterNames().asIterator()
                        .forEachRemaining(paramName -> System.out.println(paramName + "= " + request.getParameter(paramName)));

        System.out.println("[전체 파라미터 조회] - end");

        System.out.println("[단일 파라미터 조회] - start");
        
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();

        //http://localhost:8080/request-param?username=hello&age=20&username=hello2
        //이런 식으로 이미 username값이 있는데, & 연산자 사용해서 또 username 값 넣어도 가능!
        //이렇게 할 경우, 내부 우선 순위에서 먼저 잡히는 값이 나오게 됨
        //이렇게 이름이 같은 파라미터를 조회하는 방법은
        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = request.getParameterValues("username");
        //iter + enter 사용하여 아래의 코드 뽑아내기
        for (String name : usernames) {
            System.out.println("username = " + name);
            
        }

        response.getWriter().write("ok");
    }
}

/**
 * [복수 파라미터에서 단일 파라미터 조회]
 * username=hello&username=kim과 같이 파라미터 이름은 하나인데, 값이 중복이면 어떻게 될까?
 * request.getParamter()는 하나의 파라미터 이름에 대해서 단 하나의 값만 있을 때 사용해야 함
 * 지금처럼 중복일 때는 request.getParameterValues()를 사용해야 함!!
 *
 * 참고로, 이렇게 중복일 때 request.getParameter()를 사용하면,
 * request.getParameterValues()의 첫 번쩨 값을 반환한다.
 */