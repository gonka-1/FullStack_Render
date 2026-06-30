package duoc.Evaluacion.WebClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class CursoClient {

    private final WebClient webClient;

    // Se conecta usando la URL de configuración para el servicio de cursos
    public CursoClient(@Value("${curso-service.url}") String cursoServidor) {
        this.webClient = WebClient.builder().baseUrl(cursoServidor).build();
    }

    public Map<String, Object> obtenerCursoPorId(Long id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Curso no encontrado")))
                .bodyToMono(Map.class)
                .block();
    }
}