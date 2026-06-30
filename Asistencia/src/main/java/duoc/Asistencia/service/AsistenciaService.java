package duoc.Asistencia.service;


import duoc.Asistencia.WebClient.CursoClient;
import duoc.Asistencia.WebClient.EstudianteClient;
import duoc.Asistencia.dto.AsistenciaRequest;
import duoc.Asistencia.exception.AsistenciaNoEncontradaException;
import duoc.Asistencia.model.Asistencia;
import duoc.Asistencia.repository.AsistenciaRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AsistenciaService {

    private static final Logger log = LoggerFactory.getLogger(AsistenciaService.class);

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private CursoClient cursoClient;

    @Autowired
    private EstudianteClient estudianteClient;

    public List<Asistencia> listarTodos() {
        log.info("Listando todos los asistencias");
        return asistenciaRepository.findAll();
    }

    public Asistencia buscarPorId(Integer id) {
        log.info("Buscando asistencia por id: {}", id);
        return asistenciaRepository.findById(id)
                .orElseThrow(() -> new AsistenciaNoEncontradaException("No se encontro la asistencia con id: " + id));
    }

    public Asistencia crearDesdeRequest(AsistenciaRequest request) {
        log.info("Creando asistencia con estado de Asistencia: {}", request.getEstadoAsistencia());

        cursoClient.obtenerCursoPorId(request.getCursoId());
        estudianteClient.obtenerEstudiantePorId(request.getEstudianteId());

        Asistencia asistencia = new Asistencia();
        asistencia.setFecha(request.getFecha());
        asistencia.setEstadoAsistencia(request.getEstadoAsistencia());
        asistencia.setCursoId(request.getCursoId());
        asistencia.setEstudianteId(request.getEstudianteId());

        return asistenciaRepository.save(asistencia);
    }


    public Asistencia actualizarAsistencia(Integer id, AsistenciaRequest request){
        log.info("Actualizando matricula con id: {}", id);

        Asistencia asistencia = buscarPorId(id);

        asistencia.setFecha(request.getFecha());
        asistencia.setEstadoAsistencia(request.getEstadoAsistencia());
        asistencia.setCursoId(request.getCursoId());
        asistencia.setEstudianteId(request.getEstudianteId());

        return asistenciaRepository.save(asistencia);
    }

    public void eliminarAsistencia(Integer id){
        log.info("Eliminado asistencia con id: {}", id);
        Asistencia asistencia = buscarPorId(id);
        asistenciaRepository.deleteById(id);
    }

}
