package duoc.Carrera.controller;

import duoc.Carrera.assemblers.CarreraModelAssembler;
import duoc.Carrera.dto.CarreraRequest;
import duoc.Carrera.model.Carrera;
import duoc.Carrera.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/v2/carrera")
public class CarreraControllerV2 {

    @Autowired
    private CarreraService carreraService;

    @Autowired
    private CarreraModelAssembler assembler;

    // Cambiado para soportar ambos formatos de salida en Swagger
    @GetMapping(produces = { MediaTypes.HAL_JSON_VALUE, "application/json" })
    public CollectionModel<EntityModel<Carrera>> getAllCarrera() {

        List<EntityModel<Carrera>> carreras = carreraService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(carreras,
                linkTo(methodOn(CarreraControllerV2.class).getAllCarrera()).withSelfRel());
    }

    // Cambiado para soportar ambos formatos de salida en Swagger
    @GetMapping(value = "/{id}", produces = { MediaTypes.HAL_JSON_VALUE, "application/json" })
    public EntityModel<Carrera> getCarreraById(@PathVariable long id) {

        Carrera carrera = carreraService.buscarPorId(id);
        return assembler.toModel(carrera);
    }

    // Cambiado para soportar ambos formatos de salida en Swagger
    @PostMapping(produces = { MediaTypes.HAL_JSON_VALUE, "application/json" })
    public ResponseEntity<EntityModel<Carrera>> createProfesor(@RequestBody Carrera carrera) {

        CarreraRequest req = new CarreraRequest();
        req.setNombre(carrera.getNombre());
        req.setCodigoCarrera(carrera.getCodigoCarrera());
        req.setDuracionSemestre(carrera.getDuracionSemestre());
        req.setEstadoCarerra(req.getEstadoCarerra());

        Carrera newCarrera = carreraService.crearDesdeRequest(req);

        return ResponseEntity
                .created(linkTo(methodOn(CarreraControllerV2.class)
                        .getCarreraById(newCarrera.getId())).toUri())
                .body(assembler.toModel(newCarrera));
    }

    // Cambiado para soportar ambos formatos de salida en Swagger
    @PutMapping(value = "/{id}", produces = { MediaTypes.HAL_JSON_VALUE, "application/json" })
    public ResponseEntity<EntityModel<Carrera>> updateCarrera(
            @PathVariable long id,
            @RequestBody Carrera carrera) {

        CarreraRequest req = new CarreraRequest();
        req.setNombre(carrera.getNombre());
        req.setCodigoCarrera(carrera.getCodigoCarrera());
        req.setDuracionSemestre(carrera.getDuracionSemestre());
        req.setEstadoCarrera(req.getEstadoCarrera());

        Carrera updatedCarrera = carreraService.actualizar(id, req);

        return ResponseEntity.ok(assembler.toModel(updatedCarrera));
    }

    // Cambiado para soportar ambos formatos de salida en Swagger
    @DeleteMapping(value = "/{id}", produces = { MediaTypes.HAL_JSON_VALUE, "application/json" })
    public ResponseEntity<?> deleteCarrera(@PathVariable long id) {

        carreraService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
