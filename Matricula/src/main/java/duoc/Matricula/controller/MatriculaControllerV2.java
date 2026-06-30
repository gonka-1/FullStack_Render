package duoc.Matricula.controller;

import duoc.Matricula.assemblers.MatriculaModelAssembler;
import duoc.Matricula.dto.MatriculaRequest;
import duoc.Matricula.model.Matricula;
import duoc.Matricula.service.MatriculaService;
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
@RequestMapping("/api/v2/matriculas")
public class MatriculaControllerV2 {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private MatriculaModelAssembler assembler;

    // GET ALL
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Matricula>> getAllMatriculas() {
        List<EntityModel<Matricula>> matriculas = matriculaService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(matriculas,
                linkTo(methodOn(MatriculaControllerV2.class).getAllMatriculas()).withSelfRel());
    }

    // GET BY ID
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Matricula> getMatriculaById(@PathVariable Long id) {
        Matricula matricula = matriculaService.buscarPorId(id);
        return assembler.toModel(matricula);
    }

    // POST
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Matricula>> createMatricula(@RequestBody MatriculaRequest request) {
        Matricula newMatricula = matriculaService.crearDesdeRequest(request);
        return ResponseEntity
                .created(linkTo(methodOn(MatriculaControllerV2.class).getMatriculaById(newMatricula.getId())).toUri())
                .body(assembler.toModel(newMatricula));
    }

    // PUT
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Matricula>> updateMatricula(@PathVariable Long id, @RequestBody MatriculaRequest request) {
        Matricula updatedMatricula = matriculaService.actualizarMatricula(id, request);
        return ResponseEntity
                .ok(assembler.toModel(updatedMatricula));
    }

    // DELETE
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteMatricula(@PathVariable Long id) {
        matriculaService.eliminarMatricula(id);
        return ResponseEntity.noContent().build();
    }
}