package duoc.Sala.controller;

import duoc.Sala.assemblers.SalaModelAssembler;
import duoc.Sala.dto.SalaRequest;
import duoc.Sala.model.Sala;
import duoc.Sala.service.SalaService;
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
@RequestMapping("/api/v2/sala")
@Tag(name = "Salas V2", description = "Operaciones con HATEOAS para la gestión de salas")
public class SalaControllerV2 {

    @Autowired
    private SalaService salaService;

    @Autowired
    private SalaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todas las salas", description = "Retorna lista de salas con enlaces HATEOAS")
    public CollectionModel<EntityModel<Sala>> listar() {
        List<EntityModel<Sala>> salas = salaService.listarTodas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(salas,
                linkTo(methodOn(SalaControllerV2.class).listar()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener sala por ID", description = "Retorna una sala específica con enlaces HATEOAS")
    public EntityModel<Sala> buscarPorId(@PathVariable Long id) {
        Sala sala = salaService.buscarPorId(id);
        return assembler.toModel(sala);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear una nueva sala", description = "Registra una sala y retorna el recurso con enlaces HATEOAS")
    public ResponseEntity<EntityModel<Sala>> guardarSala(@Valid @RequestBody SalaRequest request) {
        Sala salaGuardada = salaService.crearDesdeRequest(request);
        return ResponseEntity
                .created(linkTo(methodOn(SalaControllerV2.class).buscarPorId(salaGuardada.getIdSala())).toUri())
                .body(assembler.toModel(salaGuardada));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar sala", description = "Actualiza una sala existente y retorna el recurso con enlaces HATEOAS")
    public ResponseEntity<EntityModel<Sala>> actualizarSala(@PathVariable Long id, @Valid @RequestBody SalaRequest request) {
        Sala salaActualizada = salaService.actualizarSala(id, request);
        return ResponseEntity.ok(assembler.toModel(salaActualizada));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar sala", description = "Elimina permanentemente una sala del sistema")
    public ResponseEntity<?> eliminarSala(@PathVariable Long id) {
        salaService.eliminarSala(id);
        return ResponseEntity.noContent().build();
    }
}