package duoc.Asistencia.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class AsistenciaRequest {

    private Date fecha;

    @NotBlank(message = "El estado es obligatorio")
    private String estadoAsistencia;

    private Long estudianteId;

    private Long cursoId;

    public AsistenciaRequest() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstadoAsistencia() {
        return estadoAsistencia;
    }

    public void setEstadoAsistencia(String estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
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
}
