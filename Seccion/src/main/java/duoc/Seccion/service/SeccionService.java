package duoc.Seccion.service;

import duoc.Seccion.WebClient.CursoClient;
import duoc.Seccion.WebClient.ProfesorClient;
import duoc.Seccion.dto.SeccionRequest;
import duoc.Seccion.exception.SeccionNoEncontradaException;
import duoc.Seccion.model.Seccion;
import duoc.Seccion.repository.SeccionRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SeccionService {

    private static final Logger log = LoggerFactory.getLogger(SeccionService.class);

    @Autowired
    private SeccionRepository seccionRepository;

    @Autowired
    private CursoClient cursoClient;

    @Autowired
    private ProfesorClient profesorClient;

    public List<Seccion> listarTodos() {
        log.info("Listando todas las secciones");
        return seccionRepository.findAll();
    }

    public Seccion buscarPorId(Long id) {
        log.info("Buscando seccion por id: {}", id);
        return seccionRepository.findById(id)
                .orElseThrow(() -> new SeccionNoEncontradaException("No se encontro la seccion con id: " + id));
    }

    public Seccion crearDesdeRequest(SeccionRequest request) {
        log.info("Creando seccion con codigo: {}", request.getCodigoSeccion());

        cursoClient.obtenerCursoPorId(request.getCursoId());
        profesorClient.obtenerProfesorPorId(request.getProfesorId());

        Seccion seccion = new Seccion();
        seccion.setCodigoSeccion(request.getCodigoSeccion());
        seccion.setSemestre(request.getSemestre());
        seccion.setAnio(request.getAnio());
        seccion.setCupoMaximo(request.getCupoMaximo());
        seccion.setCursoId(request.getCursoId());
        seccion.setProfesorId(request.getProfesorId());

        return seccionRepository.save(seccion);
    }

    public Seccion actualizarSeccion(Long id, SeccionRequest request) {
        log.info("Actualizando seccion con id: {}", id);

        cursoClient.obtenerCursoPorId(request.getCursoId());
        profesorClient.obtenerProfesorPorId(request.getProfesorId());

        Seccion seccion = buscarPorId(id);
        seccion.setCodigoSeccion(request.getCodigoSeccion());
        seccion.setSemestre(request.getSemestre());
        seccion.setAnio(request.getAnio());
        seccion.setCupoMaximo(request.getCupoMaximo());
        seccion.setCursoId(request.getCursoId());
        seccion.setProfesorId(request.getProfesorId());

        return seccionRepository.save(seccion);
    }

    public void eliminarSeccion(Long id) {
        log.info("Eliminando seccion con id: {}", id);
        Seccion seccion = buscarPorId(id);
        seccionRepository.deleteById(id);
    }
}