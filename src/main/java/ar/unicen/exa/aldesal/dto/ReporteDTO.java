package ar.unicen.exa.aldesal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReporteDTO {
    private Integer id;
    private String nombre;
    private int inscriptos;
    private int graduados;
    private int anio;

    public ReporteDTO(Integer id, String nombre, int inscriptos, int graduados, int anio) {
        this.id = id;
        this.nombre = nombre;
        this.inscriptos = inscriptos;
        this.graduados = graduados;
        this.anio = anio;
    }
}
