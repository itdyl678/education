package com.itflyket.education;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.context.annotation.ComponentScan;
=======
>>>>>>> 18415b2107057a21876fda6d6c1e3377026d1b61

@SpringBootApplication
@MapperScan("com.itflyket.education.mapper")
public class EducationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationApplication.class, args);
    }

}
