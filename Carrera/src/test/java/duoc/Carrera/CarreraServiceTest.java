package duoc.Carrera; // Ajusta el paquete según la estructura de tu proyecto

import duoc.Carrera.repository.CarreraRepository;
import duoc.Carrera.dto.CarreraRequest; // Asumiendo que tienes un DTO similar al de Estudiante
import duoc.Carrera.model.Carrera;
import duoc.Carrera.service.CarreraService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarreraServiceTest {

    @Mock
    private CarreraRepository carreraRepository;

    @InjectMocks
    private CarreraService carreraService;

    @Test
    void crearDesdeRequest() {

        // 1. Arrange (Preparar los datos de prueba)
        CarreraRequest request = new CarreraRequest();
        request.setNombre("Ingeniería en Informática");
        request.setCodigoCarrera(2021);
        request.setDuracionSemestre("8 Semestres");
        request.setEstadoCarrera("Activa");

        Carrera carreraGuardada = new Carrera();
        carreraGuardada.setId(1L); // Simulamos que la BD le asignó un ID
        carreraGuardada.setNombre("Ingeniería en Informática");
        carreraGuardada.setCodigoCarrera(2021);
        carreraGuardada.setDuracionSemestre("8 Semestres");
        carreraGuardada.setEstadoCarrera("Activa");

        // Mock del repositorio: cuando se intente guardar cualquier Carrera, devuelve nuestra carreraGuardada
        when(carreraRepository.save(any(Carrera.class)))
                .thenReturn(carreraGuardada);

        // 2. Act (Ejecutar el método real del servicio)
        Carrera resultado = carreraService.crearDesdeRequest(request);

        // 3. Assert (Verificar que los resultados sean los esperados)
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Ingeniería en Informática", resultado.getNombre());
        assertEquals(2021, resultado.getCodigoCarrera());
        assertEquals("8 Semestres", resultado.getDuracionSemestre());
        assertEquals("Activa", resultado.getEstadoCarrera());

        // Verificamos que el método save() del repositorio se haya llamado exactamente 1 vez
        verify(carreraRepository, times(1))
                .save(any(Carrera.class));
    }
}