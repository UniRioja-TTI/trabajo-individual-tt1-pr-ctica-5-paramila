package servicios;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Response;
import interfaces.InterfazContactoSim;
import io.swagger.client.ApiClient;
import modelo.DatosSimulation;
import modelo.DatosSolicitud;
import modelo.Entidad;
import modelo.Punto;
import io.swagger.client.api.ResultadosApi;
import io.swagger.client.api.SolicitudApi;
import io.swagger.client.model.ResultsResponse;
import io.swagger.client.model.Solicitud;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImplementacionSimulacion implements InterfazContactoSim {

    private DatosSolicitud laSolicitudGuardada;
    private List<Entidad> listaDeCosas;

    public ImplementacionSimulacion() {
        List<Entidad> lista = new ArrayList<>();
        lista.add(new Entidad(1, "Entidad1", "Prueba1"));
        lista.add(new Entidad(2, "Entidad2", "Prueba2"));
        lista.add(new Entidad(3, "Entidad3", "Prueba3"));
        this.listaDeCosas = lista;
    }
    @Override
    public int solicitarSimulation(DatosSolicitud sol) {
        this.laSolicitudGuardada = sol;

        ApiClient cliente = new ApiClient();
        cliente.setBasePath("http://servicio-consumible:8080");

        SolicitudApi solicitudApi = new SolicitudApi(cliente);
        int tokenReal = -1;
        try {
            Solicitud peticionSwagger = new Solicitud();
            Map<Integer, Integer> diccionarioNumeros = sol.getNums();
            for (Integer id : diccionarioNumeros.keySet()) {
                int cantidad = diccionarioNumeros.get(id);
                String nombre = "";
                for (Entidad e : this.listaDeCosas) {
                    if (e.getId() == id) {
                        nombre = e.getName();
                        break;
                    }
                }
                peticionSwagger.addNombreEntidadesItem(nombre);
                peticionSwagger.addCantidadesInicialesItem(cantidad);
            }

            try {
                solicitudApi.solicitudSolicitarPost(peticionSwagger, "Pablo");
            } catch (Exception e) {
                if(!e.getMessage().contains("expected")) {
                    System.err.println("Aviso al enviar: " + e.getMessage());
                }
            }
            List<Integer> misTickets = solicitudApi.solicitudGetSolicitudesUsuarioGet("Pablo");
            if (misTickets != null && !misTickets.isEmpty()) {
                tokenReal = misTickets.get(misTickets.size() - 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tokenReal;
    }

    @Override
    public DatosSimulation descargarDatos(int ticket) {
        DatosSimulation misDatos = new DatosSimulation();
        Map<Integer, List<Punto>> mapaPuntos = new HashMap<>();
        int maxTiempo = 0;

        ApiClient cliente = new ApiClient();
        cliente.setBasePath("http://servicio-consumible:8080");
        ResultadosApi resultadosApi = new ResultadosApi(cliente);
        try {
            Call llamada = resultadosApi.resultadosPostCall("Pablo", ticket, null, null);
            Response respuestaHttp = llamada.execute();
            String jsonBruto = respuestaHttp.body().string();
            JsonObject json = new JsonParser().parse(jsonBruto).getAsJsonObject();
            String textoBruto = json.get("data").getAsString();
            String[] lineas = textoBruto.split("\n");
            misDatos.setAnchoTablero(Integer.parseInt(lineas[0].trim()));
            for (int i = 1; i < lineas.length; i++) {
                String linea = lineas[i].trim();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split(",");
                    if (partes.length == 4) {
                        int tiempo = Integer.parseInt(partes[0].trim());
                        Punto p = new Punto();
                        p.setY(Integer.parseInt(partes[1].trim()));
                        p.setX(Integer.parseInt(partes[2].trim()));
                        p.setColor(partes[3].trim());
                        mapaPuntos.putIfAbsent(tiempo, new ArrayList<>());
                        mapaPuntos.get(tiempo).add(p);
                        if (tiempo > maxTiempo) {
                            maxTiempo = tiempo;
                        }
                    }
                }
            }

            for (int i = 0; i <= maxTiempo; i++) {
                mapaPuntos.putIfAbsent(i, new ArrayList<>());
            }
            misDatos.setPuntos(mapaPuntos);
            misDatos.setMaxSegundos(maxTiempo + 1);

        } catch (Exception e) {
            System.err.println("Error al descargar la cuadrícula: " + e.getMessage());
        }

        return misDatos;
    }

    public List<Entidad> getEntities() {
        return this.listaDeCosas;
    }

    public boolean isValidEntityId(int id) {
        return true;
    }
}