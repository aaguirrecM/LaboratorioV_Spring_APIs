package com.lab.apis.service;

import com.lab.apis.exception.ResourceNotFoundException;
import com.lab.apis.model.Tarea;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TareaService {
    private final List<Tarea> tareas = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public TareaService() {
        save(new Tarea(null, "Estudiar Spring Boot", "Revisar controladores y servicios", "Alta", false));
        save(new Tarea(null, "Comprar insumos", "Comprar papelería para oficina", "Baja", true));
        save(new Tarea(null, "Hacer laboratorio", "Crear las 10 APIs REST requeridas", "Alta", false));
        save(new Tarea(null, "Reunión de equipo", "Sync diario con el equipo Scrum", "Media", true));
        save(new Tarea(null, "Actualizar CV", "Añadir proyectos recientes", "Media", false));
    }

    public List<Tarea> findAll() { return new ArrayList<>(tareas); }
    public Tarea findById(Long id) {
        return tareas.stream().filter(t -> t.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con ID: " + id));
    }
    public Tarea save(Tarea t) {
        if (t.getId() == null) t.setId(idGenerator.getAndIncrement());
        tareas.add(t);
        return t;
    }
    public Tarea update(Long id, Tarea act) {
        Tarea t = findById(id);
        t.setTitulo(act.getTitulo());
        t.setDescripcion(act.getDescripcion());
        t.setPrioridad(act.getPrioridad());
        t.setCompletada(act.getCompletada());
        return t;
    }
    public Tarea patch(Long id, Map<String, Object> updates) {
        Tarea t = findById(id);
        updates.forEach((key, value) -> {
            switch (key) {
                case "titulo" -> t.setTitulo((String) value);
                case "descripcion" -> t.setDescripcion((String) value);
                case "prioridad" -> t.setPrioridad((String) value);
                case "completada" -> t.setCompletada((Boolean) value);
            }
        });
        return t;
    }
    public void deleteById(Long id) { tareas.remove(findById(id)); }
}