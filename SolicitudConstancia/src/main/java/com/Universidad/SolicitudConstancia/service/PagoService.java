package com.Universidad.SolicitudConstancia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Universidad.SolicitudConstancia.model.PagoModel;
import com.Universidad.SolicitudConstancia.repositories.IPagoRepository;

@Service
public class PagoService {

    @Autowired
    private IPagoRepository pagoRepository;

    public PagoModel registrarPago(PagoModel pago) {
        pago.setEstado(PagoModel.EstadoPago.COMPLETADO);
        
        // REQUISITO DEL PROFESOR: Imprimir la confirmación como un Log
        System.out.println("=========================================");
        System.out.println("LOG DE SISTEMA - CONFIRMACIÓN DE PAGO");
        System.out.println("Se ha confirmado un pago por la cantidad de: $" + pago.getMonto());
        System.out.println("Estado de la transacción: " + pago.getEstado());
        System.out.println("=========================================");
        
        return pagoRepository.save(pago);
    }
}