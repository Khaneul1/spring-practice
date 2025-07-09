package hello.hi_servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.hi_servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    //이제 요 아이를 HelloData로 변환시켜 볼 겁니다!!
    //이를 사용하기 위해서는 JSON 라이브러리가 필요함
    //그런데 일단 스프링 부트 기본으로 만들면, JSON 라이브러리를 기본으로 잭슨이라는 라이브러리를 사용함!!
    private ObjectMapper objectMapper= new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody); //Json도 그냥 문자일 뿐이다~!!

        //값을 읽어오기
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        //이렇게 하면 HelloData로 변환이 됨!
        System.out.println("helloData.username = " + helloData.getUsername());
        System.out.println("helloData.age = " + helloData.getAge());

        response.getWriter().write("ok");
    }
}

/**
 * Json 결과를 파싱해서 사용할 수 있는 자바 객체로 변환하려면
 * Jackson, Gson 같은 Json 변환 라이브러리를 추가해서 사용해야 함!!
 * 스프링 부트로 Spring MVC를 선택하면 기본으로 Jackson 라이브러리 ObjectMapper를 함께 제공함
 * 
 * HTML Form 데이터도 메시지 바디를 통해 전송되므로 직접 읽을 수 있다!
 * 하지만 편리한 파라미터 조회 기능 request.getParameter(...)을 이미 제공하기 때문에 파라미터 조회 기능을 사용하면 됨
 */
