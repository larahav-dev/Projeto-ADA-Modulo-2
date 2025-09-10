package infra;

import ports.Notifier;

public class EmailNotifier implements Notifier {
    @Override
    public void notify(String destinatario, String mensagem) {
        System.out.println("Email => " + destinatario + " | " + mensagem);
    }
}
