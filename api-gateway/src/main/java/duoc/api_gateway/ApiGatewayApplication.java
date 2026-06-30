package duoc.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.path;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouterFunction<ServerResponse> gatewayRoutes() {
		return route("ruta-estudiante")
				.route(path("/api/v1/estudiantes/**"), http())
				.filter(lb("ESTUDIANTE"))
				.build()

				.and(route("ruta-profesor")
						.route(path("/api/v1/profesores/**"), http())
						.filter(lb("PROFESOR"))
						.build())

				.and(route("ruta-carrera")
						.route(path("/api/v1/carreras/**"), http())
						.filter(lb("CARRERA"))
						.build())

				.and(route("ruta-asistencia")
						.route(path("/api/v1/asistencias/**"), http())
						.filter(lb("MS-ASISTENCIAS"))
						.build())

				.and(route("ruta-curso")
						.route(path("/api/v1/cursos/**"), http())
						.filter(lb("MS-CURSOS"))
						.build())

				.and(route("ruta-evaluacion")
						.route(path("/api/v1/evaluaciones/**"), http())
						.filter(lb("MS-EVALUACIONES"))
						.build())

				.and(route("ruta-matricula")
						.route(path("/api/v1/matriculas/**"), http())
						.filter(lb("MS-MATRICULAS"))
						.build())

				.and(route("ruta-nota")
						.route(path("/api/v1/notas/**"), http())
						.filter(lb("MS-NOTAS"))
						.build())

				.and(route("ruta-sala")
						.route(path("/api/v1/salas/**"), http())
						.filter(lb("MS-SALA"))
						.build())

				.and(route("ruta-seccion")
						.route(path("/api/v1/secciones/**"), http())
						.filter(lb("MS-SECCION"))
						.build());
	}
}