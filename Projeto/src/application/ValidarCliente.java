    package src.sevices;


public class ValidarCliente {
    public static void validarCriacao(String nome, String documento, String email) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório!");
        }
        if (documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("Documento é obrigatório!");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido!");
        }
    }

    public static void validarAtualizacao(String nome, String email) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório!");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido!");
        }
    }
}
