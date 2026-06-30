package duoc.Carrera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CarreraApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarreraApplication.class, args);
	}

}
