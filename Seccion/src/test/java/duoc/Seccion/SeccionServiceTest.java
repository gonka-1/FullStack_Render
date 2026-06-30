package duoc.Seccion;

import duoc.Seccion.dto.SeccionRequest;
import duoc.Seccion.model.Seccion;
import duoc.Seccion.repository.SeccionRepository;
import duoc.Seccion.service.SeccionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SeccionServiceTest {

    @Mock
    private SeccionRepository seccionRepository;

    @InjectMocks
    private SeccionService seccionService;

    @Test
    void crearDesdeRequest() {
        SeccionRequest request = new SeccionRequest();
        request.setCodigoSeccion("INF-4121");
        request.setSemestre("1");
        request.setAnio(2026);
        request.setCupoMaximo(30);
        request.setCursoId(1L);
        request.setProfesorId(2L);

        Seccion seccionGuardada = new Seccion();
        seccionGuardada.setIdSeccion(1L);
        seccionGuardada.setCodigoSeccion("INF-4121");
        seccionGuardada.setSemestre("1");
        seccionGuardada.setAnio(2026);
        seccionGuardada.setCupoMaximo(30);
        seccionGuardada.setCursoId(1L);
        seccionGuardada.setProfesorId(2L);

        when(seccionRepository.save(any(Seccion.class)))
                .thenReturn(seccionGuardada);

        Seccion resultado = seccionService.crearDesdeRequest(request);

        assertNotNull(resultado);
        assertEquals(Long.valueOf(1L), resultado.getIdSeccion());
        assertEquals("INF-4121", resultado.getCodigoSeccion());
        assertEquals("1", resultado.getSemestre());
        assertEquals(Integer.valueOf(2026), resultado.getAnio());
        assertEquals(Integer.valueOf(30), resultado.getCupoMaximo());
        assertEquals(Long.valueOf(1L), resultado.getCursoId());
        assertEquals(Long.valueOf(2L), resultado.getProfesorId());

        verify(seccionRepository, times(1))
                .save(any(Seccion.class));
    }
}