package com.machine.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RedisMain {
	public static void main(String[] args){
		SpringApplication.run(RedisMain.class, args);
	}
}