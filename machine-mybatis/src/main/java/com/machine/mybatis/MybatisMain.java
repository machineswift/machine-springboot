package com.machine.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MybatisMain {
	public static void main(String[] args){
		SpringApplication.run(MybatisMain.class, args);
	}
}