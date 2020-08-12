package com.lilili957;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@MapperScan(value={"com.lilili957.mapper"})
public class BlogserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogserverApplication.class, args);
	}

}
