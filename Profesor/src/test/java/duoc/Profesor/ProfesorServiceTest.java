package duoc.Profesor;

import duoc.Profesor.Repository.ProfesorRepository;
import duoc.Profesor.dto.ProfesorRequest;
import duoc.Profesor.model.Profesor;
import duoc.Profesor.service.ProfesorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfesorServiceTest {

    @Mock
    private ProfesorRepository profesorRepository;

    @InjectMocks
    private ProfesorService profesorService;

    @Test
    void crearDesdeRequest() {

        ProfesorRequest request = new ProfesorRequest();
        request.setRun("12345678-9");
        request.setNombre("Juan Perez");
        request.setEspecialidad("Programacion");

        Profesor profesorGuardado = new Profesor();
        profesorGuardado.setId(1);
        profesorGuardado.setRun("12345678-9");
        profesorGuardado.setNombre("Juan Perez");
        profesorGuardado.setEspecialidad("Programacion");

        when(profesorRepository.save(any(Profesor.class)))
                .thenReturn(profesorGuardado);

        Profesor resultado = profesorService.crearDesdeRequest(request);

        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombre());
        assertEquals("12345678-9", resultado.getRun());
        assertEquals("Programacion", resultado.getEspecialidad());

        verify(profesorRepository, times(1))
                .save(any(Profesor.class));
    }
}