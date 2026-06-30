package duoc.Seccion.controller;

import duoc.Seccion.assemblers.SeccionModelAssembler;
import duoc.Seccion.dto.SeccionRequest;
import duoc.Seccion.model.Seccion;
import duoc.Seccion.service.SeccionService;
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
@RequestMapping("/api/v2/seccion")
@Tag(name = "Secciones V2", description = "Operaciones con HATEOAS para la gestión de secciones")
public class SeccionControllerV2 {

    @Autowired
    private SeccionService seccionService;

    @Autowired
    private SeccionModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todas las secciones", description = "Retorna lista de secciones con enlaces HATEOAS")
    public CollectionModel<EntityModel<Seccion>> listar() {
        List<EntityModel<Seccion>> secciones = seccionService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(secciones,
                linkTo(methodOn(SeccionControllerV2.class).listar()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener sección por ID", description = "Retorna una sección específica con enlaces HATEOAS")
    public EntityModel<Seccion> buscarPorId(@PathVariable Long id) {
        Seccion seccion = seccionService.buscarPorId(id);
        return assembler.toModel(seccion);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear una nueva sección", description = "Registra una sección y retorna el recurso con enlaces HATEOAS")
    public ResponseEntity<EntityModel<Seccion>> guardarSeccion(@Valid @RequestBody SeccionRequest request) {
        Seccion seccionGuardada = seccionService.crearDesdeRequest(request);
        return ResponseEntity
                .created(linkTo(methodOn(SeccionControllerV2.class).buscarPorId(seccionGuardada.getIdSeccion())).toUri())
                .body(assembler.toModel(seccionGuardada));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar sección", description = "Actualiza una sección existente y retorna el recurso con enlaces HATEOAS")
    public ResponseEntity<EntityModel<Seccion>> actualizarSeccion(@PathVariable Long id, @Valid @RequestBody SeccionRequest request) {
        Seccion seccionActualizada = seccionService.actualizarSeccion(id, request);
        return ResponseEntity.ok(assembler.toModel(seccionActualizada));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar sección", description = "Elimina permanentemente una sección del sistema")
    public ResponseEntity<?> eliminarSeccion(@PathVariable Long id) {
        seccionService.eliminarSeccion(id);
        return ResponseEntity.noContent().build();
    }
}