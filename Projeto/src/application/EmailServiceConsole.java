package application;

public class EmailServiceConsole implements EmailService {
    public void enviar(String destinatario, String mensagem) {
        System.out.println("Email para " + destinatario + ": " + mensagem);
    }
}