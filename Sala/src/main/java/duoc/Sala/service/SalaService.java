package duoc.Sala.service;

import duoc.Sala.dto.SalaRequest;
import duoc.Sala.exception.SalaNoEncontradaException;
import duoc.Sala.model.Sala;
import duoc.Sala.repository.SalaRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SalaService {

    private static final Logger log = LoggerFactory.getLogger(SalaService.class);

    @Autowired
    private SalaRepository salaRepository;

    public List<Sala> listarTodas() {
        log.info("Listando todas las salas");
        return salaRepository.findAll();
    }

    public Sala buscarPorId(Long id) {
        log.info("Buscando sala por id: {}", id);
        return salaRepository.findById(id)
                .orElseThrow(() -> new SalaNoEncontradaException("No se encontró la sala con id: " + id));
    }

    public Sala crearDesdeRequest(SalaRequest request) {
        log.info("Creando sala con nombre: {}", request.getNombreSala());

        Sala sala = new Sala();
        sala.setNombreSala(request.getNombreSala());
        sala.setCapacidad(request.getCapacidad());
        sala.setUbicacion(request.getUbicacion());
        sala.setTipoSala(request.getTipoSala());
        sala.setEstadoSala(request.getEstadoSala());

        return salaRepository.save(sala);
    }

    public Sala actualizarSala(Long id, SalaRequest request) {
        log.info("Actualizando sala con id: {}", id);

        Sala sala = buscarPorId(id);
        sala.setNombreSala(request.getNombreSala());
        sala.setCapacidad(request.getCapacidad());
        sala.setUbicacion(request.getUbicacion());
        sala.setTipoSala(request.getTipoSala());
        sala.setEstadoSala(request.getEstadoSala());

        return salaRepository.save(sala);
    }

    public void eliminarSala(Long id) {
        log.info("Eliminando sala con id: {}", id);
        Sala sala = buscarPorId(id);
        salaRepository.deleteById(id);
    }
}