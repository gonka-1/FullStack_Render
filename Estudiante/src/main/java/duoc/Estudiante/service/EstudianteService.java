package duoc.Estudiante.service;

import duoc.Estudiante.WebClient.CarreraClient;
import duoc.Estudiante.dto.EstudianteRequest;
import duoc.Estudiante.exception.EstudianteNoEncontradoException;
import duoc.Estudiante.model.Estudiante;
import duoc.Estudiante.repository.EstudianteRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
@Transactional
public class EstudianteService {


    private static final Logger log = LoggerFactory.getLogger(EstudianteRepository.class);


    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CarreraClient carreraClient;

    public List<Estudiante> listarTodos() {
        log.info("Listando todos los estudiantes");
        return estudianteRepository.findAll();
    }

    public Estudiante buscarPorId(Integer id) {
        log.info("Buscando estudiante con id: {}", id);
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new EstudianteNoEncontradoException("No se encontró el estudiante con id: " + id));
    }

    public Estudiante crearDesdeRequest(EstudianteRequest request) {
        log.info("Creando estudiante con run: {}", request.getRun());

        carreraClient.obtenerCarreraPorId(request.getCarreraId());

        Estudiante estudiante = new Estudiante();
        estudiante.setRun(request.getRun());
        estudiante.setNombre(request.getNombre());
        estudiante.setEmail(request.getEmail());
        estudiante.setCarreraId(request.getCarreraId());

        return estudianteRepository.save(estudiante);
    }

    public Estudiante actualizar(Integer id, EstudianteRequest request) {
        log.info("Actualizando estudiante con id: {}", id);

        Estudiante estudiante = buscarPorId(id);
        estudiante.setRun(request.getRun());
        estudiante.setNombre(request.getNombre());
        estudiante.setEmail(request.getEmail());
        estudiante.setCarreraId(request.getCarreraId());

        return estudianteRepository.save(estudiante);
    }

    public void eliminar(Integer id) {
        log.info("Eliminando Estudiante con id: {}", id);
        Estudiante estudiante = buscarPorId(id);
        estudianteRepository.delete(estudiante);
    }
}