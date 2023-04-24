package com.claudiocarige.CntrMovimentApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.claudiocarige.CntrMovimentApi"})
public class CntrMovimentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CntrMovimentApiApplication.class, args);
	}

}
