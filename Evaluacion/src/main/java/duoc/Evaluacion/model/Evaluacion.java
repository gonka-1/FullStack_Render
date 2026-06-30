package duoc.Evaluacion.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "evaluacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluacion")
    private Long idEvaluacion;

    @Column(name = "nombre_evaluacion", nullable = false)
    private String nombreEvaluacion;

    @Column(name = "tipo_evaluacion", nullable = false)
    private String tipoEvaluacion;

    @Column(nullable = false)
    private Double ponderacion;

    @Column(name = "fecha_evaluacion", nullable = false)
    private LocalDate fechaEvaluacion;

    @Column(name = "curso_id", nullable = false)
    private Long cursoId;
}