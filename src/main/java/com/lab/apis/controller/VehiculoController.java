package com.lab.apis.controller;

import com.lab.apis.model.Vehiculo;
import com.lab.apis.service.VehiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    private final VehiculoService vehiculoService;
    public VehiculoController(VehiculoService vehiculoService) { this.vehiculoService = vehiculoService; }

    @GetMapping public ResponseEntity<List<Vehiculo>> getAll() { return ResponseEntity.ok(vehiculoService.findAll()); }
    @GetMapping("/{id}") public ResponseEntity<Vehiculo> getById(@PathVariable Long id) { return ResponseEntity.ok(vehiculoService.findById(id)); }
    @PostMapping public ResponseEntity<Vehiculo> create(@RequestBody Vehiculo v) { return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.save(v)); }
    @PutMapping("/{id}") public ResponseEntity<Vehiculo> update(@PathVariable Long id, @RequestBody Vehiculo v) { return ResponseEntity.ok(vehiculoService.update(id, v)); }
    @PatchMapping("/{id}") public ResponseEntity<Vehiculo> patch(@PathVariable Long id, @RequestBody Map<String, Object> u) { return ResponseEntity.ok(vehiculoService.patch(id, u)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { vehiculoService.deleteById(id); return ResponseEntity.noContent().build(); }
}