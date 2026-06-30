package duoc.Seccion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "secciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seccion")
    private Long idSeccion;

    @Column(name = "codigo_seccion", nullable = false, unique = true)
    private String codigoSeccion;

    @Column(nullable = false)
    private String semestre;

    @Column(name = "anio", nullable = false)
    private Integer anio;

    @Column(name = "cupo_maximo", nullable = false)
    private Integer cupoMaximo;

    // Relaciones lógicas con otros microservicios (Guardamos solo el ID)
    @Column(name = "curso_id", nullable = false)
    private Long cursoId;

    @Column(name = "profesor_id", nullable = false)
    private Long profesorId;
}