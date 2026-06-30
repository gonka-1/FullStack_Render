package Duoc.Nota.service;

import Duoc.Nota.client.MatriculaClient;
import Duoc.Nota.exceptions.NotaNoEncontradaException;
import Duoc.Nota.models.Nota;
import Duoc.Nota.repository.NotaRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class NotaService {

    private static final Logger log = LoggerFactory.getLogger(NotaService.class);

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private MatriculaClient matriculaClient;

    public List<Nota> listarTodasLasNotas() {
        log.info("Listando todas las notas");
        return notaRepository.findAll();
    }

    public Nota buscarPorId(Long id) {
        log.info("Buscando nota por id: {}", id);
        return notaRepository.findById(id)
                .orElseThrow(() -> new NotaNoEncontradaException("No se encontró la nota con id: " + id));
    }

    public Nota crearDesdeRequest(Nota nota) {
        log.info("Creando nota con matriculaId: {}", nota.getMatriculaId());
        matriculaClient.obtenerMatriculaPorId(nota.getMatriculaId()); //
        return notaRepository.save(nota);
    }

    public Nota actualizarNota(Long id, Nota nota) {
        log.info("Actualizando nota con id: {}", id);
        Nota existente = buscarPorId(id);
        existente.setMatriculaId(nota.getMatriculaId());
        existente.setCalificacion(nota.getCalificacion());
        return notaRepository.save(existente);
    }

    public void eliminarNota(Long id) {
        log.info("Eliminando nota con id: {}", id);
        Nota nota = buscarPorId(id);
        notaRepository.deleteById(id);
    }
}
