package modelo;

public class Destinatario {

    private String email;

    public Destinatario() {}

    public Destinatario(String email) {
        this.email = email;
    }

    public String getEmailAddress() {
        return email;
    }

    public void setEmailAddress(String email) {
        this.email = email;
    }
}
