package com.lab.apis.service;

import com.lab.apis.exception.ResourceNotFoundException;
import com.lab.apis.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductoService {
    private final List<Producto> productos = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ProductoService() {
        // Al menos 5 datos iniciales
        save(new Producto(null, "Laptop Pro 15", 1299.99, "Tecnología"));
        save(new Producto(null, "Teclado Mecánico", 89.90, "Accesorios"));
        save(new Producto(null, "Mouse Inalámbrico", 25.50, "Accesorios"));
        save(new Producto(null, "Monitor 27 Pulgadas", 349.00, "Monitores"));
        save(new Producto(null, "Auriculares Bluetooth", 110.00, "Audio"));
    }

    public List<Producto> findAll() {
        return new ArrayList<>(productos);
    }

    public Producto findById(Long id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
    }

    public Producto save(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(idGenerator.getAndIncrement());
        }
        productos.add(producto);
        return producto;
    }

    public Producto update(Long id, Producto actualizado) {
        Producto existente = findById(id);
        existente.setNombre(actualizado.getNombre());
        existente.setPrecio(actualizado.getPrecio());
        existente.setCategoria(actualizado.getCategoria());
        return existente;
    }

    public Producto patch(Long id, Map<String, Object> updates) {
        Producto existente = findById(id);
        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre" -> existente.setNombre((String) value);
                case "precio" -> existente.setPrecio(((Number) value).doubleValue());
                case "categoria" -> existente.setCategoria((String) value);
            }
        });
        return existente;
    }

    public void deleteById(Long id) {
        Producto producto = findById(id);
        productos.remove(producto);
    }
}