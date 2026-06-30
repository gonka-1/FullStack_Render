package Duoc.Nota.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
public class MatriculaClient {

    private final WebClient webClient;

    public MatriculaClient(@Value("${ms-matriculas.url}") String matriculaServidor) {
        this.webClient = WebClient.builder().baseUrl(matriculaServidor).build();
    }

    public Map<String, Object> obtenerMatriculaPorId(Long id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Matricula no encontrada")))
                .bodyToMono(Map.class)
                .block();
    }
}