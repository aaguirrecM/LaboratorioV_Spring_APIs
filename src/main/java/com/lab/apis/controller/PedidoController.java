package com.lab.apis.controller;

import com.lab.apis.model.Pedido;
import com.lab.apis.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;
    public PedidoController(PedidoService pedidoService) { this.pedidoService = pedidoService; }

    @GetMapping public ResponseEntity<List<Pedido>> getAll() { return ResponseEntity.ok(pedidoService.findAll()); }
    @GetMapping("/{id}") public ResponseEntity<Pedido> getById(@PathVariable Long id) { return ResponseEntity.ok(pedidoService.findById(id)); }
    @PostMapping public ResponseEntity<Pedido> create(@RequestBody Pedido p) { return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(p)); }
    @PutMapping("/{id}") public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody Pedido p) { return ResponseEntity.ok(pedidoService.update(id, p)); }
    @PatchMapping("/{id}") public ResponseEntity<Pedido> patch(@PathVariable Long id, @RequestBody Map<String, Object> u) { return ResponseEntity.ok(pedidoService.patch(id, u)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) { pedidoService.deleteById(id); return ResponseEntity.noContent().build(); }
}