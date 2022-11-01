package me.kekvrose.localplay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LocalPlayApplication {
	@GetMapping("hello")
	public ResponseEntity<String> hello(){
		return new ResponseEntity<String>("hello",HttpStatus.I_AM_A_TEAPOT);
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalPlayApplication.class, args);
	}

}
