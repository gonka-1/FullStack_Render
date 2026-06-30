package duoc.Evaluacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EvaluacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluacionApplication.class, args);
	}

}
