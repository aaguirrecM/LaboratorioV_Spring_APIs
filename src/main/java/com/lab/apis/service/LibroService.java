package com.lab.apis.service;

import com.lab.apis.exception.ResourceNotFoundException;
import com.lab.apis.model.Libro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LibroService {
    private final List<Libro> libros = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public LibroService() {
        save(new Libro(null, "Cien Años de Soledad", "Gabriel García Márquez", "Realismo Mágico", 25.00));
        save(new Libro(null, "1984", "George Orwell", "Distopía", 18.50));
        save(new Libro(null, "El Principito", "Antoine de Saint-Exupéry", "Fábula", 12.00));
        save(new Libro(null, "Clean Code", "Robert C. Martin", "Tecnología", 45.00));
        save(new Libro(null, "Don Quijote de la Mancha", "Miguel de Cervantes", "Clásico", 30.00));
    }

    public List<Libro> findAll() { return new ArrayList<>(libros); }
    public Libro findById(Long id) {
        return libros.stream().filter(l -> l.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con ID: " + id));
    }
    public Libro save(Libro libro) {
        if (libro.getId() == null) libro.setId(idGenerator.getAndIncrement());
        libros.add(libro);
        return libro;
    }
    public Libro update(Long id, Libro act) {
        Libro l = findById(id);
        l.setTitulo(act.getTitulo());
        l.setAutor(act.getAutor());
        l.setGenero(act.getGenero());
        l.setPrecio(act.getPrecio());
        return l;
    }
    public Libro patch(Long id, Map<String, Object> updates) {
        Libro l = findById(id);
        updates.forEach((key, value) -> {
            switch (key) {
                case "titulo" -> l.setTitulo((String) value);
                case "autor" -> l.setAutor((String) value);
                case "genero" -> l.setGenero((String) value);
                case "precio" -> l.setPrecio(((Number) value).doubleValue());
            }
        });
        return l;
    }
    public void deleteById(Long id) { libros.remove(findById(id)); }
}