package ar.unicen.exa.aldesal.repository.impl;

import ar.unicen.exa.aldesal.dto.EstadoOperacionDTO;
import ar.unicen.exa.aldesal.dto.EstudianteDTO;
import ar.unicen.exa.aldesal.model.Estudiante;
import ar.unicen.exa.aldesal.repository.EstudianteRepository;
import com.opencsv.CSVReader;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.io.FileReader;
import java.util.List;


public class EstudianteRepositoryImpl implements EstudianteRepository {
    private EntityManager em;
    public EstudianteRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertarDesdeCSV(String rutaArchivo) {
        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            String[] linea;
            reader.readNext(); // salta cabecera

            this.em.getTransaction().begin();

            while ((linea = reader.readNext()) != null) {
                Estudiante estudiante = new Estudiante();
                estudiante.setDni(Integer.parseInt(linea[0]));
                estudiante.setNombre(linea[1]);
                estudiante.setApellido(linea[2]);
                estudiante.setEdad(Integer.parseInt(linea[3]));
                estudiante.setGenero(linea[4]);
                estudiante.setCiudad(linea[5]);
                estudiante.setNroLibreta(Integer.parseInt(linea[6]));

                this.em.persist(estudiante);
            }

            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Inciso 2.a) dar de alta un estudiante
    @Override
    public EstadoOperacionDTO<EstudianteDTO> guardar(Estudiante estudiante) {
        try {
            em.getTransaction().begin();
            em.persist(estudiante);
            em.getTransaction().commit();
        }catch (Exception e) {
            return new EstadoOperacionDTO<>(false, null);
        }
        EstudianteDTO estudianteDTO = new EstudianteDTO(estudiante.getDni(), estudiante.getNroLibreta(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getEdad(), estudiante.getCiudad(), estudiante.getGenero());
        return new EstadoOperacionDTO<>(true, estudianteDTO);
    }

    //Inciso 2.c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
    @Override
    public EstadoOperacionDTO<List<EstudianteDTO>> obtenerTodos() {
        List<EstudianteDTO> estudiantes = null;
        try {
            String sortOrder = "ASC";  // orden ascendente
            String orderBy = "e.dni";  // campo por el cual se ordena
            String query = "SELECT new ar.unicen.exa.aldesal.dto.EstudianteDTO(" +
                    "e.dni, e.nroLibreta, e.nombre, e.apellido, e.edad, e.ciudad, e.genero) " +
                    "FROM Estudiante e " +
                    "ORDER BY " + orderBy + " " + sortOrder;
            Query q = em.createQuery(query,  EstudianteDTO.class);
            estudiantes = q.getResultList();
        } catch (Exception e) {
            return new EstadoOperacionDTO<>(false, null);
        }
        return new EstadoOperacionDTO<>(true, estudiantes);
    }

    //Inciso 2.d) recuperar un estudiante, en base a su número de libreta universitaria.
    @Override
    public EstadoOperacionDTO<EstudianteDTO> obtenerPorLibreta(Integer nroLibreta) {
        EstudianteDTO estudiante = null;
        try {
            String query =  "SELECT new ar.unicen.exa.aldesal.dto.EstudianteDTO(e.dni, e.nroLibreta, e.nombre, e.apellido, e.edad, e.ciudad, e.genero) FROM Estudiante e WHERE e.nroLibreta =:libreta";
            TypedQuery<EstudianteDTO> q = em.createQuery(query, EstudianteDTO.class);
            q.setParameter("libreta", nroLibreta);
            estudiante = q.getSingleResult();
        } catch (Exception e) {
            return new EstadoOperacionDTO<>(false, null);
        }
        return new EstadoOperacionDTO<>(true, estudiante);
    }
    //Incisco 2.e) recuperar todos los estudiantes, en base a su género.
    @Override
    public EstadoOperacionDTO<List<EstudianteDTO>> obtenerTodosSegunGenero(String genero) {
        List<EstudianteDTO> estudiantes = null;
        try {
            String query = "SELECT new ar.unicen.exa.aldesal.dto.EstudianteDTO (e.dni, e.nroLibreta, e.nombre, e.apellido, e.edad, e.ciudad, e.genero) FROM Estudiante e WHERE e.genero =:genero";
            TypedQuery<EstudianteDTO> q = em.createQuery(query, EstudianteDTO.class);
            q.setParameter("genero", genero);
            estudiantes = q.getResultList();
        } catch (Exception e) {
            return new EstadoOperacionDTO<>(false, null);
        }
        return new EstadoOperacionDTO<>(true, estudiantes);
    }

    //Inciso 2.g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia
    @Override
    public EstadoOperacionDTO<List<EstudianteDTO>> obtenerEstudiantesPorCarreraYCiudad(Integer id_carrera, String id_ciudad) {
        List<EstudianteDTO> estudiantes = null;
        try {
            String query = "SELECT DISTINCT new ar.unicen.exa.aldesal.dto.EstudianteDTO (e.dni, e.nroLibreta, e.nombre, e.apellido, e.edad, e.ciudad, e.genero) FROM Estudiante e " +
                           "JOIN e.inscripciones i JOIN i.carrera c " +
                            "WHERE c.id = :id_carrera AND e.ciudad = :id_ciudad";
            TypedQuery<EstudianteDTO> q = em.createQuery(query, EstudianteDTO.class);
            q.setParameter("id_carrera", id_carrera);
            q.setParameter("id_ciudad", id_ciudad);
            estudiantes = q.getResultList();
        } catch (Exception e) {
            return new EstadoOperacionDTO<>(false, null);
        }
        return new EstadoOperacionDTO<>(true, estudiantes);
    }



}
