package duoc.Seccion.WebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
public class ProfesorClient {

    private final WebClient webClient;

    public ProfesorClient(@Value("${profesor-service.url}") String profesorServidor) {
        this.webClient = WebClient.builder().baseUrl(profesorServidor).build();
    }

    public Map<String, Object> obtenerProfesorPorId(Long id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Profesor no encontrado en el sistema")))
                .bodyToMono(Map.class)
                .block();
    }
}