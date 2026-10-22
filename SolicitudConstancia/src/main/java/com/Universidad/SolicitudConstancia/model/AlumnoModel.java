package com.Universidad.SolicitudConstancia.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "alumnos")
public class AlumnoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    private Long idAlumno;

    @Column(nullable = false, length = 50)
    private String matricula;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String correo;

    // Atributo clave para la operación verificarInscripcion()
    @Column(name = "esta_inscrito", nullable = false)
    private Boolean estaInscrito; 

    // Relación: Un alumno puede tener muchos pagos
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PagoModel> pagos;

    // Relación: Un alumno puede pedir muchas constancias
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SolicitudConstanciaModel> solicitudes;

    public AlumnoModel() {}

    public Long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Boolean getEstaInscrito() {
        return estaInscrito;
    }

    public void setEstaInscrito(Boolean estaInscrito) {
        this.estaInscrito = estaInscrito;
    }

    public List<PagoModel> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoModel> pagos) {
        this.pagos = pagos;
    }

    public List<SolicitudConstanciaModel> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudConstanciaModel> solicitudes) {
        this.solicitudes = solicitudes;
    }

    
}