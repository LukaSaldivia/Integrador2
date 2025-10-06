package ar.unicen.exa.aldesal.repository.impl;

import ar.unicen.exa.aldesal.dto.CarreraDTO;
import ar.unicen.exa.aldesal.dto.EstadoOperacionDTO;
import ar.unicen.exa.aldesal.dto.ReporteDTO;
import ar.unicen.exa.aldesal.model.Carrera;
import ar.unicen.exa.aldesal.repository.CarreraRepository;

import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.FileReader;
import java.util.List;

public class CarreraRepositoryImpl implements CarreraRepository {

    private EntityManager em;
    public CarreraRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertarDesdeCSV(String rutaArchivo) {
        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            String[] linea;
            reader.readNext(); // salta cabecera

            this.em.getTransaction().begin();

            while ((linea = reader.readNext()) != null) {
                Carrera carrera = new Carrera();
                carrera.setId(Integer.parseInt(linea[0]));
                carrera.setNombre(linea[1]);
                carrera.setDuracion(Integer.parseInt(linea[2]));

                this.em.persist(carrera);
            }

            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.em.close();
        }
    }

    // Inciso 2.f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
    // En esta consulta se usa un "constructor expression" de JPQL al principio del select
    // significa que por cada resultado de la consulta se crea directamente un objeto CarreraDTO,
    // pasando los valores al constructor: id, nombre, duración y la cantidad de estudiantes inscriptos (COUNT(i.id)).
    // Así, el campo cantInscriptos se calcula al vuelo y se guarda en el DTO, sin estar en la entidad ni en la BD.
    @Override
    public EstadoOperacionDTO<List<CarreraDTO>> getCarrerasOrderByCantInscriptos() {
        String query = "SELECT new ar.unicen.exa.aldesal.dto.CarreraDTO(c.id, c.nombre, c.duracion, COUNT(i.id)) " +
                "FROM Carrera c " +
                "JOIN c.inscripciones i " +
                "GROUP BY c.id, c.nombre, c.duracion " +
                "ORDER BY COUNT(i.id) DESC";  // añadi un atributo en CarreraDTO que es CantInscriptos.
        TypedQuery<CarreraDTO> q = em.createQuery(query, CarreraDTO.class);
        List<CarreraDTO> carreras = q.getResultList();
        em.close();
        return new EstadoOperacionDTO<>(true, carreras);
    }

    @Override
    public EstadoOperacionDTO<List<ReporteDTO>> getReporte() {

        List<ReporteDTO> response = null;

        try {
            String query = """
                    SELECT c.id, 
                    c.nombre, 
                    COUNT(i.inscripcion) cantInscriptos, 
                    COUNT(i.graduacion) cantGraduados,
                    i.graduacion,
                    FROM Carrera c
                    JOIN Inscripcion i ON i.id_carrera = c.id
                    GROUP BY i.graduacion
                    ORDER BY c.nombre ASC, i.graduacion ASC
                    """;

            response = em.createQuery(query, ReporteDTO.class).getResultList();
        }catch (Exception e) {
            return new EstadoOperacionDTO<>(false, null);
        }

        return new EstadoOperacionDTO<>(true, response);
    }
}
