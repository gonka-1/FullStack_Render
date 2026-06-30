package Duoc.Curso;

import Duoc.Curso.WebClient.CarreraClient;
import Duoc.Curso.WebClient.ProfesorClient;
import Duoc.Curso.dto.CursoRequest;
import Duoc.Curso.model.Curso;
import Duoc.Curso.repository.CursoRepository;
import Duoc.Curso.service.CursoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private ProfesorClient profesorClient;

    @Mock
    private CarreraClient carreraClient;

    @InjectMocks
    private CursoService cursoService;

    @Test
    void crearDesdeRequest() {

        CursoRequest request = new CursoRequest();
        request.setNombre("Programacion Avanzada");
        request.setProfesorId(1L);
        request.setCarreraId(1L);

        Curso cursoGuardado = new Curso();
        cursoGuardado.setId(1L);
        cursoGuardado.setNombre("Programacion Avanzada");
        cursoGuardado.setProfesorId(1L);
        cursoGuardado.setCarreraId(1L);

        when(cursoRepository.save(any(Curso.class)))
                .thenReturn(cursoGuardado);

        Curso resultado = cursoService.crearDesdeRequest(request);

        assertNotNull(resultado);
        assertEquals("Programacion Avanzada", resultado.getNombre());
        assertEquals(1L, resultado.getProfesorId());
        assertEquals(1L, resultado.getCarreraId());

        verify(profesorClient, times(1)).obtenerProfesorPorId(1L);
        verify(carreraClient, times(1)).obtenerCarreraPorId(1L);
        verify(cursoRepository, times(1)).save(any(Curso.class));
    }
}