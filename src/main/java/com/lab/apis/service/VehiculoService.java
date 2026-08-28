package com.lab.apis.service;

import com.lab.apis.exception.ResourceNotFoundException;
import com.lab.apis.model.Vehiculo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class VehiculoService {
    private final List<Vehiculo> vehiculos = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public VehiculoService() {
        save(new Vehiculo(null, "Toyota", "Corolla", 2022, 22000.00));
        save(new Vehiculo(null, "Honda", "Civic", 2023, 25000.00));
        save(new Vehiculo(null, "Ford", "Mustang", 2021, 45000.00));
        save(new Vehiculo(null, "Chevrolet", "Onix", 2020, 14000.00));
        save(new Vehiculo(null, "Mazda", "CX-5", 2024, 32000.00));
    }

    public List<Vehiculo> findAll() { return new ArrayList<>(vehiculos); }
    public Vehiculo findById(Long id) {
        return vehiculos.stream().filter(v -> v.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo no encontrado con ID: " + id));
    }
    public Vehiculo save(Vehiculo v) {
        if (v.getId() == null) v.setId(idGenerator.getAndIncrement());
        vehiculos.add(v);
        return v;
    }
    public Vehiculo update(Long id, Vehiculo act) {
        Vehiculo v = findById(id);
        v.setMarca(act.getMarca());
        v.setModelo(act.getModelo());
        v.setAnio(act.getAnio());
        v.setPrecio(act.getPrecio());
        return v;
    }
    public Vehiculo patch(Long id, Map<String, Object> updates) {
        Vehiculo v = findById(id);
        updates.forEach((key, value) -> {
            switch (key) {
                case "marca" -> v.setMarca((String) value);
                case "modelo" -> v.setModelo((String) value);
                case "anio" -> v.setAnio(((Number) value).intValue());
                case "precio" -> v.setPrecio(((Number) value).doubleValue());
            }
        });
        return v;
    }
    public void deleteById(Long id) { vehiculos.remove(findById(id)); }
}