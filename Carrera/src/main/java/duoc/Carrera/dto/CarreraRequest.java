package duoc.Carrera.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CarreraRequest {

    @NotBlank(message = "El nombre de la carrera es obligatorio")
    private String nombre;

    @NotBlank(message = "El nombre es obligartorio")
    @Size(max = 10, message =  "El código no debe de superar los 10 caracteres")
    private int codigoCarrera;

    private String duracionSemestre;

    private String estadoCarrera;

    public CarreraRequest() {
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigoCarrera() {
        return codigoCarrera;
    }
    public void setCodigoCarrera(int codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }

    public String getDuracionSemestre() {
        return duracionSemestre;
    }

    public void setDuracionSemestre(String duracionSemestre) {
        this.duracionSemestre = duracionSemestre;
    }

    public String getEstadoCarrera() {
        return estadoCarrera;
    }

    public void setEstadoCarerra(String estadoCarerra) {
        this.estadoCarrera = estadoCarerra;
    }
}
