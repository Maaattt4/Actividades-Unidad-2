package com.Universidad.SolicitudConstancia.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "solicitudes_constancia")
public class SolicitudConstanciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long idSolicitud;

    // El estudiante que solicita la constancia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno", nullable = false)
    @JsonIgnoreProperties({"solicitudes", "pagos"})
    private AlumnoModel alumno;

    // Relación 1 a 1 con su recibo de pago
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pago")
    @JsonIgnoreProperties({"alumno"})
    private PagoModel pago;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoSolicitud estado;

    @Column(name = "fecha_solicitud", nullable = false, updatable = false)
    private LocalDateTime fechaSolicitud;

    // Enum que modela los pasos de tu proceso SOA (Actividad 1)
    public enum EstadoSolicitud {
        INICIADA,               // Inicia solicitarConstancia()
        INSCRIPCION_VERIFICADA, // Pasa verificarInscripcion()
        PAGADA,                 // Pasa pagoTramite()
        DOCUMENTO_GENERADO,     // Pasa generarDocumento()
        ENVIADA                 // Pasa enviarDocumento()
    }

    // Callback para que Spring Boot asigne la fecha automáticamente al crearla
    @PrePersist
    public void onPrePersist() {
        if (this.fechaSolicitud == null) {
            this.fechaSolicitud = LocalDateTime.now();
        }
    }

    public SolicitudConstanciaModel() {}

    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public AlumnoModel getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoModel alumno) {
        this.alumno = alumno;
    }

    public PagoModel getPago() {
        return pago;
    }

    public void setPago(PagoModel pago) {
        this.pago = pago;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    
}