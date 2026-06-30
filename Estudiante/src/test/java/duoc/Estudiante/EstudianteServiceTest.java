package duoc.Estudiante;

import duoc.Estudiante.WebClient.CarreraClient;
import duoc.Estudiante.repository.EstudianteRepository;
import duoc.Estudiante.dto.EstudianteRequest;
import duoc.Estudiante.model.Estudiante;
import duoc.Estudiante.service.EstudianteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @Mock
    private CarreraClient carreraClient;

    @InjectMocks
    private EstudianteService estudianteService;

    @Test
    void crearDesdeRequest() {

        // Arrange
        EstudianteRequest request = new EstudianteRequest();
        request.setRun("12345678-9");
        request.setNombre("Juan Perez");
        request.setEmail("juan@duoc.cl");
        request.setCarreraId(1L);

        Estudiante estudianteGuardado = new Estudiante();
        estudianteGuardado.setId(1);
        estudianteGuardado.setRun("12345678-9");
        estudianteGuardado.setNombre("Juan Perez");
        estudianteGuardado.setEmail("juan@duoc.cl");
        estudianteGuardado.setCarreraId(1L);

        // Mock del WebClient (IMPORTANTE)
        when(carreraClient.obtenerCarreraPorId(anyLong()))
                .thenReturn(null); // o DTO si existe

        // Mock del repositorio
        when(estudianteRepository.save(any(Estudiante.class)))
                .thenReturn(estudianteGuardado);

        // Act
        Estudiante resultado = estudianteService.crearDesdeRequest(request);

        // Assert
        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombre());
        assertEquals("12345678-9", resultado.getRun());
        assertEquals("juan@duoc.cl", resultado.getEmail());
        assertEquals(1L, resultado.getCarreraId());

        verify(estudianteRepository, times(1))
                .save(any(Estudiante.class));

        verify(carreraClient, times(1))
                .obtenerCarreraPorId(anyLong());
    }
}