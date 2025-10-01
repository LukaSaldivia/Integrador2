package ar.unicen.exa.aldesal.repository;

import ar.unicen.exa.aldesal.dto.CarreraDTO;
import ar.unicen.exa.aldesal.dto.EstadoOperacionDTO;

import java.util.List;

public interface CarreraRepository {
    public EstadoOperacionDTO<List<CarreraDTO>> getCarrerasOrderByCantInscriptos();
}
