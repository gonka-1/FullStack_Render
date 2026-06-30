package Duoc.Curso.dto;

import jakarta.validation.constraints.NotBlank;

public class CursoRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    private Long profesorId;
    private Long carreraId;

    public CursoRequest() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Long getProfesorId() { return profesorId; }
    public void setProfesorId(Long profesorId) { this.profesorId = profesorId; }
    public Long getCarreraId() { return carreraId;}

    public void setCarreraId(Long carreraId) {
        this.carreraId = carreraId;
    }
}
