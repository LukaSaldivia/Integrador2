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


    public Estudiante(Integer dni, Integer nroLibreta, String nombre, String apellido, Integer edad, String ciudad, String genero) {
        this.dni = dni;
        this.nroLibreta = nroLibreta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ciudad = ciudad;
        this.genero = genero;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Integer getNroLibreta() {
        return nroLibreta;
    }

    public void setNroLibreta(Integer nroLibreta) {
        this.nroLibreta = nroLibreta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
