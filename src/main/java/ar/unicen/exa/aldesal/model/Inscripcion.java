package ar.unicen.exa.aldesal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {
    @Id
    private  Integer id;

    @ManyToOne
    @JoinColumn(name = "id_carrera", referencedColumnName = "id")
    private Carrera carrera;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", referencedColumnName = "dni")
    private Estudiante estudiante;

    @Column (nullable = false)
    private Integer inscripcion;

    @Column
    private Integer graduacion;

    @Column (nullable = false)
    private Integer antiguedad;

}
