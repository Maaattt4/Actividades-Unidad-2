package com.Universidad.SolicitudConstancia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Universidad.SolicitudConstancia.model.AlumnoModel;

@Repository
public interface IAlumnoRepository extends JpaRepository<AlumnoModel, Long> {

}