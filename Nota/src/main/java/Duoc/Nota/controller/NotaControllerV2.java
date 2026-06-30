package Duoc.Nota.controller;

import Duoc.Nota.assemblers.NotaModelAssembler;
import Duoc.Nota.models.Nota;
import Duoc.Nota.service.NotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/v2/notas")
@Tag(name = "Notas V2", description = "Operaciones con HATEOAS para la gestión de notas")
public class NotaControllerV2 {

    @Autowired
    private NotaService notaService;

    @Autowired
    private NotaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todas las notas", description = "Retorna lista de notas con enlaces HATEOAS")
    public CollectionModel<EntityModel<Nota>> listarTodasLasNotas() {
        List<EntityModel<Nota>> notas = notaService.listarTodasLasNotas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(notas,
                linkTo(methodOn(NotaControllerV2.class).listarTodasLasNotas()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener nota por ID", description = "Retorna una nota específica con enlaces HATEOAS")
    public EntityModel<Nota> buscarPorId(@PathVariable Long id) {
        Nota nota = notaService.buscarPorId(id);
        return assembler.toModel(nota);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear una nueva nota", description = "Registra una nota y retorna el recurso con enlaces HATEOAS")
    public ResponseEntity<EntityModel<Nota>> guardarNota(@RequestBody Nota nota) {
        Nota notaGuardada = notaService.crearDesdeRequest(nota);
        return ResponseEntity
                .created(linkTo(methodOn(NotaControllerV2.class).buscarPorId(notaGuardada.getId())).toUri())
                .body(assembler.toModel(notaGuardada));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar nota", description = "Actualiza una nota existente y retorna el recurso con enlaces HATEOAS")
    public ResponseEntity<EntityModel<Nota>> actualizarNota(@PathVariable Long id, @RequestBody Nota nota) {
        Nota notaActualizada = notaService.actualizarNota(id, nota);
        return ResponseEntity.ok(assembler.toModel(notaActualizada));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar nota", description = "Elimina permanentemente una nota del sistema")
    public ResponseEntity<?> eliminarNota(@PathVariable Long id) {
        notaService.eliminarNota(id);
        return ResponseEntity.noContent().build();
    }
}