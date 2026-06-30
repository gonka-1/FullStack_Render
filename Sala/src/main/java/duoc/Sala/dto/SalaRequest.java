package duoc.Sala.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SalaRequest {

    @NotBlank(message = "El nombre de la sala es obligatorio")
    private String nombreSala;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad mínima debe ser de al menos 1 persona")
    private Integer capacidad;

    @NotBlank(message = "La ubicación es obligatoria")
    private String ubicacion;

    @NotBlank(message = "El tipo de sala es obligatorio (Laboratorio, Aula, Taller)")
    private String tipoSala;

    @NotBlank(message = "El estado de la sala es obligatorio (Disponible, Ocupada)")
    private String estadoSala;


    public SalaRequest() {}

    public String getNombreSala() { return nombreSala; }
    public void setNombreSala(String nombreSala) { this.nombreSala = nombreSala; }

    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getTipoSala() { return tipoSala; }
    public void setTipoSala(String tipoSala) { this.tipoSala = tipoSala; }

    public String getEstadoSala() { return estadoSala; }
    public void setEstadoSala(String estadoSala) { this.estadoSala = estadoSala; }
}