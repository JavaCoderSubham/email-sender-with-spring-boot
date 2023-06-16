package com.email.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@SpringBootApplication
public class MainSenderApplication {

	@Autowired
	private JavaMailSender javaMailSender;

	public static void main(String[] args) {
		SpringApplication.run(MainSenderApplication.class, args);
	}

	@GetMapping("/mail/{email}")
	public String mailSender(@PathVariable String email) {
		Random random = new Random();
		int num = 1000 + random.nextInt(9999);
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("noreply@gmail.com");
		simpleMailMessage.setSubject("OTP Generated");
		simpleMailMessage.setText("Your OTP is : "+ num);
		simpleMailMessage.setTo(email);
		javaMailSender.send(simpleMailMessage);
		return "Check Your inbox";
	}

}
