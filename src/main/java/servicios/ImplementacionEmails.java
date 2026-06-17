package servicios;

import interfaces.InterfazEnviarEmails;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.EmailApi;
import io.swagger.client.model.EmailResponse;
import modelo.Destinatario;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ImplementacionEmails implements InterfazEnviarEmails {

    private Logger elLogger;

    public ImplementacionEmails(Logger unLogger) {
        this.elLogger = unLogger;
    }

    public boolean enviarEmail(Destinatario dest, String mensaje) {
        ApiClient cliente = new ApiClient();
        cliente.setBasePath("http://servicio-consumible:8080");
        EmailApi emailApi = new EmailApi(cliente);
        String direccion = dest.getEmailAddress();
        try {
            EmailResponse respuesta = emailApi.emailPost(direccion, mensaje);
            if (respuesta != null && respuesta.isDone()) {
                elLogger.info("Email enviado correctamente a: " + direccion);
            } else {
                elLogger.warn("El servidor no confirmó el envío del email");
            }
        } catch (Exception e) {
            elLogger.error("Error al llamar a la API de email: " + e.getMessage());
        }
        return true;
    }
}
