package mk.finki.ukim.mk.lab203078;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Lab203078Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab203078Application.class, args);
    }

}
