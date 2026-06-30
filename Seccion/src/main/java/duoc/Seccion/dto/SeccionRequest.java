package duoc.Seccion.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SeccionRequest {

    @NotBlank(message = "El código de sección es obligatorio")
    private String codigoSeccion;

    @NotBlank(message = "El semestre es obligatorio")
    private String semestre;

    @NotNull(message = "El año es obligatorio")
    @Min(value = 2026, message = "El año no puede ser menor al actual")
    private Integer anio;

    @NotNull(message = "El cupo máximo es obligatorio")
    @Min(value = 1, message = "El cupo mínimo debe ser al menos 1 alumno")
    @Max(value = 50, message = "El cupo máximo no puede exceder los 50 alumnos")
    private Integer cupoMaximo;

    @NotNull(message = "El ID del curso es obligatorio")
    private Long cursoId;

    @NotNull(message = "El ID del profesor es obligatorio")
    private Long profesorId;

    public SeccionRequest() {}

    public String getCodigoSeccion() { return codigoSeccion; }
    public void setCodigoSeccion(String codigoSeccion) { this.codigoSeccion = codigoSeccion; }

    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public Integer getCupoMaximo() { return cupoMaximo; }
    public void setCupoMaximo(Integer cupoMaximo) { this.cupoMaximo = cupoMaximo; }

    public Long getCursoId() { return cursoId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }

    public Long getProfesorId() { return profesorId; }
    public void setProfesorId(Long profesorId) { this.profesorId = profesorId; }
}