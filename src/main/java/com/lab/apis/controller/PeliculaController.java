package com.lab.apis.controller;

import com.lab.apis.model.Pelicula;
import com.lab.apis.service.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {
    private final PeliculaService peliculaService;
    public PeliculaController(PeliculaService peliculaService) { this.peliculaService = peliculaService; }

    @GetMapping public ResponseEntity<List<Pelicula>> getAll() { return ResponseEntity.ok(peliculaService.findAll()); }
    @GetMapping("/{id}") public ResponseEntity<Pelicula> getById(@PathVariable Long id) { return ResponseEntity.ok(peliculaService.findById(id)); }
    @PostMapping public ResponseEntity<Pelicula> create(@RequestBody Pelicula p) { return ResponseEntity.status(HttpStatus.CREATED).body(peliculaService.save(p)); }
    @PutMapping("/{id}") public ResponseEntity<Pelicula> update(@PathVariable Long id, @RequestBody Pelicula p) { return ResponseEntity.ok(peliculaService.update(id, p)); }
    @PatchMapping("/{id}") public ResponseEntity<Pelicula> patch(@PathVariable Long id, @RequestBody Map<String, Object> u) { return ResponseEntity.ok(peliculaService.patch(id, u)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { peliculaService.deleteById(id); return ResponseEntity.noContent().build(); }
}