package duoc.Evaluacion.controller;

import duoc.Evaluacion.assemblers.EvaluacionModelAssembler;
import duoc.Evaluacion.dto.EvaluacionRequest;
import duoc.Evaluacion.model.Evaluacion;
import duoc.Evaluacion.service.EvaluacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/evaluacion")
@Tag(name = "Evaluaciones V2", description = "Operaciones con HATEOAS para la gestión de evaluaciones")
public class EvaluacionControllerV2 {

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    private EvaluacionModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todas las evaluaciones", description = "Retorna lista de evaluaciones con enlaces HATEOAS")
    public CollectionModel<EntityModel<Evaluacion>> listar() {
        List<EntityModel<Evaluacion>> evaluaciones = evaluacionService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(evaluaciones,
                linkTo(methodOn(EvaluacionControllerV2.class).listar()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener evaluación por ID", description = "Retorna una evaluación específica con enlaces HATEOAS")
    public EntityModel<Evaluacion> buscarPorId(@PathVariable Long id) {
        Evaluacion evaluacion = evaluacionService.buscarPorId(id);
        return assembler.toModel(evaluacion);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear una nueva evaluación", description = "Registra una evaluación y retorna el recurso con enlaces HATEOAS")
    public ResponseEntity<EntityModel<Evaluacion>> guardarEvaluacion(@Valid @RequestBody EvaluacionRequest request) {
        Evaluacion evaluacionGuardada = evaluacionService.crearDesdeRequest(request);
        return ResponseEntity
                .created(linkTo(methodOn(EvaluacionControllerV2.class).buscarPorId(evaluacionGuardada.getIdEvaluacion())).toUri())
                .body(assembler.toModel(evaluacionGuardada));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar evaluación", description = "Actualiza una evaluación existente y retorna el recurso con enlaces HATEOAS")
    public ResponseEntity<EntityModel<Evaluacion>> actualizarEvaluacion(@PathVariable Long id, @Valid @RequestBody EvaluacionRequest request) {
        Evaluacion evaluacionActualizada = evaluacionService.actualizarEvaluacion(id, request);
        return ResponseEntity.ok(assembler.toModel(evaluacionActualizada));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar evaluación", description = "Elimina permanentemente una evaluación del sistema")
    public ResponseEntity<?> eliminarEvaluacion(@PathVariable Long id) {
        evaluacionService.eliminarEvaluacion(id);
        return ResponseEntity.noContent().build();
    }
}