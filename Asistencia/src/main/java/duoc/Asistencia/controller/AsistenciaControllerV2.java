package duoc.Asistencia.controller;

import duoc.Asistencia.assemblers.AsistenciaModelAssembler;
import duoc.Asistencia.dto.AsistenciaRequest;
import duoc.Asistencia.model.Asistencia;
import duoc.Asistencia.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/asistencias")
public class AsistenciaControllerV2 {

    @Autowired
    private AsistenciaService asistenciaService;

    @Autowired
    private AsistenciaModelAssembler assembler;

    // GET ALL
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Asistencia>> getAllAsistencias() {
        List<EntityModel<Asistencia>> asistencias = asistenciaService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(asistencias,
                linkTo(methodOn(AsistenciaControllerV2.class).getAllAsistencias()).withSelfRel());
    }

    // GET BY ID
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Asistencia> getAsistenciaById(@PathVariable Integer id) {
        Asistencia asistencia = asistenciaService.buscarPorId(id);
        return assembler.toModel(asistencia);
    }

    // POST
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Asistencia>> createAsistencia(@RequestBody AsistenciaRequest request) {
        Asistencia newAsistencia = asistenciaService.crearDesdeRequest(request);
        return ResponseEntity
                .created(linkTo(methodOn(AsistenciaControllerV2.class).getAsistenciaById(newAsistencia.getId())).toUri())
                .body(assembler.toModel(newAsistencia));
    }

    // PUT
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Asistencia>> updateAsistencia(@PathVariable Integer id, @RequestBody AsistenciaRequest request) {
        Asistencia updatedAsistencia = asistenciaService.actualizarAsistencia(id, request);
        return ResponseEntity
                .ok(assembler.toModel(updatedAsistencia));
    }

    // DELETE
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteAsistencia(@PathVariable Integer id) {
        asistenciaService.eliminarAsistencia(id);
        return ResponseEntity.noContent().build();
    }
}