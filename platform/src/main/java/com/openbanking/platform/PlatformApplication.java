package com.openbanking.platform;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.openbanking.platform.mail.JavaMailSender;
import com.openbanking.platform.service.AuthService;
import com.openbanking.platform.storage.CloudinaryClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class PlatformApplication {

	private final JavaMailSender javaMailSender;
	private final CloudinaryClient cloudinaryClient;
	private final AuthService authService;

	public static void main(String[] args) {
		SpringApplication.run(PlatformApplication.class, args);
	}
	@PostConstruct()
	private void test() throws MessagingException, IOException {
		log.info("Video uplading is started");
		String uploaded = cloudinaryClient.uploadVideo();
		log.info("Video is uploaded ");
		authService.saveRecord(uploaded);
		log.info("Data is saved ");
		log.info("Mail sending is started");
		javaMailSender.sendmail("shamistan.huseynov@gmail.com","test", "it is for testing");
		log.info("Mail is sent");
	}
}
