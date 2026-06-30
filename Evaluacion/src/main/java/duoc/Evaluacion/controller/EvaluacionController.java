package duoc.Evaluacion.controller;

import duoc.Evaluacion.dto.EvaluacionRequest;
import duoc.Evaluacion.model.Evaluacion;
import duoc.Evaluacion.service.EvaluacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@Tag(name = "Evaluaciones", description = "Operaciones relacionadas con la gestión de evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping
    @Operation(summary = "Obtener todas las evaluaciones", description = "Obtiene una lista completa de las evaluaciones registradas")
    public ResponseEntity<List<Evaluacion>> listar() {
        List<Evaluacion> evaluaciones = evaluacionService.listarTodos();
        if (evaluaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(evaluaciones);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener evaluación por ID", description = "Busca y devuelve los detalles de una evaluación específica por su ID")
    public ResponseEntity<Evaluacion> buscarPorId(@PathVariable Long id) {
        Evaluacion evaluacion = evaluacionService.buscarPorId(id);
        return ResponseEntity.ok(evaluacion);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva evaluación", description = "Registra una nueva evaluación en el sistema")
    public ResponseEntity<Evaluacion> guardarEvaluacion(@Valid @RequestBody EvaluacionRequest request) {
        Evaluacion evaluacionGuardada = evaluacionService.crearDesdeRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluacionGuardada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar evaluación", description = "Actualiza los datos de una evaluación existente por su ID")
    public ResponseEntity<Evaluacion> actualizarEvaluacion(@PathVariable Long id, @Valid @RequestBody EvaluacionRequest request) {
        Evaluacion evaluacionActualizada = evaluacionService.actualizarEvaluacion(id, request);
        return ResponseEntity.ok(evaluacionActualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar evaluación", description = "Elimina permanentemente una evaluación del sistema")
    public ResponseEntity<Void> eliminarEvaluacion(@PathVariable Long id) {
        evaluacionService.eliminarEvaluacion(id);
        return ResponseEntity.noContent().build();
    }
}