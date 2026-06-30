package duoc.Profesor.service;

import duoc.Profesor.Repository.ProfesorRepository;
import duoc.Profesor.dto.ProfesorRequest;
import duoc.Profesor.exception.ProfesorNoEncontradoException;
import duoc.Profesor.model.Profesor;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Service
@Transactional
public class ProfesorService {

    private static final Logger log = LoggerFactory.getLogger(ProfesorRepository.class);


    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Profesor> listarTodos() {
        log.info("Listando todos los profesores");
        return profesorRepository.findAll();
    }

    public Profesor buscarPorId(Integer id) {
        log.info("Buscando profesor con id: {}", id);
        return profesorRepository.findById(id)
                .orElseThrow(() -> new ProfesorNoEncontradoException("No se encontró el profesor con id: " + id));
    }

    public Profesor crearDesdeRequest(ProfesorRequest request) {
        log.info("Creando profesor con run: {}", request.getRun());

        Profesor profesor = new Profesor();
        profesor.setRun(request.getRun());
        profesor.setNombre(request.getNombre());
        profesor.setEspecialidad(request.getEspecialidad());

        return profesorRepository.save(profesor);
    }

    public Profesor actualizar(Integer id, ProfesorRequest request) {
        log.info("Actualizando profesor con id: {}", id);

        Profesor profesor = buscarPorId(id);
        profesor.setRun(request.getRun());
        profesor.setNombre(request.getNombre());
        profesor.setEspecialidad(request.getEspecialidad());

        return profesorRepository.save(profesor);
    }

    public void eliminar(Integer id) {
        log.info("Eliminando profesor con id: {}", id);
        Profesor profesor = buscarPorId(id);
        profesorRepository.delete(profesor);
    }
}
