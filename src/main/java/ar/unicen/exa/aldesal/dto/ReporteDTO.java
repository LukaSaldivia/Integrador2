package ar.unicen.exa.aldesal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ReporteDTO {
    private Integer id;
    private String nombre;
    private int inscriptos;
    private int graduados;
    private int anio;
}
