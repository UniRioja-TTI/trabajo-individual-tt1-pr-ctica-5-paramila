package modelo;

public class Destinatario {

    private String emailAddress;

    public Destinatario() {}

    public Destinatario(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
