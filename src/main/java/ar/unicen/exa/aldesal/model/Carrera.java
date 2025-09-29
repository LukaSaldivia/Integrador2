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
}
