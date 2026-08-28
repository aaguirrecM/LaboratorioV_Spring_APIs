package com.lab.apis.controller;

import com.lab.apis.model.Empleado;
import com.lab.apis.service.EmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    private final EmpleadoService empleadoService;
    public EmpleadoController(EmpleadoService empleadoService) { this.empleadoService = empleadoService; }

    @GetMapping public ResponseEntity<List<Empleado>> getAll() { return ResponseEntity.ok(empleadoService.findAll()); }
    @GetMapping("/{id}") public ResponseEntity<Empleado> getById(@PathVariable Long id) { return ResponseEntity.ok(empleadoService.findById(id)); }
    @PostMapping public ResponseEntity<Empleado> create(@RequestBody Empleado e) { return ResponseEntity.status(HttpStatus.CREATED).body(empleadoService.save(e)); }
    @PutMapping("/{id}") public ResponseEntity<Empleado> update(@PathVariable Long id, @RequestBody Empleado e) { return ResponseEntity.ok(empleadoService.update(id, e)); }
    @PatchMapping("/{id}") public ResponseEntity<Empleado> patch(@PathVariable Long id, @RequestBody Map<String, Object> u) { return ResponseEntity.ok(empleadoService.patch(id, u)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { empleadoService.deleteById(id); return ResponseEntity.noContent().build(); }
}