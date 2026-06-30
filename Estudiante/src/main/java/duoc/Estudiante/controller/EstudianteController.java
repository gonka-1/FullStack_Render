package duoc.Estudiante.controller;

import duoc.Estudiante.dto.EstudianteRequest;
import duoc.Estudiante.model.Estudiante;
import duoc.Estudiante.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estudiantes")
public class EstudianteController {


    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<Estudiante>> listar() {
        List<Estudiante> estudiantes = estudianteService.listarTodos();
        if (estudiantes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscarPorId(@PathVariable Integer id) {
        Estudiante estudiante = estudianteService.buscarPorId(id);
        return ResponseEntity.ok(estudiante);
    }

    @PostMapping
    public ResponseEntity<Estudiante> guardar(@Valid @RequestBody EstudianteRequest request) {
        Estudiante estudianteGuardado = estudianteService.crearDesdeRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteGuardado);
    }

    @PutMapping("/{id}")
        public ResponseEntity<Estudiante> actualizar(@PathVariable Integer id, @Valid @RequestBody EstudianteRequest request) {
        Estudiante estudianteActualizado = estudianteService.actualizar(id, request);
        return ResponseEntity.ok(estudianteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
