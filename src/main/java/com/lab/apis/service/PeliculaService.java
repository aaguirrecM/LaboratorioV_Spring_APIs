package com.lab.apis.service;

import com.lab.apis.exception.ResourceNotFoundException;
import com.lab.apis.model.Pelicula;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PeliculaService {
    private final List<Pelicula> peliculas = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public PeliculaService() {
        save(new Pelicula(null, "Inception", "Christopher Nolan", "Ciencia Ficción", 2010));
        save(new Pelicula(null, "Pulp Fiction", "Quentin Tarantino", "Crimen", 1994));
        save(new Pelicula(null, "Interstellar", "Christopher Nolan", "Ciencia Ficción", 2014));
        save(new Pelicula(null, "The Dark Knight", "Christopher Nolan", "Acción", 2008));
        save(new Pelicula(null, "Parasite", "Bong Joon-ho", "Drama", 2019));
    }

    public List<Pelicula> findAll() { return new ArrayList<>(peliculas); }
    public Pelicula findById(Long id) {
        return peliculas.stream().filter(p -> p.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Película no encontrada con ID: " + id));
    }
    public Pelicula save(Pelicula p) {
        if (p.getId() == null) p.setId(idGenerator.getAndIncrement());
        peliculas.add(p);
        return p;
    }
    public Pelicula update(Long id, Pelicula act) {
        Pelicula p = findById(id);
        p.setTitulo(act.getTitulo());
        p.setDirector(act.getDirector());
        p.setGenero(act.getGenero());
        p.setAnio(act.getAnio());
        return p;
    }
    public Pelicula patch(Long id, Map<String, Object> updates) {
        Pelicula p = findById(id);
        updates.forEach((key, value) -> {
            switch (key) {
                case "titulo" -> p.setTitulo((String) value);
                case "director" -> p.setDirector((String) value);
                case "genero" -> p.setGenero((String) value);
                case "anio" -> p.setAnio(((Number) value).intValue());
            }
        });
        return p;
    }
    public void deleteById(Long id) { peliculas.remove(findById(id)); }
}