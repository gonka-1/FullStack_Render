package Duoc.Curso.controller;


import Duoc.Curso.dto.CursoRequest;
import Duoc.Curso.model.Curso;
import Duoc.Curso.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        List<Curso> cursos = cursoService.listarTodos();
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Curso> buscarPorId(@PathVariable Long id) {
        Curso curso = cursoService.buscarPorId(id);
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> guardarCurso(@Valid @RequestBody CursoRequest request) {
        Curso cursoGuardado = cursoService.crearDesdeRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Long id, @Valid @RequestBody CursoRequest request) {
        Curso cursoActualizado = cursoService.actualizarCurso(id, request);
        return ResponseEntity.ok(cursoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Curso> eliminarCurso(@PathVariable Long id){
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }

    




}
