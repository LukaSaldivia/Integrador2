package ar.unicen.exa.aldesal;

import ar.unicen.exa.aldesal.factory.JPAUtil;
import ar.unicen.exa.aldesal.model.Estudiante;
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
        } finally {
            em1.close();
            em2.close();
            em3.close();
        }
    }
}