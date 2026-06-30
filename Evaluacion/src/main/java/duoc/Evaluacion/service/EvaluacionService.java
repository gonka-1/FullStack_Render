package duoc.Evaluacion.service;

import duoc.Evaluacion.WebClient.CursoClient;
import duoc.Evaluacion.dto.EvaluacionRequest;
import duoc.Evaluacion.exception.EvaluacionNoEncontradoException;
import duoc.Evaluacion.model.Evaluacion;
import duoc.Evaluacion.repository.EvaluacionRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EvaluacionService {

    private static final Logger log = LoggerFactory.getLogger(EvaluacionService.class);

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private CursoClient cursoClient;

    public List<Evaluacion> listarTodos() {
        log.info("Listando todas las evaluaciones");
        return evaluacionRepository.findAll();
    }

    public Evaluacion buscarPorId(Long id) {
        log.info("Buscando evaluacion por id: {}", id);
        return evaluacionRepository.findById(id)
                .orElseThrow(() -> new EvaluacionNoEncontradoException("No se encontro la evaluacion con id: " + id));
    }

    public Evaluacion crearDesdeRequest(EvaluacionRequest request) {
        log.info("Creando evaluacion con nombre: {}", request.getNombreEvaluacion());

        cursoClient.obtenerCursoPorId(request.getCursoId());

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNombreEvaluacion(request.getNombreEvaluacion());
        evaluacion.setTipoEvaluacion(request.getTipoEvaluacion());
        evaluacion.setPonderacion(request.getPonderacion());
        evaluacion.setFechaEvaluacion(request.getFechaEvaluacion());
        evaluacion.setCursoId(request.getCursoId());

        return evaluacionRepository.save(evaluacion);
    }

    public Evaluacion actualizarEvaluacion(Long id, EvaluacionRequest request){
        log.info("Actualizando evaluacion con id: {}", id);


        cursoClient.obtenerCursoPorId(request.getCursoId());

        Evaluacion evaluacion = buscarPorId(id);
        evaluacion.setNombreEvaluacion(request.getNombreEvaluacion());
        evaluacion.setTipoEvaluacion(request.getTipoEvaluacion());
        evaluacion.setPonderacion(request.getPonderacion());
        evaluacion.setFechaEvaluacion(request.getFechaEvaluacion());
        evaluacion.setCursoId(request.getCursoId());

        return evaluacionRepository.save(evaluacion);
    }

    public void eliminarEvaluacion(Long id){
        log.info("Eliminando evaluacion con id: {}", id);
        Evaluacion evaluacion = buscarPorId(id);
        evaluacionRepository.deleteById(id);
    }
}