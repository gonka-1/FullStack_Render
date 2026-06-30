package duoc.Sala;

import duoc.Sala.dto.SalaRequest;
import duoc.Sala.model.Sala;
import duoc.Sala.repository.SalaRepository;
import duoc.Sala.service.SalaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SalaServiceTest {

    @Mock
    private SalaRepository salaRepository;

    @InjectMocks
    private SalaService salaService;

    @Test
    void crearDesdeRequest() {
        SalaRequest request = new SalaRequest();
        request.setNombreSala("Sala 204");
        request.setCapacidad(35);
        request.setUbicacion("Sede San Carlos, Piso 2");
        request.setTipoSala("Laboratorio de Computación");
        request.setEstadoSala("Disponible");

        Sala salaGuardada = new Sala();
        salaGuardada.setIdSala(1L);
        salaGuardada.setNombreSala("Sala 204");
        salaGuardada.setCapacidad(35);
        salaGuardada.setUbicacion("Sede San Carlos, Piso 2");
        salaGuardada.setTipoSala("Laboratorio de Computación");
        salaGuardada.setEstadoSala("Disponible");

        when(salaRepository.save(any(Sala.class)))
                .thenReturn(salaGuardada);

        Sala resultado = salaService.crearDesdeRequest(request);

        assertNotNull(resultado);
        assertEquals(Long.valueOf(1L), resultado.getIdSala());
        assertEquals("Sala 204", resultado.getNombreSala());
        assertEquals(Integer.valueOf(35), resultado.getCapacidad());
        assertEquals("Sede San Carlos, Piso 2", resultado.getUbicacion());
        assertEquals("Laboratorio de Computación", resultado.getTipoSala());
        assertEquals("Disponible", resultado.getEstadoSala());

        verify(salaRepository, times(1))
                .save(any(Sala.class));
    }
}