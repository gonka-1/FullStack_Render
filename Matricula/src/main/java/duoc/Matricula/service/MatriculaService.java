package duoc.Matricula.service;

import duoc.Matricula.WebClient.CursoClient;
import duoc.Matricula.WebClient.EstudianteClient;
import duoc.Matricula.dto.MatriculaRequest;
import duoc.Matricula.exception.MatriculaNoEncontradaException;
import duoc.Matricula.model.Matricula;
import duoc.Matricula.repository.MatriculaRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MatriculaService {

    private static final Logger log = LoggerFactory.getLogger(MatriculaService.class);

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private CursoClient cursoClient;

    @Autowired
    private EstudianteClient estudianteClient;

    public List<Matricula> listarTodos() {
        log.info("Listando todos los matricula");
        return matriculaRepository.findAll();
    }

    public Matricula buscarPorId(Long id) {
        log.info("Buscando matricula por id: {}", id);
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new MatriculaNoEncontradaException("No se encontro la matricula con id: " + id));
    }

    public Matricula crearDesdeRequest(MatriculaRequest request) {
        log.info("Creando matricula con calificación: {}", request.getCalificacion());

        cursoClient.obtenerCursoPorId(request.getCursoId());
        estudianteClient.obtenerEstudiantePorId(request.getEstudianteId());

        Matricula matricula = new Matricula();
        matricula.setCursoId(request.getCursoId());
        matricula.setEstudianteId(request.getEstudianteId());
        matricula.setCalificacion(request.getCalificacion());

        return matriculaRepository.save(matricula);
    }


    public Matricula actualizarMatricula(Long id, MatriculaRequest request){
        log.info("Actualizando matricula con id: {}", id);

        Matricula matricula = buscarPorId(id);
        matricula.setCursoId(request.getCursoId());
        matricula.setEstudianteId(request.getEstudianteId());
        matricula.setCalificacion(request.getCalificacion());

        return matriculaRepository.save(matricula);
    }

    public void eliminarMatricula(Long id){
        log.info("Eliminado matricula con id: {}", id);
        Matricula matricula = buscarPorId(id);
        matriculaRepository.deleteById(id);
    }

}
