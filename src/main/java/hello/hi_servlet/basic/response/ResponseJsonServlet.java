package hello.hi_servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.hi_servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name= "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Content-Type: application/json으로 들어가야 함!! http 응답 메시지로 json을 보내는 거니까
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("ko");
        helloData.setAge(24);

        //위의 코드 형태를 json으로 바꿔야 함!!
        //{"username":"ko", "age":24}
        String result = objectMapper.writeValueAsString(helloData);
        //객체를 가지고 값을 써서 문자로 바꿔라! writeValueAsString()

        response.getWriter().write(result);

    }
}

/**
 * application/json은 스펙상 utf-8 형식을 사용하도록 정의되어 있음!
 * 이 때문에 스펙에서 charset=utf-8과 같은 추가 파라미터를 지원하지 않는 것
 * 따라서, application/json;charset=utf-8이라고 전달하는 것은 의미 없는 파라미터를 추가한 꼴
 * response.getWriter()를 사용하면 추가 파라미터를 자동으로 추가해버림!!
 * 이때는 response.getOutputStrea()으로 출력하면 그런 문제가 없당
 */
