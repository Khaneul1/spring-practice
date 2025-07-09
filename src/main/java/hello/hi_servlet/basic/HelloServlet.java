package hello.hi_servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "hiServlet", urlPatterns = "/hi")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service"); //sout
        System.out.println("request = " + request); //soutv
        System.out.println("response = " + response);

        // 서블릿이 지원하는 쿼리 파라미터 쉽게 읽는 법
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // 응답 메시지 보내기 HttpServletResponse에 찍어 줘야 함
        // 여기에 값을 넣으면 웹 브라우저에 응답하는 http response 응답 메시지에 데이터가 담겨서 나가게 됨
        response.setContentType("text/plain"); //단순 문자
        response.setCharacterEncoding("utf-8"); //인코딩 정보
        // 위의 2개는 content type인 헤더 정보에 들어감

        response.getWriter().write("hello " + username);
        //getWriter().write()를 사용하면, HTTP 메시지 바디에 데이터가 들어감
    }
}
