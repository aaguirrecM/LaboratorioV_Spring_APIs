package com.lab.apis.controller;

import com.lab.apis.model.Libro;
import com.lab.apis.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
    private final LibroService libroService;
    public LibroController(LibroService libroService) { this.libroService = libroService; }

    @GetMapping public ResponseEntity<List<Libro>> getAll() { return ResponseEntity.ok(libroService.findAll()); }
    @GetMapping("/{id}") public ResponseEntity<Libro> getById(@PathVariable Long id) { return ResponseEntity.ok(libroService.findById(id)); }
    @PostMapping public ResponseEntity<Libro> create(@RequestBody Libro l) { return ResponseEntity.status(HttpStatus.CREATED).body(libroService.save(l)); }
    @PutMapping("/{id}") public ResponseEntity<Libro> update(@PathVariable Long id, @RequestBody Libro l) { return ResponseEntity.ok(libroService.update(id, l)); }
    @PatchMapping("/{id}") public ResponseEntity<Libro> patch(@PathVariable Long id, @RequestBody Map<String, Object> u) { return ResponseEntity.ok(libroService.patch(id, u)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { libroService.deleteById(id); return ResponseEntity.noContent().build(); }
}