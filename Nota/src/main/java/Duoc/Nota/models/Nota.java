package Duoc.Nota.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nota")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "matricula_id", nullable = false)
    private Long matriculaId;

    @Column(nullable = false)
    private Double calificacion;


}
