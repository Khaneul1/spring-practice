package hello.hi_servlet.web.servlet;

import hello.hi_servlet.domain.member.Member;
import hello.hi_servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name= "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //member list 조회
        List<Member> members = memberRepository.findAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write("<html>");
        w.write("<head>");
        w.write("    <meta charset=\"UTF-8\">");
        w.write("    <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write("    <thead>");
        w.write("    <th>id</th>");
        w.write("    <th>username</th>");
        w.write("    <th>age</th>");
        w.write("    </thead>");
        w.write("    <tbody>");

//        w.write("    <tr>");
//        w.write("        <td>1</td>");
//        w.write("        <td>userA</td>");
//        w.write("        <td>10</td>");
//        w.write("    </tr>");

        //동적으로 돌릴 수 있도록 바꿈
        for (Member member : members) {
            w.write("    <tr>");
            w.write("        <td>" + member.getId() + "</td>");
            w.write("        <td>" + member.getUsername() + "</td>");
            w.write("        <td>" + member.getAge() + "</td>");
            w.write("    </tr>");
        }
        w.write("    </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");



    }
}

/**
 * 현재 코드는 자바 코드에 html을 만들어 넣은 것!!
 * 템플릿 엔진을 사용해서 html에 자바 코드를 넣을 것!!
 * 그렇게 하면 html 작성이 쉬워짐
 *
 * 대표적인 템플릿 엔진 : JSP, Thymeleaf 등
 * 서블릿 덕분에 동적으로 원하는 HTML을 마음껏 만들 수 있음
 * 정적인 HTML 문서라면 화면이 계속 달라지는 회원의 저장 결과나 회원 목록 같은 동적인 HTML을 만드는 일은 불가능
 * 그런데 코드에서 보듯이 매우 복잡하고 비효율적... ㅠㅠ
 * 이 때문에 템플릿 엔진이 나옴!!
 *
 * 템플릿 엔진을 사용하면 html 문서에서 필요한 곳만 코드를 적용해서 동적으로 변경할 수 있음!!
 */
