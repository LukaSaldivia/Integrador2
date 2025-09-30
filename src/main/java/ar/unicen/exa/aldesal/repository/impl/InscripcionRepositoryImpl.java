package ar.unicen.exa.aldesal.repository.impl;

import ar.unicen.exa.aldesal.dto.EstadoOperacionDTO;
import ar.unicen.exa.aldesal.dto.EstudianteDTO;
import ar.unicen.exa.aldesal.repository.InscripcionRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.Year;

public class InscripcionRepositoryImpl implements InscripcionRepository {
    private EntityManager em;
    public InscripcionRepositoryImpl( EntityManager em) {
        this.em = em;
    }

    //Inciso 2.b) matricular un estudiante en una carrera
    @Override
    public EstadoOperacionDTO<EstudianteDTO> matricular(EstudianteDTO estudiante, Integer id_carrera) {
        Long estudianteDni = estudiante.getDni();
        int inscripcion = Year.now().getValue();
        //para checkear la tabla es con I o i?
        Query q = em.createNativeQuery("INSERT INTO Inscripcion (id_estudiante, id_carrera, inscripcion, graduacion, antiguedad) VALUES (?,?,?,?,?)");
        q.setParameter(1, estudianteDni);
        q.setParameter(2, id_carrera);
        q.setParameter(3, inscripcion);
        q.setParameter(4, 0);
        q.setParameter(5, 0);
        q.executeUpdate();
        em.close();
        // no entendi el return
        return null;

    }
}
