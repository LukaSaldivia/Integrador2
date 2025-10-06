package ar.unicen.exa.aldesal.repository;

import ar.unicen.exa.aldesal.dto.CarreraDTO;
import ar.unicen.exa.aldesal.dto.EstadoOperacionDTO;
import ar.unicen.exa.aldesal.dto.ReporteDTO;

import java.util.List;

public interface CarreraRepository {
    public void insertarDesdeCSV(String rutaArchivo);
    public EstadoOperacionDTO<List<CarreraDTO>> getCarrerasOrderByCantInscriptos();
    public EstadoOperacionDTO<List<ReporteDTO>> getReporte();
}
