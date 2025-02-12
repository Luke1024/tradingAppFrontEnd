package frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class VaadinApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(VaadinApplication.class, args);
    }
/*
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(VaadinApplication.class);
    }
 */
}
