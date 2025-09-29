package ar.unicen.exa.aldesal.repository.impl;

import ar.unicen.exa.aldesal.dto.EstadoOperacionDTO;
import ar.unicen.exa.aldesal.dto.EstudianteDTO;
import ar.unicen.exa.aldesal.repository.EstudianteRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository {
    public EntityManager em;
    public EstudianteRepositoryImpl(EntityManager em) {
        this.em = em;
    }
    @Override
    public EstadoOperacionDTO<EstudianteDTO> guardar(EstudianteDTO estudiante) {
        if (estudiante.getDni() == null) {
            em.persist(estudiante);
        }
        else
            em.merge(estudiante);
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
