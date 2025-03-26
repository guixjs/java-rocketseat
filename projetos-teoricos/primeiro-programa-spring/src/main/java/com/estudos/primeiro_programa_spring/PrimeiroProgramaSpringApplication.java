package com.estudos.primeiro_programa_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.estudos")
public class PrimeiroProgramaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimeiroProgramaSpringApplication.class, args);
	}

}
