package duoc.Profesor.controller;

import duoc.Profesor.assemblers.ProfesorModelAssembler;
import duoc.Profesor.dto.ProfesorRequest;
import duoc.Profesor.model.Profesor;
import duoc.Profesor.service.ProfesorService;
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
@RequestMapping("/api/v2/profesor")
public class ProfesorControllerV2 {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private ProfesorModelAssembler assembler;

    // Cambiado para soportar ambos formatos de salida en Swagger
    @GetMapping(produces = { MediaTypes.HAL_JSON_VALUE, "application/json" })
    public CollectionModel<EntityModel<Profesor>> getAllProfesores() {

        List<EntityModel<Profesor>> profesores = profesorService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(profesores,
                linkTo(methodOn(ProfesorControllerV2.class).getAllProfesores()).withSelfRel());
    }

    // Cambiado para soportar ambos formatos de salida en Swagger
    @GetMapping(value = "/{id}", produces = { MediaTypes.HAL_JSON_VALUE, "application/json" })
    public EntityModel<Profesor> getProfesorById(@PathVariable Integer id) {

        Profesor profesor = profesorService.buscarPorId(id);
        return assembler.toModel(profesor);
    }

    // Cambiado para soportar ambos formatos de salida en Swagger
    @PostMapping(produces = { MediaTypes.HAL_JSON_VALUE, "application/json" })
    public ResponseEntity<EntityModel<Profesor>> createProfesor(@RequestBody Profesor profesor) {

        ProfesorRequest req = new ProfesorRequest();
        req.setRun(profesor.getRun());
        req.setNombre(profesor.getNombre());
        req.setEspecialidad(profesor.getEspecialidad());

        Profesor newProfesor = profesorService.crearDesdeRequest(req);

        return ResponseEntity
                .created(linkTo(methodOn(ProfesorControllerV2.class)
                        .getProfesorById(newProfesor.getId())).toUri())
                .body(assembler.toModel(newProfesor));
    }

    // Cambiado para soportar ambos formatos de salida en Swagger
    @PutMapping(value = "/{id}", produces = { MediaTypes.HAL_JSON_VALUE, "application/json" })
    public ResponseEntity<EntityModel<Profesor>> updateProfesor(
            @PathVariable Integer id,
            @RequestBody Profesor profesor) {

        ProfesorRequest req = new ProfesorRequest();
        req.setRun(profesor.getRun());
        req.setNombre(profesor.getNombre());
        req.setEspecialidad(profesor.getEspecialidad());

        Profesor updatedProfesor = profesorService.actualizar(id, req);

        return ResponseEntity.ok(assembler.toModel(updatedProfesor));
    }

    // Cambiado para soportar ambos formatos de salida en Swagger
    @DeleteMapping(value = "/{id}", produces = { MediaTypes.HAL_JSON_VALUE, "application/json" })
    public ResponseEntity<?> deleteProfesor(@PathVariable Integer id) {

        profesorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}