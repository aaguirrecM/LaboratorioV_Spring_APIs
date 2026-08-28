package com.lab.apis.controller;

import com.lab.apis.model.Curso;
import com.lab.apis.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    private final CursoService cursoService;
    public CursoController(CursoService cursoService) { this.cursoService = cursoService; }

    @GetMapping public ResponseEntity<List<Curso>> getAll() { return ResponseEntity.ok(cursoService.findAll()); }
    @GetMapping("/{id}") public ResponseEntity<Curso> getById(@PathVariable Long id) { return ResponseEntity.ok(cursoService.findById(id)); }
    @PostMapping public ResponseEntity<Curso> create(@RequestBody Curso c) { return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(c)); }
    @PutMapping("/{id}") public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody Curso c) { return ResponseEntity.ok(cursoService.update(id, c)); }
    @PatchMapping("/{id}") public ResponseEntity<Curso> patch(@PathVariable Long id, @RequestBody Map<String, Object> u) { return ResponseEntity.ok(cursoService.patch(id, u)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { cursoService.deleteById(id); return ResponseEntity.noContent().build(); }
}