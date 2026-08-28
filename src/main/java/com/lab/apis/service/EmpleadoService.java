package com.lab.apis.service;

import com.lab.apis.exception.ResourceNotFoundException;
import com.lab.apis.model.Empleado;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmpleadoService {
    private final List<Empleado> empleados = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public EmpleadoService() {
        save(new Empleado(null, "Alejandro Castro", "Desarrollador Java", 2500.00, "Sistemas"));
        save(new Empleado(null, "Beatriz Méndez", "Analista QA", 1800.00, "Calidad"));
        save(new Empleado(null, "Daniela Ortiz", "Scrum Master", 3000.00, "Proyectos"));
        save(new Empleado(null, "Fernando Silva", "Soporte Técnico", 1200.00, "TI"));
        save(new Empleado(null, "Gabriela Rios", "Gerente RH", 2800.00, "Recursos Humanos"));
    }

    public List<Empleado> findAll() { return new ArrayList<>(empleados); }
    public Empleado findById(Long id) {
        return empleados.stream().filter(e -> e.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con ID: " + id));
    }
    public Empleado save(Empleado emp) {
        if (emp.getId() == null) emp.setId(idGenerator.getAndIncrement());
        empleados.add(emp);
        return emp;
    }
    public Empleado update(Long id, Empleado act) {
        Empleado e = findById(id);
        e.setNombre(act.getNombre());
        e.setPuesto(act.getPuesto());
        e.setSalario(act.getSalario());
        e.setDepartamento(act.getDepartamento());
        return e;
    }
    public Empleado patch(Long id, Map<String, Object> updates) {
        Empleado e = findById(id);
        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre" -> e.setNombre((String) value);
                case "puesto" -> e.setPuesto((String) value);
                case "salario" -> e.setSalario(((Number) value).doubleValue());
                case "departamento" -> e.setDepartamento((String) value);
            }
        });
        return e;
    }
    public void deleteById(Long id) { empleados.remove(findById(id)); }
}