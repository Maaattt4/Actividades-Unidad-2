package com.Universidad.SolicitudConstancia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Universidad.SolicitudConstancia.model.PagoModel;
import com.Universidad.SolicitudConstancia.model.SolicitudConstanciaModel;
import com.Universidad.SolicitudConstancia.service.ConstanciaService;

@RestController
@RequestMapping("/api/constancias")
public class ConstanciaController {

    @Autowired
    private ConstanciaService constanciaService;

    @PostMapping("/solicitar/{idAlumno}")
    public ResponseEntity<SolicitudConstanciaModel> solicitarNuevaConstancia(
            @PathVariable Long idAlumno,
            @RequestBody PagoModel informacionPago) {
        
        try {
            // Mandamos a llamar al Servicio de Tarea (El que tiene el @Transactional)
            SolicitudConstanciaModel nuevaSolicitud = constanciaService.solicitarConstancia(idAlumno, informacionPago);
            
            return ResponseEntity.status(201).body(nuevaSolicitud);
            
        } catch (RuntimeException e) {
            // Si la regla de negocio falla (ej. Alumno no inscrito), lanzamos un error 400 Bad Request
            return ResponseEntity.badRequest().build();
        }
    }
}