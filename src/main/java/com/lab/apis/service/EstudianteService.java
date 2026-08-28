package com.lab.apis.service;

import com.lab.apis.exception.ResourceNotFoundException;
import com.lab.apis.model.Estudiante;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EstudianteService {
    private final List<Estudiante> estudiantes = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public EstudianteService() {
        save(new Estudiante(null, "Sofia", "Ramirez", "Ingeniería en Sistemas", 20));
        save(new Estudiante(null, "Mateo", "Fernandez", "Ingeniería Industrial", 22));
        save(new Estudiante(null, "Valeria", "Morales", "Administración de Empresas", 21));
        save(new Estudiante(null, "Lucas", "Herrera", "Derecho", 23));
        save(new Estudiante(null, "Camila", "Torres", "Medicina", 19));
    }

    public List<Estudiante> findAll() { return new ArrayList<>(estudiantes); }

    public Estudiante findById(Long id) {
        return estudiantes.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con ID: " + id));
    }

    public Estudiante save(Estudiante estudiante) {
        if (estudiante.getId() == null) estudiante.setId(idGenerator.getAndIncrement());
        estudiantes.add(estudiante);
        return estudiante;
    }

    public Estudiante update(Long id, Estudiante act) {
        Estudiante est = findById(id);
        est.setNombre(act.getNombre());
        est.setApellido(act.getApellido());
        est.setCarrera(act.getCarrera());
        est.setEdad(act.getEdad());
        return est;
    }

    public Estudiante patch(Long id, Map<String, Object> updates) {
        Estudiante est = findById(id);
        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre" -> est.setNombre((String) value);
                case "apellido" -> est.setApellido((String) value);
                case "carrera" -> est.setCarrera((String) value);
                case "edad" -> est.setEdad(((Number) value).intValue());
            }
        });
        return est;
    }

    public void deleteById(Long id) { estudiantes.remove(findById(id)); }
}