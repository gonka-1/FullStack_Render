package Duoc.Curso.WebClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class CarreraClient {

    private final WebClient webClient;

    public CarreraClient(@Value("${carrera-service.url}") String profesorServidor) {
        this.webClient = WebClient.builder().baseUrl(profesorServidor).build();
    }

    public Map<String, Object> obtenerCarreraPorId(Long id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Profesor no encontrado")))
                .bodyToMono(Map.class)
                .block();
    }
}
