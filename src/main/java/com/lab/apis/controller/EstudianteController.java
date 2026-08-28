package com.lab.apis.controller;

import com.lab.apis.model.Estudiante;
import com.lab.apis.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {
    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping public ResponseEntity<List<Estudiante>> getAll() { return ResponseEntity.ok(estudianteService.findAll()); }
    @GetMapping("/{id}") public ResponseEntity<Estudiante> getById(@PathVariable Long id) { return ResponseEntity.ok(estudianteService.findById(id)); }
    @PostMapping public ResponseEntity<Estudiante> create(@RequestBody Estudiante e) { return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.save(e)); }
    @PutMapping("/{id}") public ResponseEntity<Estudiante> update(@PathVariable Long id, @RequestBody Estudiante e) { return ResponseEntity.ok(estudianteService.update(id, e)); }
    @PatchMapping("/{id}") public ResponseEntity<Estudiante> patch(@PathVariable Long id, @RequestBody Map<String, Object> u) { return ResponseEntity.ok(estudianteService.patch(id, u)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { estudianteService.deleteById(id); return ResponseEntity.noContent().build(); }
}