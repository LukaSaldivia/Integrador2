package ar.unicen.exa.aldesal.dto;

public class CarreraDTO {

    private int id;
    private String nombre;
    private int duracion;
    private long cantInscriptos;

    public CarreraDTO(int id, String nombre, int duracion, long cantInscriptos) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.cantInscriptos = cantInscriptos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public long getCantInscriptos() {
        return cantInscriptos;
    }

    @Override
    public String toString() {
        return "CarreraDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", cantInscriptos=" + cantInscriptos +
                '}';
    }
}
