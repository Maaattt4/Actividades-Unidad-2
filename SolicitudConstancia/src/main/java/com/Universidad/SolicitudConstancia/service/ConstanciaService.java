package com.Universidad.SolicitudConstancia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Universidad.SolicitudConstancia.model.AlumnoModel;
import com.Universidad.SolicitudConstancia.model.PagoModel;
import com.Universidad.SolicitudConstancia.model.SolicitudConstanciaModel;
import com.Universidad.SolicitudConstancia.repositories.ISolicitudConstanciaRepository;

@Service
public class ConstanciaService {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private PagoService pagoService;

    @Autowired
    private CorreoService correoService;

    @Autowired
    private ISolicitudConstanciaRepository solicitudRepository;

    // Tarea secundaria: generarDocumento()
    public String generarDocumento(AlumnoModel alumno) {
        // Aquí iría una librería como iText o PDFBox para crear el archivo
        String nombreArchivo = "Constancia_" + alumno.getMatricula() + ".pdf";
        System.out.println("Generando " + nombreArchivo + "...");
        return "/archivos/pdf/" + nombreArchivo;
    }

    // Tarea principal coordinadora: solicitarConstancia()
    @Transactional
    public SolicitudConstanciaModel solicitarConstancia(Long idAlumno, PagoModel informacionPago) {
        
        SolicitudConstanciaModel solicitud = new SolicitudConstanciaModel();
        AlumnoModel alumno = alumnoService.obtenerAlumno(idAlumno);
        solicitud.setAlumno(alumno);
        solicitud.setEstado(SolicitudConstanciaModel.EstadoSolicitud.INICIADA);

        // PASO 1: verificarInscripcion() -> Llamamos a la Entidad
        if (!alumnoService.verificarInscripcion(idAlumno)) {
            throw new RuntimeException("AlumnoNoInscrito");
        }
        solicitud.setEstado(SolicitudConstanciaModel.EstadoSolicitud.INSCRIPCION_VERIFICADA);

        // PASO 2: pagoTramite() -> Llamamos a la Entidad
        informacionPago.setAlumno(alumno);
        PagoModel pagoRealizado = pagoService.registrarPago(informacionPago);
        solicitud.setPago(pagoRealizado);
        solicitud.setEstado(SolicitudConstanciaModel.EstadoSolicitud.PAGADA);

        // PASO 3: generarDocumento() -> Tarea interna
        String rutaDocumento = generarDocumento(alumno);
        solicitud.setEstado(SolicitudConstanciaModel.EstadoSolicitud.DOCUMENTO_GENERADO);

        // PASO 4: enviarDocumento() -> Llamamos a la Utilidad
        correoService.enviarDocumento(alumno.getCorreo(), rutaDocumento);
        solicitud.setEstado(SolicitudConstanciaModel.EstadoSolicitud.ENVIADA);

        // Finalmente, guardamos el registro de toda la trazabilidad
        return solicitudRepository.save(solicitud);
    }
}