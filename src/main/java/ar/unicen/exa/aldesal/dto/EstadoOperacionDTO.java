package ar.unicen.exa.aldesal.dto;

public class EstadoOperacionDTO<T> {
    private boolean exito;
    private T datos;

    public EstadoOperacionDTO(boolean exito, T datos) {
        this.exito = exito;
        this.datos = datos;
    }

    public boolean getExito() {
        return exito;
    }

    public T getDatos() {
        return datos;
    }


}
