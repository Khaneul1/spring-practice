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

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");

        //값을 가지고 와서 읽어야 함
        //url 쿼리 스트링이든 html 폼 데이터든 Form Data를 읽어야 함!
        
        //form에서 온 값 getParameter로 꺼내오기
        String username = request.getParameter("username");
        //request.getParameter()의 값은 항상 문자임
        //그렇기 때문에 age는 숫자 타입으로 변환해 줘야 함!!
        int age = Integer.parseInt(request.getParameter("age"));

        //비즈니스 로직
        Member member = new Member(username, age);
        memberRepository.save(member);

        //저장이 잘 됐는지 확인!!
        //결과를 html form으로 응답할 것
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "    <li>id="+member.getId()+"</li>\n" +
                "    <li>username="+member.getUsername()+"</li>\n" +
                "    <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
    //id는 멤버리파지토리에서 save 할 때 id 값 넣어 준 것을 받아옴
}

/**
 * 정적인 html 같은 경우, 프로그램 파일이기 때문에 중간에 동적인 코드 삽입 불가
 * 현재 응답으로 보내는 html form 코드의 경우, 동적으로 코드 삽입 가능!!
 */