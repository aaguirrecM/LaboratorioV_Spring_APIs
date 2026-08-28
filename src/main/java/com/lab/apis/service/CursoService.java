package com.lab.apis.service;

import com.lab.apis.exception.ResourceNotFoundException;
import com.lab.apis.model.Curso;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CursoService {
    private final List<Curso> cursos = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public CursoService() {
        save(new Curso(null, "Programación Java", "Curso completo de sintaxis y POO", 4, "Virtual"));
        save(new Curso(null, "Base de Datos SQL", "Modelado y consultas relacionales", 5, "Presencial"));
        save(new Curso(null, "Desarrollo Web", "Frontend con HTML, CSS y JS", 3, "Híbrida"));
        save(new Curso(null, "Spring Boot Framework", "Creación de REST APIs", 5, "Virtual"));
        save(new Curso(null, "Estructuras de Datos", "Algoritmos y listas enlazadas", 4, "Presencial"));
    }

    public List<Curso> findAll() { return new ArrayList<>(cursos); }
    public Curso findById(Long id) {
        return cursos.stream().filter(c -> c.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + id));
    }
    public Curso save(Curso c) {
        if (c.getId() == null) c.setId(idGenerator.getAndIncrement());
        cursos.add(c);
        return c;
    }
    public Curso update(Long id, Curso act) {
        Curso c = findById(id);
        c.setNombre(act.getNombre());
        c.setDescripcion(act.getDescripcion());
        c.setCreditos(act.getCreditos());
        c.setModalidad(act.getModalidad());
        return c;
    }
    public Curso patch(Long id, Map<String, Object> updates) {
        Curso c = findById(id);
        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre" -> c.setNombre((String) value);
                case "descripcion" -> c.setDescripcion((String) value);
                case "creditos" -> c.setCreditos(((Number) value).intValue());
                case "modalidad" -> c.setModalidad((String) value);
            }
        });
        return c;
    }
    public void deleteById(Long id) { cursos.remove(findById(id)); }
}