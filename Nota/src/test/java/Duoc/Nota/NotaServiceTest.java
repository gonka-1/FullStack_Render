package Duoc.Nota;

import Duoc.Nota.client.MatriculaClient;
import Duoc.Nota.models.Nota;
import Duoc.Nota.repository.NotaRepository;
import Duoc.Nota.service.NotaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotaServiceTest {

    @Mock
    private NotaRepository notaRepository;

    @Mock
    private MatriculaClient matriculaClient;

    @InjectMocks
    private NotaService notaService;

    @Test
    void crearDesdeRequest() {
        Nota notaInput = new Nota();
        notaInput.setMatriculaId(10L);
        notaInput.setCalificacion(6.5);

        Nota notaGuardada = new Nota();
        notaGuardada.setId(1L);
        notaGuardada.setMatriculaId(10L);
        notaGuardada.setCalificacion(6.5);

        doNothing().when(matriculaClient).obtenerMatriculaPorId(10L);

        when(notaRepository.save(any(Nota.class)))
                .thenReturn(notaGuardada);

        Nota resultado = notaService.crearDesdeRequest(notaInput);

        assertNotNull(resultado);
        assertEquals(Long.valueOf(1L), resultado.getId());
        assertEquals(Long.valueOf(10L), resultado.getMatriculaId());
        assertEquals(Double.valueOf(6.5), resultado.getCalificacion());

        verify(matriculaClient, times(1)).obtenerMatriculaPorId(10L);
        verify(notaRepository, times(1)).save(any(Nota.class));
    }
}