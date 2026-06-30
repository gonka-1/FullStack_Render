package duoc.Profesor.controller;

import duoc.Profesor.dto.ProfesorRequest;
import duoc.Profesor.model.Profesor;
import duoc.Profesor.service.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profesores")
public class ProfesorController {



    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<Profesor>> listar() {
        List<Profesor> estudiantes = profesorService.listarTodos();
        if (estudiantes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> buscarPorId(@PathVariable Integer id) {
        Profesor profesor = profesorService.buscarPorId(id);
        return ResponseEntity.ok(profesor);
    }

    @PostMapping
    public ResponseEntity<Profesor> guardar(@Valid @RequestBody ProfesorRequest request) {
        Profesor profesorGuardado = profesorService.crearDesdeRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(profesorGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> actualizar(@PathVariable Integer id, @Valid @RequestBody ProfesorRequest request) {
        Profesor profesorActualizado = profesorService.actualizar(id, request);
        return ResponseEntity.ok(profesorActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        profesorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
