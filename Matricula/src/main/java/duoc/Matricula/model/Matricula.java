package duoc.Matricula.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matricula")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long estudianteId;

    @Column(nullable = false)
    private Long cursoId;

    @Column(nullable = false)
    private String calificacion;
}
