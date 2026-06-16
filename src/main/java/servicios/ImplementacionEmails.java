package servicios;

import interfaces.InterfazEnviarEmails;
import modelo.Destinatario;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ImplementacionEmails implements InterfazEnviarEmails {
    
    private Logger elLogger;

    public ImplementacionEmails(Logger unLogger) {
        this.elLogger = unLogger;
    }

    public boolean enviarEmail(Destinatario dest, String email) {
        String textoAviso = email;
        this.elLogger.info(textoAviso);
        return true;
    }
}