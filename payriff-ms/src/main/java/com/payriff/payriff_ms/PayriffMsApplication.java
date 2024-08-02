package com.payriff.payriff_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PayriffMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayriffMsApplication.class, args);
	}

}
