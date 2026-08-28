package com.lab.apis.controller;

import com.lab.apis.model.Tarea;
import com.lab.apis.service.TareaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    private final TareaService tareaService;
    public TareaController(TareaService tareaService) { this.tareaService = tareaService; }

    @GetMapping public ResponseEntity<List<Tarea>> getAll() { return ResponseEntity.ok(tareaService.findAll()); }
    @GetMapping("/{id}") public ResponseEntity<Tarea> getById(@PathVariable Long id) { return ResponseEntity.ok(tareaService.findById(id)); }
    @PostMapping public ResponseEntity<Tarea> create(@RequestBody Tarea t) { return ResponseEntity.status(HttpStatus.CREATED).body(tareaService.save(t)); }
    @PutMapping("/{id}") public ResponseEntity<Tarea> update(@PathVariable Long id, @RequestBody Tarea t) { return ResponseEntity.ok(tareaService.update(id, t)); }
    @PatchMapping("/{id}") public ResponseEntity<Tarea> patch(@PathVariable Long id, @RequestBody Map<String, Object> u) { return ResponseEntity.ok(tareaService.patch(id, u)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { tareaService.deleteById(id); return ResponseEntity.noContent().build(); }
}