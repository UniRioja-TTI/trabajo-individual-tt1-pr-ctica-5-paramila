package servicios;

import interfaces.InterfazContactoSim;
import modelo.DatosSimulation;
import modelo.DatosSolicitud;
import modelo.Entidad;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ImplementacionSimulacion implements InterfazContactoSim {
    
    private DatosSolicitud laSolicitudGuardada;
    private List<Entidad> listaDeCosas;

    public ImplementacionSimulacion() {
        List<Entidad> lista = new ArrayList<>();
        Entidad cosa1 = new Entidad(1, "Pablo","Prueba1");
        Entidad cosa2 = new Entidad(2, "Pedro","Prueba2");
        Entidad cosa3 = new Entidad(3, "Alejandro","Prueba3");
        lista.add(cosa1);
        lista.add(cosa2);
        lista.add(cosa3);
        this.listaDeCosas = lista;
    }

    public int solicitarSimulation(DatosSolicitud sol) {
        this.laSolicitudGuardada = sol;
        Random generador = new Random();
        int numeroAleatorio = generador.nextInt(1000);
        return numeroAleatorio;
    }

    public DatosSimulation descargarDatos(int ticket) {
        DatosSimulation misDatos = new DatosSimulation();
        return misDatos;
    }

    public List<Entidad> getEntities() {
        List<Entidad> lista = this.listaDeCosas;
        return lista;
    }

    public boolean isValidEntityId() {
        return true;
    }
}