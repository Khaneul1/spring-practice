package hello.hi_servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class HiServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiServletApplication.class, args);
	}

}
