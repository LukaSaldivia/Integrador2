package ar.unicen.exa.aldesal.repository.impl;

import ar.unicen.exa.aldesal.dto.EstadoOperacionDTO;
import ar.unicen.exa.aldesal.dto.EstudianteDTO;
import ar.unicen.exa.aldesal.repository.EstudianteRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository {
    public EntityManager em;
    public EstudianteRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    //Inciso 2.a) dar de alta un estudiante
    @Override
    public EstadoOperacionDTO<EstudianteDTO> guardar(EstudianteDTO estudiante) {
        if (estudiante.getDni() == null) {
            em.persist(estudiante);
        }
        else
            em.merge(estudiante);
        return null;
    }

    //Inciso 2.c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
    @Override
    public EstadoOperacionDTO<List<EstudianteDTO>> obtenerTodos() {
        String sortOrder = " ASC";
        String orderBy = " .dni";
        String query = "SELECT e FROM Estudiante e" + orderBy + " " + sortOrder;
        Query q = em.createQuery(query);
        List<EstudianteDTO> estudiantes = q.getResultList();
        em.close();
        return new EstadoOperacionDTO<>(true, estudiantes);
    }

    //Inciso 2.d) recuperar un estudiante, en base a su número de libreta universitaria.
    @Override
    public EstadoOperacionDTO<EstudianteDTO> obtenerPorLibreta(Integer nroLibreta) {
        String query =  "SELECT e FROM Estudiante e WHERE e.nroLibreta =:libreta";
        TypedQuery<EstudianteDTO> q = em.createQuery(query, EstudianteDTO.class);
        q.setParameter("libreta", nroLibreta);
        EstudianteDTO estudiante = q.getSingleResult();
        em.close();
        return new EstadoOperacionDTO<>(true, estudiante);
    }
    //Incisco 2.e) recuperar todos los estudiantes, en base a su género.
    @Override
    public EstadoOperacionDTO<List<EstudianteDTO>> obtenerTodosSegunGenero(String genero) {
        String query = "SELECT e FROM Estudiante e WHERE e.genero =:genero";
        TypedQuery<EstudianteDTO> q = em.createQuery(query, EstudianteDTO.class);
        q.setParameter("genero", genero);
        List<EstudianteDTO> estudiantes = q.getResultList();
        em.close();
        return new EstadoOperacionDTO<>(true, estudiantes);
    }


    //Inciso 2.f) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia
    @Override
    public EstadoOperacionDTO<List<EstudianteDTO>> obtenerEstudiantesPorCarreraYCiudad(Integer id_carrera, String id_ciudad) {
        String query = "SELECT new ar.unicen.exa.aldesal.dto.EstudianteDTO (e.dni, e.nroLibreta, e.nombre, e.apellido, e.edad, e.ciudad, e.genero) FROM Estudiante e " +
                       "JOIN e.inscripciones i JOIN i.carrera c " +
                        "WHERE c.id = :id_carrera AND e.ciudad = :id_ciudad";
        TypedQuery<EstudianteDTO> q = em.createQuery(query, EstudianteDTO.class);
        q.setParameter("id_carrera", id_carrera);
        q.setParameter("id_ciudad", id_ciudad);
        List<EstudianteDTO> estudiantes = q.getResultList();
        return new EstadoOperacionDTO<>(true, estudiantes);
    }



}
