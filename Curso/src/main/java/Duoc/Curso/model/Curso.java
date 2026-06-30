package Duoc.Curso.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "curso")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "profesor_id", nullable = false)
    private Long profesorId;

    @Column(name = "carera_id", nullable = false)
    private Long CarreraId;
}
