package duoc.Asistencia.WebClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class CursoClient {

    private final WebClient webClient;

    public CursoClient(@Value("${curso-service.url}") String cursoServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(cursoServiceUrl)
                .build();
    }

    public Map<String, Object> obtenerCursoPorId(Long id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new RuntimeException("Curso no encontrado"))))
                .bodyToMono(Map.class)
                .block();
    }
}
