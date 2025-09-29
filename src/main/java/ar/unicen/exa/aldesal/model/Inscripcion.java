package ar.unicen.exa.aldesal.model;

import javax.persistence.*;

@Entity
public class Inscripcion {
    @Id
    private  Integer id;

    @ManyToOne
    @JoinColumn(name = "id_carrera", referencedColumnName = "id")
    private Carrera carrera;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id")
    private Estudiante estudiante;

    @Column (nullable = false)
    private Integer inscripcion;

    @Column
    private Integer graduacion;

    @Column (nullable = false)
    private Integer antiguedad;


}
