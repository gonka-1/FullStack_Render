package duoc.Estudiante.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class EstudianteRequest {

    @NotBlank(message = "El run es obligatorio")
    @Size(min = 9, max = 13, message = "El run debe tener entre 9 y 13 caracteres")
    private String run;

    @NotBlank(message = "El nombre es obligartorio")
    @Size(max = 100, message =  "El nombre no debe de superar los 100 caracteres")
    private String nombre;

    @NotBlank(message = "El email es obligartorio")
    @Email(message = "El correo debe tener formato válido")
    private String email;

    private Long carreraId;

    public EstudianteRequest() {
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(Long carreraId) {
        this.carreraId = carreraId;
    }
}
