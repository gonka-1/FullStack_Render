package Duoc.Nota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotaApplication.class, args);
	}
}