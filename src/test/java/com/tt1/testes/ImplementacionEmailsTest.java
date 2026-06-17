package com.tt1.testes;

import servicios.ImplementacionEmails;
import modelo.Destinatario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImplementacionEmailsTest {

    @Test
    public void probarQueMandaCorreos() {
        Logger unLogger = LoggerFactory.getLogger(ImplementacionEmails.class);
        ImplementacionEmails servicio = new ImplementacionEmails(unLogger);
        Destinatario destino = new Destinatario();
        String unTexto = "hola";
        boolean resultado = servicio.enviarEmail(destino, unTexto);
        assertTrue(resultado);
    }
}
