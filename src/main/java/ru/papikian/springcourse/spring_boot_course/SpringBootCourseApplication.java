package ru.papikian.springcourse.spring_boot_course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringBootCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCourseApplication.class, args);
	}

}
