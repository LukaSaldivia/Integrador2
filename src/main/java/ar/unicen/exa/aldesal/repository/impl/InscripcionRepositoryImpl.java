package ar.unicen.exa.aldesal.repository.impl;

import ar.unicen.exa.aldesal.dto.EstadoOperacionDTO;
import ar.unicen.exa.aldesal.dto.EstudianteDTO;
import ar.unicen.exa.aldesal.dto.InscripcionDTO;
import ar.unicen.exa.aldesal.model.Carrera;
import ar.unicen.exa.aldesal.model.Estudiante;
import ar.unicen.exa.aldesal.model.Inscripcion;
import ar.unicen.exa.aldesal.repository.InscripcionRepository;

import com.opencsv.CSVReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.io.FileReader;
import java.time.Year;

public class InscripcionRepositoryImpl implements InscripcionRepository {
    private EntityManager em;
    public InscripcionRepositoryImpl( EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertarDesdeCSV(String rutaArchivo) {
        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            String[] linea;
            reader.readNext(); // salta cabecera

            this.em.getTransaction().begin();

            while ((linea = reader.readNext()) != null) {
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setId(Integer.parseInt(linea[0]));
                // Busca en la base de datos el Estudiante y la Carrera referenciados por los IDs del CSV,
                // y los asigna a la nueva Inscripcion para mantener la relación entre las entidades.
                Estudiante estudiante = em.find(Estudiante.class, Integer.parseInt(linea[1]));
                inscripcion.setEstudiante(estudiante);
                Carrera carrera = em.find(Carrera.class, Integer.parseInt(linea[2]));
                inscripcion.setCarrera(carrera);
                inscripcion.setInscripcion(Integer.parseInt(linea[3]));
                inscripcion.setGraduacion(Integer.parseInt(linea[4]));
                inscripcion.setAntiguedad(Integer.parseInt(linea[5]));

                this.em.persist(inscripcion);
            }

            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Inciso 2.b) matricular un estudiante en una carrera
    @Override
    public EstadoOperacionDTO<InscripcionDTO> matricular(Inscripcion inscripcion) {
        try {
            em.getTransaction().begin();
            em.persist(inscripcion);
            em.getTransaction().commit();
        } catch (Exception e) {
            return new EstadoOperacionDTO<>(false, null);
        }
        InscripcionDTO inscripcionDTO = new InscripcionDTO(inscripcion.getId(), inscripcion.getCarrera().getId(), inscripcion.getEstudiante().getDni(), inscripcion.getInscripcion(), inscripcion.getGraduacion(), inscripcion.getAntiguedad());
        return new EstadoOperacionDTO<>( true, inscripcionDTO);
    }
}
