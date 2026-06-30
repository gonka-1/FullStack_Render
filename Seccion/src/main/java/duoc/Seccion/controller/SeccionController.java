package duoc.Seccion.controller;

import duoc.Seccion.dto.SeccionRequest;
import duoc.Seccion.model.Seccion;
import duoc.Seccion.service.SeccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/secciones")
@Tag(name = "Secciones", description = "Operaciones relacionadas con la gestión y registro de secciones de cursos")
public class SeccionController {

    @Autowired
    private SeccionService seccionService;

    @GetMapping
    @Operation(summary = "Obtener todas las secciones", description = "Obtiene una lista completa de las secciones registradas")
    public ResponseEntity<List<Seccion>> listar() {
        List<Seccion> secciones = seccionService.listarTodos();
        if (secciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(secciones);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener sección por ID", description = "Busca y devuelve los detalles de una sección específica utilizando su ID")
    public ResponseEntity<Seccion> buscarPorId(@PathVariable Long id) {
        Seccion seccion = seccionService.buscarPorId(id);
        return ResponseEntity.ok(seccion);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva sección", description = "Registra una nueva sección en el sistema")
    public ResponseEntity<Seccion> guardarSeccion(@Valid @RequestBody SeccionRequest request) {
        Seccion seccionGuardada = seccionService.crearDesdeRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(seccionGuardada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar sección", description = "Actualiza los datos de una sección existente buscando por su ID")
    public ResponseEntity<Seccion> actualizarSeccion(@PathVariable Long id, @Valid @RequestBody SeccionRequest request) {
        Seccion seccionActualizada = seccionService.actualizarSeccion(id, request);
        return ResponseEntity.ok(seccionActualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar sección", description = "Elimina permanentemente una sección del sistema")
    public ResponseEntity<Void> eliminarSeccion(@PathVariable Long id) {
        seccionService.eliminarSeccion(id);
        return ResponseEntity.noContent().build();
    }
}