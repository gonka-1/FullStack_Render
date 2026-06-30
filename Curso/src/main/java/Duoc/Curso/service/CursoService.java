package Duoc.Curso.service;


import Duoc.Curso.WebClient.CarreraClient;
import Duoc.Curso.WebClient.ProfesorClient;
import Duoc.Curso.dto.CursoRequest;
import Duoc.Curso.exception.CursoNoEncontradoException;
import Duoc.Curso.model.Curso;
import Duoc.Curso.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CursoService {

    private static final Logger log = LoggerFactory.getLogger(CursoService.class);

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfesorClient profesorClient;

    @Autowired
    private CarreraClient carreraClient;

    public List<Curso> listarTodos() {
        log.info("Listando todos los cursos");
        return cursoRepository.findAll();
    }

    public Curso buscarPorId(Long id) {
        log.info("Buscando curso por id: {}", id);
        return cursoRepository.findById(id)
                .orElseThrow(() -> new CursoNoEncontradoException("No se encontro el curso con id: " + id));
    }

    public Curso crearDesdeRequest(CursoRequest request) {
        log.info("Creando curso con nombre: {}", request.getNombre());

        profesorClient.obtenerProfesorPorId(request.getProfesorId());
        carreraClient.obtenerCarreraPorId(request.getCarreraId());

        Curso curso = new Curso();
        curso.setNombre(request.getNombre());
        curso.setProfesorId(request.getProfesorId());
        curso.setCarreraId(request.getCarreraId());

        return cursoRepository.save(curso);
    }

    public Curso actualizarCurso(Long id, CursoRequest request){
        log.info("Actualizando curso con id: {}", id);

        Curso curso = buscarPorId(id);
        curso.setNombre(request.getNombre());
        curso.setProfesorId(request.getProfesorId());
        curso.setCarreraId(request.getCarreraId());

        return cursoRepository.save(curso);
    }

    public void eliminarCurso(Long id){
        log.info("Eliminado curso con id: {}", id);
        Curso curso = buscarPorId(id);
        cursoRepository.deleteById(id);
    }

}
