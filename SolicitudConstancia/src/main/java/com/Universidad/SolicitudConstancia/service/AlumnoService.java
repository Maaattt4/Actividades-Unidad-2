package com.Universidad.SolicitudConstancia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.Universidad.SolicitudConstancia.model.AlumnoModel;
import com.Universidad.SolicitudConstancia.repositories.IAlumnoRepository;

@Service
public class AlumnoService {

    @Autowired
    private IAlumnoRepository alumnoRepository;

    public boolean verificarInscripcion(Long idAlumno) {
        Optional<AlumnoModel> alumnoOpt = alumnoRepository.findById(idAlumno);
        
        if (alumnoOpt.isPresent()) {
            return alumnoOpt.get().getEstaInscrito();
        } else {
            throw new RuntimeException("AlumnoNoExiste");
        }
    }
    
    public AlumnoModel obtenerAlumno(Long idAlumno) {
        return alumnoRepository.findById(idAlumno)
            .orElseThrow(() -> new RuntimeException("AlumnoNoExiste"));
    }
}