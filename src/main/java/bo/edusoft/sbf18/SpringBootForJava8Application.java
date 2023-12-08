package bo.edusoft.sbf18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//@EntityScan(basePackages = {"bo.edusoft.sbf18"})
//@ComponentScan(basePackages = {"bo.edusoft.sbf18"})
@SpringBootApplication
@EnableSwagger2
public class SpringBootForJava8Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootForJava8Application.class, args);
	}
}
