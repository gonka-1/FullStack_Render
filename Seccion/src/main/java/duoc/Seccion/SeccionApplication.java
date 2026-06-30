package duoc.Seccion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SeccionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeccionApplication.class, args);
	}

}
