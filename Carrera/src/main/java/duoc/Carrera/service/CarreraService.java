package duoc.Carrera.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duoc.Carrera.dto.CarreraRequest;
import duoc.Carrera.exception.CarreraNoEncontradaException;
import duoc.Carrera.model.Carrera;
import duoc.Carrera.repository.CarreraRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarreraService {

    private static final Logger log = LoggerFactory.getLogger(CarreraRepository.class);


    @Autowired
    private CarreraRepository carreraRepository;

    public List<Carrera> listarTodos() {
        log.info("Listando todos las carreras");
        return carreraRepository.findAll();
    }

    public Carrera buscarPorId(Long id) {
        log.info("Buscando carrera con id: {}", id);
        return carreraRepository.findById(id)
                .orElseThrow(() -> new CarreraNoEncontradaException("No se encontró el estudiante con id: " + id));
    }

    public Carrera crearDesdeRequest(CarreraRequest request) {
        log.info("Creando carrera con nombre: {}", request.getNombre());

        Carrera carrera = new Carrera();
        carrera.setNombre(request.getNombre());
        carrera.setCodigoCarrera(request.getCodigoCarrera());
        carrera.setDuracionSemestre(request.getDuracionSemestre());
        carrera.setEstadoCarrera(request.getEstadoCarrera());

        return carreraRepository.save(carrera);
    }

    public Carrera actualizar(Long id, CarreraRequest request) {
        log.info("Actualizando carrera con id: {}", id);

        Carrera carrera = buscarPorId(id);
        carrera.setNombre(request.getNombre());
        carrera.setCodigoCarrera(request.getCodigoCarrera());
        carrera.setDuracionSemestre(request.getDuracionSemestre());
        carrera.setEstadoCarrera(request.getEstadoCarrera());


        return carreraRepository.save(carrera);
    }

    public void eliminar(Long id) {
        log.info("Eliminando Carrera con id: {}", id);
        Carrera carrera = buscarPorId(id);
        carreraRepository.delete(carrera);
    }
}
