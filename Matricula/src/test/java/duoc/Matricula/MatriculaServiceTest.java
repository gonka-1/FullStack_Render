package duoc.Matricula;

import duoc.Matricula.WebClient.CursoClient;
import duoc.Matricula.WebClient.EstudianteClient;
import duoc.Matricula.dto.MatriculaRequest;
import duoc.Matricula.model.Matricula;
import duoc.Matricula.repository.MatriculaRepository;
import duoc.Matricula.service.MatriculaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatriculaServiceTest {

    @Mock
    private MatriculaRepository matriculaRepository;

    @Mock
    private CursoClient cursoClient;

    @Mock
    private EstudianteClient estudianteClient;

    @InjectMocks
    private MatriculaService matriculaService;

    @Test
    void crearDesdeRequest() {

        MatriculaRequest request = new MatriculaRequest();
        request.setCursoId(1L);
        request.setEstudianteId(1L);
        request.setCalificacion("6.0");

        Matricula matriculaGuardada = new Matricula();
        matriculaGuardada.setId(1L);
        matriculaGuardada.setCursoId(1L);
        matriculaGuardada.setEstudianteId(1L);
        matriculaGuardada.setCalificacion("6.0");

        when(matriculaRepository.save(any(Matricula.class)))
                .thenReturn(matriculaGuardada);

        Matricula resultado = matriculaService.crearDesdeRequest(request);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getCursoId());
        assertEquals(1L, resultado.getEstudianteId());
        assertEquals("6.0", resultado.getCalificacion());

        verify(cursoClient, times(1)).obtenerCursoPorId(1L);
        verify(estudianteClient, times(1)).obtenerEstudiantePorId(1L);
        verify(matriculaRepository, times(1)).save(any(Matricula.class));
    }
}