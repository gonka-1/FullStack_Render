package duoc.Asistencia;

import duoc.Asistencia.WebClient.CursoClient;
import duoc.Asistencia.WebClient.EstudianteClient;
import duoc.Asistencia.dto.AsistenciaRequest;
import duoc.Asistencia.model.Asistencia;
import duoc.Asistencia.repository.AsistenciaRepository;
import duoc.Asistencia.service.AsistenciaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AsistenciaServiceTest {

    @Mock
    private AsistenciaRepository asistenciaRepository;

    @Mock
    private CursoClient cursoClient;

    @Mock
    private EstudianteClient estudianteClient;

    @InjectMocks
    private AsistenciaService asistenciaService;

    @Test
    void crearDesdeRequest() {

        AsistenciaRequest request = new AsistenciaRequest();
        request.setFecha(new Date());
        request.setEstadoAsistencia("Presente");
        request.setCursoId(1L);
        request.setEstudianteId(1L);

        Asistencia asistenciaGuardada = new Asistencia();
        asistenciaGuardada.setId(1);
        asistenciaGuardada.setFecha(request.getFecha());
        asistenciaGuardada.setEstadoAsistencia("Presente");
        asistenciaGuardada.setCursoId(1L);
        asistenciaGuardada.setEstudianteId(1L);

        when(asistenciaRepository.save(any(Asistencia.class)))
                .thenReturn(asistenciaGuardada);

        Asistencia resultado = asistenciaService.crearDesdeRequest(request);

        assertNotNull(resultado);
        assertEquals("Presente", resultado.getEstadoAsistencia());
        assertEquals(1L, resultado.getCursoId());
        assertEquals(1L, resultado.getEstudianteId());

        verify(cursoClient, times(1)).obtenerCursoPorId(1L);
        verify(estudianteClient, times(1)).obtenerEstudiantePorId(1L);
        verify(asistenciaRepository, times(1)).save(any(Asistencia.class));
    }
}