package duoc.Matricula;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MatriculaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatriculaApplication.class, args);
	}

}
