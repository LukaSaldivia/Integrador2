package ar.unicen.exa.aldesal.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
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

    public Estudiante(Integer dni, Integer nroLibreta, String nombre, String apellido, Integer edad, String ciudad, String genero) {
        this.dni = dni;
        this.nroLibreta = nroLibreta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ciudad = ciudad;
        this.genero = genero;
    }

    public Estudiante() {

    }
}
