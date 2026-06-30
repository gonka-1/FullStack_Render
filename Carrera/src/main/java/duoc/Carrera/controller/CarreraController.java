package duoc.Carrera.controller;


import duoc.Carrera.dto.CarreraRequest;
import duoc.Carrera.model.Carrera;
import duoc.Carrera.service.CarreraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public ResponseEntity<List<Carrera>> listar() {
        List<Carrera> carreras = carreraService.listarTodos();
        if (carreras.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carreras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrera> buscarPorId(@PathVariable Long id) {
        Carrera carrera = carreraService.buscarPorId(id);
        return ResponseEntity.ok(carrera);
    }

    @PostMapping
    public ResponseEntity<Carrera> guardar(@Valid @RequestBody CarreraRequest request) {
        Carrera carreraGuardada = carreraService.crearDesdeRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(carreraGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrera> actualizar(@PathVariable Long id, @Valid @RequestBody CarreraRequest request) {
        Carrera carreraActualizada = carreraService.actualizar(id, request);
        return ResponseEntity.ok(carreraActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        carreraService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
