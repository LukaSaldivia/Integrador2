package ar.unicen.exa.aldesal.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Carrera {
    @Id
    private Integer id;

    @Column (nullable = false)
    private String nombre;

    @Column (nullable = false)
    private Integer duracion;

    @OneToMany(mappedBy = "carrera",  cascade = CascadeType.ALL)
    private List<Inscripcion> inscripciones;

    public Carrera(Integer id, Integer duracion, String nombre) {
        this.id = id;
        this.duracion = duracion;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
}
