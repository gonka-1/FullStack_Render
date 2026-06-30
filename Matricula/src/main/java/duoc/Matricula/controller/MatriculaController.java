package duoc.Matricula.controller;

import duoc.Matricula.dto.MatriculaRequest;
import duoc.Matricula.model.Matricula;
import duoc.Matricula.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/matriculas")
public class MatriculaController {



    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<Matricula>> listar() {
        List<Matricula> matriculas = matriculaService.listarTodos();
        if (matriculas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> buscarPorId(@PathVariable Long id) {
        Matricula matricula = matriculaService.buscarPorId(id);
        return ResponseEntity.ok(matricula);
    }

    @PostMapping
    public ResponseEntity<Matricula> guardar(@Valid @RequestBody MatriculaRequest request) {
        Matricula matriculaGuardada = matriculaService.crearDesdeRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> actualizar(@PathVariable Long id, @Valid @RequestBody MatriculaRequest request) {
        Matricula matriculaActualizada = matriculaService.actualizarMatricula(id, request);
        return ResponseEntity.ok(matriculaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        matriculaService.eliminarMatricula(id);
        return ResponseEntity.noContent().build();
    }
}
