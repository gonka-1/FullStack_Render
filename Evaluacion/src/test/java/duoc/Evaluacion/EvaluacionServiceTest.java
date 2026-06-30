package duoc.Evaluacion;

import duoc.Evaluacion.dto.EvaluacionRequest;
import duoc.Evaluacion.model.Evaluacion;
import duoc.Evaluacion.repository.EvaluacionRepository;
import duoc.Evaluacion.service.EvaluacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EvaluacionServiceTest {

    @Mock
    private EvaluacionRepository evaluacionRepository;

    @InjectMocks
    private EvaluacionService evaluacionService;

    @Test
    void crearDesdeRequest() {
        EvaluacionRequest request = new EvaluacionRequest();
        request.setNombreEvaluacion("Prueba de Unidad 1");
        request.setTipoEvaluacion("Solemne");
        request.setPonderacion(20.0);
        request.setFechaEvaluacion(LocalDate.now().plusDays(5));
        request.setCursoId(1L);

        Evaluacion evaluacionGuardada = new Evaluacion();
        evaluacionGuardada.setIdEvaluacion(1L);
        evaluacionGuardada.setNombreEvaluacion("Prueba de Unidad 1");
        evaluacionGuardada.setTipoEvaluacion("Solemne");
        evaluacionGuardada.setPonderacion(20.0);
        evaluacionGuardada.setFechaEvaluacion(request.getFechaEvaluacion());
        evaluacionGuardada.setCursoId(1L);


        when(evaluacionRepository.save(any(Evaluacion.class)))
                .thenReturn(evaluacionGuardada);

        Evaluacion resultado = evaluacionService.crearDesdeRequest(request);

        assertNotNull(resultado);
        assertEquals(Long.valueOf(1L), resultado.getIdEvaluacion());
        assertEquals("Prueba de Unidad 1", resultado.getNombreEvaluacion());
        assertEquals("Solemne", resultado.getTipoEvaluacion());
        assertEquals(20.0, resultado.getPonderacion());
        assertEquals(request.getFechaEvaluacion(), resultado.getFechaEvaluacion());
        assertEquals(Long.valueOf(1L), resultado.getCursoId());

        verify(evaluacionRepository, times(1))
                .save(any(Evaluacion.class));
    }
}