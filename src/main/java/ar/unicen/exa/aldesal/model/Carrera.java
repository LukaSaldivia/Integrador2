package ar.unicen.exa.aldesal.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
