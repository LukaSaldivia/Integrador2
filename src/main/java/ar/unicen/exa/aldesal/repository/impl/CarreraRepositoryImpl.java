package ar.unicen.exa.aldesal.repository.impl;

import ar.unicen.exa.aldesal.dto.CarreraDTO;
import ar.unicen.exa.aldesal.dto.EstadoOperacionDTO;
import ar.unicen.exa.aldesal.repository.CarreraRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CarreraRepositoryImpl implements CarreraRepository {

    private EntityManager em;
    public CarreraRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    // Inciso 2.f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
    @Override
    public EstadoOperacionDTO<List<CarreraDTO>> getCarrerasOrderByCantInscriptos() {
        String query = "SELECT new ar.unicen.exa.aldesal.dto.CarreraDTO(c.id, c.nombre, c.duracion, COUNT(i.id)) " +
                "FROM Carrera c " +
                "JOIN c.inscripciones i " +
                "GROUP BY c.id, c.nombre, c.duracion " +
                "ORDER BY COUNT(i.id) DESC";  // añadi un atributo en CarreraDTO que es CantInscriptos.Solo existe en el dto , se calcula al momento de ejecutar la consulta.
        TypedQuery<CarreraDTO> q = em.createQuery(query, CarreraDTO.class);
        List<CarreraDTO> carreras = q.getResultList();
        em.close();
        return new EstadoOperacionDTO<>(true, carreras);
    }
}
