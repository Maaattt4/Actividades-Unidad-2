package com.Universidad.SolicitudConstancia.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pagos")
public class PagoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;

    // Conexión con el alumno que hace el pago
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno", nullable = false)
    @JsonIgnoreProperties({"pagos", "solicitudes"})
    private AlumnoModel alumno;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoPago estado;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    // Enum para controlar estrictamente el estado de la transacción
    public enum EstadoPago {
        PENDIENTE,
        COMPLETADO,
        RECHAZADO
    }

    public PagoModel() {}

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public AlumnoModel getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoModel alumno) {
        this.alumno = alumno;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public EstadoPago getEstado() {
        return estado;
    }

    public void setEstado(EstadoPago estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    
}