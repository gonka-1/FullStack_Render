package duoc.Asistencia.controller;


import duoc.Asistencia.dto.AsistenciaRequest;
import duoc.Asistencia.model.Asistencia;
import duoc.Asistencia.service.AsistenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/asistencias")
public class AsistenciaController {


    @Autowired
    private AsistenciaService asistenciaService;

    @GetMapping
    public ResponseEntity<List<Asistencia>> listar() {
        List<Asistencia> asistencias = asistenciaService.listarTodos();
        if (asistencias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> buscarPorId(@PathVariable Integer id) {
        Asistencia asistencia = asistenciaService.buscarPorId(id);
        return ResponseEntity.ok(asistencia);
    }

    @PostMapping
    public ResponseEntity<Asistencia> guardar(@Valid @RequestBody AsistenciaRequest request) {
        Asistencia asistenciaGuardada = asistenciaService.crearDesdeRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(asistenciaGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> actualizar(@PathVariable Integer id, @Valid @RequestBody AsistenciaRequest request) {
        Asistencia asistenciaActualizada = asistenciaService.actualizarAsistencia(id, request);
        return ResponseEntity.ok(asistenciaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        asistenciaService.eliminarAsistencia(id);
        return ResponseEntity.noContent().build();
    }
}
