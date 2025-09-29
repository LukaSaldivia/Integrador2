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

    public Inscripcion(Integer id, Integer antiguedad, Integer graduacion, Estudiante estudiante, Carrera carrera, Integer inscripcion) {
        this.id = id;
        this.antiguedad = antiguedad;
        this.graduacion = graduacion;
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.inscripcion = inscripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Integer getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Integer inscripcion) {
        this.inscripcion = inscripcion;
    }

    public Integer getGraduacion() {
        return graduacion;
    }

    public void setGraduacion(Integer graduacion) {
        this.graduacion = graduacion;
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }
}
