package duoc.Sala.controller;

import duoc.Sala.dto.SalaRequest;
import duoc.Sala.model.Sala;
import duoc.Sala.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salas")
@Tag(name = "Salas", description = "Operaciones relacionadas con la gestión y registro de salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping
    @Operation(summary = "Obtener todas las salas", description = "Obtiene una lista completa de las salas registradas en el sistema")
    public ResponseEntity<List<Sala>> listar() {
        List<Sala> salas = salaService.listarTodas();
        if (salas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(salas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener sala por ID", description = "Busca y devuelve los detalles de una sala específica utilizando su ID")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id) {
        Sala sala = salaService.buscarPorId(id);
        return ResponseEntity.ok(sala);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva sala", description = "Registra una nueva sala en el sistema")
    public ResponseEntity<Sala> guardarSala(@Valid @RequestBody SalaRequest request) {
        Sala salaGuardada = salaService.crearDesdeRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(salaGuardada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar sala", description = "Actualiza los datos de una sala existente buscando por su ID")
    public ResponseEntity<Sala> actualizarSala(@PathVariable Long id, @Valid @RequestBody SalaRequest request) {
        Sala salaActualizada = salaService.actualizarSala(id, request);
        return ResponseEntity.ok(salaActualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar sala", description = "Elimina permanentemente una sala del sistema")
    public ResponseEntity<Void> eliminarSala(@PathVariable Long id) {
        salaService.eliminarSala(id);
        return ResponseEntity.noContent().build();
    }
}