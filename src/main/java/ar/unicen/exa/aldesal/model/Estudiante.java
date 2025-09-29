package ar.unicen.exa.aldesal.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Estudiante {

    @Id
    private Integer dni;

    @Column (nullable = false, unique = true, name = "nro_libreta")
    private Integer nroLibreta;

    @Column (nullable = false)
    private String nombre;

    @Column (nullable = false)
    private String apellido;

    @Column (nullable = false)
    private Integer edad;

    @Column (nullable = false)
    private String ciudad;

    @Column (nullable = false)
    private String genero;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Inscripcion> inscripciones;

}
