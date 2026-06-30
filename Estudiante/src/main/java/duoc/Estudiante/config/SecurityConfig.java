package duoc.Estudiante.config;

import duoc.Estudiante.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Este filtro revisará el token antes de que la petición llegue al controller.
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Para esta clase se deshabilita CSRF porque estamos probando una API REST.
                .csrf(csrf -> csrf.disable())

                // Stateless significa que el servidor NO guardará sesión.
                // Toda la identidad viajará dentro del token.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Reglas de acceso.
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas de tu API
                        .requestMatchers("/auth/**", "/api/publico/**").permitAll()

                        // LISTA BLANCA DEFINITIVA PARA SWAGGER UI
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/error"
                        ).permitAll()

                        // Todo lo demás bloqueado sin token
                        .anyRequest().authenticated()
                )

                // Antes de llegar al controller, Spring ejecuta nuestro filtro JWT.
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
