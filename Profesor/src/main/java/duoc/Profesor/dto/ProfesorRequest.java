package duoc.Profesor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProfesorRequest {

    @NotBlank(message = "El run es obligatorio")
    @Size(min = 9, max = 13, message = "El run debe tener entre 9 y 13 caracteres")
    private String run;

    @NotBlank(message = "El nombre es obligartorio")
    @Size(max = 100, message =  "El nombre no debe de superar los 100 caracteres")
    private String nombre;

    @NotBlank(message = "La especialidad es obligartoria")
    @Size(max = 50, message =  "La especialidad no debe llevar más de 50 caracteres")
    private String especialidad;

    public ProfesorRequest() {
    }

    public String getRun() {
        return run;
    }
    public String getNombre() {
        return nombre;
    }
    public String getEspecialidad() {
        return especialidad;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}
