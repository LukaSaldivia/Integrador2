package ar.unicen.exa.aldesal;

import ar.unicen.exa.aldesal.dto.EstadoOperacionDTO;
import ar.unicen.exa.aldesal.factory.JPAUtil;
import ar.unicen.exa.aldesal.model.Carrera;
import ar.unicen.exa.aldesal.model.Estudiante;
import ar.unicen.exa.aldesal.model.Inscripcion;
import ar.unicen.exa.aldesal.repository.CarreraRepository;
import ar.unicen.exa.aldesal.repository.EstudianteRepository;
import ar.unicen.exa.aldesal.repository.InscripcionRepository;
import ar.unicen.exa.aldesal.repository.impl.CarreraRepositoryImpl;
import ar.unicen.exa.aldesal.repository.impl.EstudianteRepositoryImpl;

import ar.unicen.exa.aldesal.repository.impl.InscripcionRepositoryImpl;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager em1 = JPAUtil.getEntityManager();
        EntityManager em2 = JPAUtil.getEntityManager();
        EntityManager em3 = JPAUtil.getEntityManager();

        try {
            EstudianteRepository er = new EstudianteRepositoryImpl(em1);
            er.insertarDesdeCSV("src/main/resources/estudiantes.csv");

            CarreraRepository cr = new CarreraRepositoryImpl(em2);
            cr.insertarDesdeCSV("src/main/resources/carreras.csv");

            InscripcionRepository ir = new InscripcionRepositoryImpl(em3);
            ir.insertarDesdeCSV("src/main/resources/estudianteCarrera.csv");

            //2)a)
            Estudiante estudiante = new Estudiante(37935541, 282612, "marcos", "cordoba", 31, "Tandil", "Male");
            EstadoOperacionDTO estudianteDTO = er.guardar(estudiante);
            System.out.println("Estado del metodo guardar: " + estudianteDTO.getExito());

            //2)b)
            Carrera carrera = new Carrera(1, "TUDAI", 2, null);
            Inscripcion inscripcion = new Inscripcion(110, carrera, estudiante, 2025, 0,0);
            EstadoOperacionDTO inscripcionDTO = ir.matricular(inscripcion);
            System.out.println("Estado del metodo matricular: " + inscripcionDTO.getExito());

            //2)c)
            EstadoOperacionDTO obtenerTodos = er.obtenerTodos();
            System.out.println("Metodo del ejercicio 2)c): " + obtenerTodos.getExito());
            System.out.println(obtenerTodos.getDatos());

            //2)d)
            EstadoOperacionDTO obtenerPorLibreta = er.obtenerPorLibreta(34978);
            System.out.println("Metodo del ejercicio 2)d): " + obtenerPorLibreta.getExito());
            System.out.println(obtenerPorLibreta.getDatos());

            //2)e)
            EstadoOperacionDTO obtenerTodosSegunGenero = er.obtenerTodosSegunGenero("Male");
            System.out.println("Metodo del ejercicio 2)e): " + obtenerTodosSegunGenero.getExito());
            System.out.println(obtenerTodosSegunGenero.getDatos());

            //2)f)
            EstadoOperacionDTO estudiantesInscriptosOrdenarPorCantidadDeInscriptos = cr.getCarrerasOrderByCantInscriptos();
            System.out.println("Metodo del ejercicio 2)f): " + estudiantesInscriptosOrdenarPorCantidadDeInscriptos.getExito());
            System.out.println(estudiantesInscriptosOrdenarPorCantidadDeInscriptos.getDatos());

            //2)g)
            EstadoOperacionDTO obtenerEstudiantesPorCarreraYCiudad = er.obtenerEstudiantesPorCarreraYCiudad(1,"Tandil");
            System.out.println("Metodo del ejercicio 2)g): " + obtenerEstudiantesPorCarreraYCiudad.getExito());
            System.out.println(obtenerEstudiantesPorCarreraYCiudad.getDatos());

            //3)
            EstadoOperacionDTO estadoOperacionDTO = cr.getReporte();
            System.out.println("Metodo del ejercicio 3: " +estadoOperacionDTO.getExito());
            System.out.println(estadoOperacionDTO.getDatos());

        } finally {
            em1.close();
            em2.close();
            em3.close();
        }
    }

}