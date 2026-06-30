package duoc.Sala.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "salas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private Long idSala;

    @Column(name = "nombre_sala", nullable = false, unique = true)
    private String nombreSala;

    @Column(nullable = false)
    private Integer capacidad;

    @Column(nullable = false)
    private String ubicacion; // Ej: "Sede San Carlos, Piso 4"

    @Column(name = "tipo_sala", nullable = false)
    private String tipoSala; // Laboratorio, Aula, Taller

    @Column(name = "estado_sala", nullable = false)
    private String estadoSala; // Disponible, Ocupada
}