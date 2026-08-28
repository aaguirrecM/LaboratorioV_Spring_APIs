package com.lab.apis.service;

import com.lab.apis.exception.ResourceNotFoundException;
import com.lab.apis.model.Pedido;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PedidoService {
    private final List<Pedido> pedidos = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public PedidoService() {
        save(new Pedido(null, "Carlos Gómez", "Laptop Pro 15", 1, 1299.99, "PENDIENTE"));
        save(new Pedido(null, "María López", "Teclado Mecánico", 2, 179.80, "ENVIADO"));
        save(new Pedido(null, "Juan Martínez", "Mouse Inalámbrico", 1, 25.50, "ENTREGADO"));
        save(new Pedido(null, "Ana Rodríguez", "Monitor 27 Pulgadas", 1, 349.00, "CANCELADO"));
        save(new Pedido(null, "Luis Fernández", "Auriculares Bluetooth", 3, 330.00, "ENVIADO"));
    }

    public List<Pedido> findAll() { return new ArrayList<>(pedidos); }
    public Pedido findById(Long id) {
        return pedidos.stream().filter(p -> p.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con ID: " + id));
    }
    public Pedido save(Pedido p) {
        if (p.getId() == null) p.setId(idGenerator.getAndIncrement());
        pedidos.add(p);
        return p;
    }
    public Pedido update(Long id, Pedido act) {
        Pedido p = findById(id);
        p.setCliente(act.getCliente());
        p.setProducto(act.getProducto());
        p.setCantidad(act.getCantidad());
        p.setTotal(act.getTotal());
        p.setEstado(act.getEstado());
        return p;
    }
    public Pedido patch(Long id, Map<String, Object> updates) {
        Pedido p = findById(id);
        updates.forEach((key, value) -> {
            switch (key) {
                case "cliente" -> p.setCliente((String) value);
                case "producto" -> p.setProducto((String) value);
                case "cantidad" -> p.setCantidad(((Number) value).intValue());
                case "total" -> p.setTotal(((Number) value).doubleValue());
                case "estado" -> p.setEstado((String) value);
            }
        });
        return p;
    }
    public void deleteById(Long id) { pedidos.remove(findById(id)); }
}