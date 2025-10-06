package ar.unicen.exa.aldesal.repository;


import ar.unicen.exa.aldesal.dto.EstadoOperacionDTO;
import ar.unicen.exa.aldesal.dto.EstudianteDTO;
import ar.unicen.exa.aldesal.model.Estudiante;

import java.util.List;

public interface EstudianteRepository {
    public void insertarDesdeCSV(String rutaArchivo);
    public EstadoOperacionDTO<EstudianteDTO> guardar(Estudiante estudiante);
    public EstadoOperacionDTO<List<EstudianteDTO>> obtenerTodos();
    public EstadoOperacionDTO<EstudianteDTO> obtenerPorLibreta(Integer nroLibreta);
    public EstadoOperacionDTO<List<EstudianteDTO>> obtenerTodosSegunGenero(String genero);
    public EstadoOperacionDTO<List<EstudianteDTO>>  obtenerEstudiantesPorCarreraYCiudad(Integer id_carrera, String id_ciudad);

}
