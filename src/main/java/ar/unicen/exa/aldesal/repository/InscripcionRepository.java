package ar.unicen.exa.aldesal.repository;

import ar.unicen.exa.aldesal.dto.EstadoOperacionDTO;
import ar.unicen.exa.aldesal.dto.EstudianteDTO;
import ar.unicen.exa.aldesal.dto.InscripcionDTO;
import ar.unicen.exa.aldesal.model.Inscripcion;

public interface InscripcionRepository {
    public void insertarDesdeCSV(String rutaArchivo);
    public EstadoOperacionDTO<InscripcionDTO> matricular(Inscripcion inscripcion);
}
