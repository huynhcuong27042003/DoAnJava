package org.example.dajava;

import org.example.dajava.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DaJavaApplication {
//	@Autowired
//	private MailService mailServices;
//	@EventListener(ApplicationReadyEvent.class)
//	public void sendMail() { // Phương thức void không trả về giá trị
//		mailServices.SendMail("nguyenlequan11042k3@gmail.com", "anh quân đẹp trai", "this is body");
//		System.out.println("Send success");
//	}

	public static void main(String[] args) {
		SpringApplication.run(DaJavaApplication.class, args);
	}


}
