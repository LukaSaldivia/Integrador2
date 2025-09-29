package ar.unicen.exa.aldesal.dto;

public class EstudianteDTO {
    private int dni;
    private int nroLibreta;
    private String nombre;
    private String apellido;
    private int edad;
    private String ciudad;
    private String genero;

    public EstudianteDTO(int dni, int nroLibreta, String nombre, String apellido, int edad, String ciudad, String genero) {
        this.dni = dni;
        this.nroLibreta = nroLibreta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ciudad = ciudad;
        this.genero = genero;
    }

    public int getDni() {
        return dni;
    }

    public int getNroLibreta() {
        return nroLibreta;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "EstudianteDTO{" +
                "dni=" + dni +
                ", nroLibreta=" + nroLibreta +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", ciudad='" + ciudad + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}
