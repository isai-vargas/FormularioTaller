package org.example.Controllers;
import org.example.Models.OrdenTrabajo;
import org.example.Services.OrdenTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/ordenes-trabajo")
public class OrdenTrabajoController {

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    // Crear o actualizar una orden de trabajo
    @PostMapping
    public ResponseEntity<String> saveOrdenTrabajo(@RequestBody OrdenTrabajo ordenTrabajo) {
        try {
            String result = ordenTrabajoService.saveOrdenTrabajo(ordenTrabajo);
            return ResponseEntity.ok("Orden de trabajo guardada/actualizada con éxito. Fecha de actualización: " + result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al guardar la orden de trabajo: " + e.getMessage());
        }
    }

    // Obtener una orden de trabajo por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenTrabajo> getOrdenTrabajoById(@PathVariable String id) {
        try {
            OrdenTrabajo ordenTrabajo = ordenTrabajoService.getOrdenTrabajoById(id);
            if (ordenTrabajo != null) {
                return ResponseEntity.ok(ordenTrabajo);
            } else {
                return ResponseEntity.status(404).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Obtener todas las ordenes de trabajo
    @GetMapping
    public ResponseEntity<List<OrdenTrabajo>> getAllOrdenesTrabajo() {
        try {
            List<OrdenTrabajo> ordenesTrabajo = ordenTrabajoService.getAllOrdenesTrabajo().stream()
                    .map(documentSnapshot -> documentSnapshot.toObject(OrdenTrabajo.class))
                    .toList();
            return ResponseEntity.ok(ordenesTrabajo);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Eliminar una orden de trabajo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrdenTrabajo(@PathVariable String id) {
        try {
            String result = ordenTrabajoService.deleteOrdenTrabajo(id);
            return ResponseEntity.ok("Orden de trabajo eliminada con éxito. Fecha de eliminación: " + result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar la orden de trabajo: " + e.getMessage());
        }
    }
}