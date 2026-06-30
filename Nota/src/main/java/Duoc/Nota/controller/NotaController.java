package Duoc.Nota.controller;

import Duoc.Nota.models.Nota;
import Duoc.Nota.service.NotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notas")
@Tag(name = "Notas", description = "Operaciones relacionadas con la gestión de calificaciones y notas")
public class NotaController {

    private static final Logger log = LoggerFactory.getLogger(NotaController.class);

    @Autowired
    private NotaService notaService;

    @GetMapping
    @Operation(summary = "Obtener todas las notas", description = "Obtiene una lista completa de las calificaciones registradas en el sistema")
    public ResponseEntity<List<Nota>> listarTodasLasNotas() {
        List<Nota> notas = notaService.listarTodasLasNotas();
        if (notas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener nota por ID", description = "Busca y devuelve los detalles de una nota específica utilizando su ID")
    public ResponseEntity<Nota> buscarPorId (@PathVariable Long id) {
        Nota nota = notaService.buscarPorId(id);
        return ResponseEntity.ok(nota);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva nota", description = "Registra una nueva calificación en el sistema asociada a una matrícula")
    public ResponseEntity<Nota> guardarNota(@RequestBody Nota nota){
        Nota notaGuardada = notaService.crearDesdeRequest(nota);
        return ResponseEntity.status(HttpStatus.CREATED).body(notaGuardada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar nota", description = "Actualiza los datos de una calificación existente buscando por su ID")
    public ResponseEntity<Nota> actualizaNota(@PathVariable Long id,  @RequestBody Nota nota){
        Nota notaActualizada = notaService.actualizarNota(id, nota);
        return ResponseEntity.ok(notaActualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar nota", description = "Elimina permanentemente una calificación del sistema")
    public ResponseEntity<Nota> eliminarNota(@PathVariable Long id){
        notaService.eliminarNota(id);
        return ResponseEntity.noContent().build();
    }
}