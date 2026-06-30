package duoc.Estudiante.controller;

import duoc.Estudiante.assemblers.EstudianteModelAssembler;
import duoc.Estudiante.dto.EstudianteRequest;
import duoc.Estudiante.model.Estudiante;
import duoc.Estudiante.service.EstudianteService;
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
@RequestMapping("/api/v2/estudiante")
public class EstudianteControllerV2 {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private EstudianteModelAssembler assembler;

    // Cambiado para soportar ambos formatos de salida en Swagger
    @GetMapping(produces = { MediaTypes.HAL_JSON_VALUE, "application/json" })
    public CollectionModel<EntityModel<Estudiante>> getAllEstudaiantes() {

        List<EntityModel<Estudiante>> estudiantes = estudianteService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(estudiantes,
                linkTo(methodOn(EstudianteControllerV2.class).getAllEstudaiantes()).withSelfRel());
    }

    // Cambiado para soportar ambos formatos de salida en Swagger
    @GetMapping(value = "/{id}", produces = { MediaTypes.HAL_JSON_VALUE, "application/json" })
    public EntityModel<Estudiante> getEstudiantesById(@PathVariable Integer id) {

        Estudiante estudiante = estudianteService.buscarPorId(id);
        return assembler.toModel(estudiante);
    }

    // Cambiado para soportar ambos formatos de salida en Swagger
    @PostMapping(produces = { MediaTypes.HAL_JSON_VALUE, "application/json" })
    public ResponseEntity<EntityModel<Estudiante>> createProfesor(@RequestBody Estudiante estudiante) {

        EstudianteRequest req = new EstudianteRequest();
        req.setRun(estudiante.getRun());
        req.setNombre(estudiante.getNombre());
        req.setEmail(estudiante.getEmail());
        req.setCarreraId(estudiante.getCarreraId());

        Estudiante newEstudiante = estudianteService.crearDesdeRequest(req);

        return ResponseEntity
                .created(linkTo(methodOn(EstudianteControllerV2.class)
                        .getEstudiantesById(newEstudiante.getId())).toUri())
                .body(assembler.toModel(newEstudiante));
    }

    // Cambiado para soportar ambos formatos de salida en Swagger
    @PutMapping(value = "/{id}", produces = { MediaTypes.HAL_JSON_VALUE, "application/json" })
    public ResponseEntity<EntityModel<Estudiante>> updateEstuadiante(
            @PathVariable Integer id,
            @RequestBody Estudiante estudiante) {

        EstudianteRequest req = new EstudianteRequest();
        req.setRun(estudiante.getRun());
        req.setNombre(estudiante.getNombre());
        req.setEmail(estudiante.getEmail());
        req.setCarreraId(estudiante.getCarreraId());


        Estudiante updatedEstudiante = estudianteService.actualizar(id, req);

        return ResponseEntity.ok(assembler.toModel(updatedEstudiante));
    }

    // Cambiado para soportar ambos formatos de salida en Swagger
    @DeleteMapping(value = "/{id}", produces = { MediaTypes.HAL_JSON_VALUE, "application/json" })
    public ResponseEntity<?> deleteEstudiante(@PathVariable Integer id) {

        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
