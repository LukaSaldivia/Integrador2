package ar.unicen.exa.aldesal.repository;

import ar.unicen.exa.aldesal.dto.EstadoOperacionDTO;
import ar.unicen.exa.aldesal.dto.EstudianteDTO;

public interface InscripcionRepository {
    public EstadoOperacionDTO<EstudianteDTO> matricular(EstudianteDTO estudiante, Integer id_carrera);
}
