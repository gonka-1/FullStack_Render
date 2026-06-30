package duoc.Evaluacion.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public class EvaluacionRequest {

    @NotBlank(message = "El nombre de la evaluación es obligatorio")
    private String nombreEvaluacion;
    private String tipoEvaluacion;
    private Double ponderacion;
    private LocalDate fechaEvaluacion;
    private Long cursoId;

    public EvaluacionRequest() {}

    public String getNombreEvaluacion() {
        return nombreEvaluacion;
    }

    public void setNombreEvaluacion(String nombreEvaluacion) {
        this.nombreEvaluacion = nombreEvaluacion;
    }

    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public Double getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(Double ponderacion) {
        this.ponderacion = ponderacion;
    }

    public LocalDate getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(LocalDate fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
}