package com.Universidad.SolicitudConstancia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Universidad.SolicitudConstancia.model.PagoModel;

@Repository
public interface IPagoRepository extends JpaRepository<PagoModel, Long> {
}