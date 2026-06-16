package com.tt1.testes;

import servicios.ImplementacionSimulacion;
import modelo.DatosSolicitud;
import modelo.Entidad;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class ImplementacionSimulacionTest {

    @Test
    public void probarPedirSimulacion() {
        ImplementacionSimulacion servicio = new ImplementacionSimulacion();
        DatosSolicitud unaSolicitud = new DatosSolicitud();
        
        int elToken = servicio.solicitarSimulation(unaSolicitud);
        boolean esMayorQueCero = elToken >= 0;
        assertTrue(esMayorQueCero);
    }

    @Test
    public void probarConseguirEntidades() {
        ImplementacionSimulacion servicio = new ImplementacionSimulacion();
        
        List<Entidad> lista = servicio.getEntities();
        int cantidad = lista.size();
        boolean hayCosas = cantidad > 0;
        assertTrue(hayCosas);
    }
}