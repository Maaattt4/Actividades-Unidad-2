package com.Universidad.SolicitudConstancia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Universidad.SolicitudConstancia.model.SolicitudConstanciaModel;

@Repository
public interface ISolicitudConstanciaRepository extends JpaRepository<SolicitudConstanciaModel, Long> {
}