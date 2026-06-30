package duoc.Matricula.dto;

import jakarta.validation.constraints.NotBlank;

public class MatriculaRequest {


    private Long estudianteId;

    private Long cursoId;

    @NotBlank(message = "La calificación es obligatoria")
    private String calificacion;

    public MatriculaRequest() {
    }

    public Long getEstudianteId() {
        return estudianteId;
    }
    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }
    public Long getCursoId() {
        return cursoId;
    }
    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
    public String getCalificacion() {
        return calificacion;
    }
    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }


}
