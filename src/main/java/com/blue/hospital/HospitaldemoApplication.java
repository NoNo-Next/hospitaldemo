package com.blue.hospital;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.blue.hospital.mapper")
public class HospitaldemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitaldemoApplication.class, args);
    }

}
