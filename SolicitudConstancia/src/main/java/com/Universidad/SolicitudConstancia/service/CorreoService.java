package com.Universidad.SolicitudConstancia.service;

import org.springframework.stereotype.Service;

@Service
public class CorreoService {

    public void enviarDocumento(String correoDestino, String rutaDocumento) {
        System.out.println("Enviando el documento " + rutaDocumento + " al correo: " + correoDestino);
    }
}