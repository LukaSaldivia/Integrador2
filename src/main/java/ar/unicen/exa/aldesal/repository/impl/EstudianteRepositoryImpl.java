package ar.unicen.exa.aldesal.repository.impl;

import ar.unicen.exa.aldesal.dto.EstadoOperacionDTO;
import ar.unicen.exa.aldesal.dto.EstudianteDTO;
import ar.unicen.exa.aldesal.repository.EstudianteRepository;

import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository {



    @Override
    public EstadoOperacionDTO<EstudianteDTO> guardar(EstudianteDTO estudiante) {

    }

    @Override
    public EstadoOperacionDTO<EstudianteDTO> matricular(EstudianteDTO estudiante, Integer id_carrera) {
        return null;
    }

    @Override
    public EstadoOperacionDTO<List<EstudianteDTO>> obtenerTodos() {
        return null;
    }

    @Override
    public EstadoOperacionDTO<EstudianteDTO> obtenerPorLibreta(Integer nroLibreta) {
        return null;
    }

    @Override
    public EstadoOperacionDTO<List<EstudianteDTO>> obtenerTodosSegunGenero(String genero) {
        return null;
    }
}
