package duoc.Asistencia.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "asistencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Asistencia {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private String estadoAsistencia;

    @Column(nullable = false)
    private Long estudianteId;

    @Column(nullable = false)
    private Long CursoId;
}
