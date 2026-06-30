
package Duoc.Curso.controller;

import Duoc.Curso.assemblers.CursoModelAssembler;
import Duoc.Curso.dto.CursoRequest;
import Duoc.Curso.model.Curso;
import Duoc.Curso.service.CursoService;
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
@RequestMapping("/api/v2/cursos")
public class CursoControllerV2 {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoModelAssembler assembler;

    // GET ALL
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Curso>> getAllCursos() {
        List<EntityModel<Curso>> cursos = cursoService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(cursos,
                linkTo(methodOn(CursoControllerV2.class).getAllCursos()).withSelfRel());
    }

    // GET BY ID
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Curso> getCursoById(@PathVariable Long id) {
        Curso curso = cursoService.buscarPorId(id);
        return assembler.toModel(curso);
    }

    // POST
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Curso>> createCurso(@RequestBody CursoRequest request) {
        Curso newCurso = cursoService.crearDesdeRequest(request);
        return ResponseEntity
                .created(linkTo(methodOn(CursoControllerV2.class).getCursoById(newCurso.getId())).toUri())
                .body(assembler.toModel(newCurso));
    }

    // PUT
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Curso>> updateCurso(@PathVariable Long id, @RequestBody CursoRequest request) {
        Curso updatedCurso = cursoService.actualizarCurso(id, request);
        return ResponseEntity
                .ok(assembler.toModel(updatedCurso));
    }

    // DELETE
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}