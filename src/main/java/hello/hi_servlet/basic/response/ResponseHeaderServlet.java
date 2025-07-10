package hello.hi_servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "resonseHeaderServelt", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //응답 코드 세팅 방법
        response.setStatus(HttpServletResponse.SC_OK); //직접 200 넣는 것보다 이렇게 사용하는 게 나음
        //의미 파악 수월!

        //response headers
        response.setHeader("Content-Type", "text/plain;charset-utf-8");
        response.setHeader("Cache-Control", "mo-cache, no-store, must-revalidate"); //캐시 무효화
        response.setHeader("Pragma", "no-cache"); //과거 버전 캐시까지 없앰

        //내가 원하는 임의의 헤더를 만들 수도 있음
        response.setHeader("my-header", "hello");

        //헤더 편의 메서드
//        content(response);
//        cookie(response);
//        redirect(response);

        //메시지 바디
        PrintWriter writer = response.getWriter();
        writer.println("안냐세요"); //println == 엔터 포함! 그래서 content-length가 3이 나오는 것
    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html

        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");

    }
}