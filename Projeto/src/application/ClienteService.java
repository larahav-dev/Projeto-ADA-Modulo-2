package application;

import domain.Cliente;
import domain.validation.ClienteValidator;
import ports.ClienteRepository;

import java.util.List;
import java.util.UUID;

public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteValidator clienteValidator;

    public ClienteService(ClienteRepository clienteRepository, ClienteValidator clienteValidator) {
        this.clienteRepository = clienteRepository;
        this.clienteValidator = clienteValidator;
    }

    public Cliente cadastrar(String nome, String email, String documento) {
        Cliente cliente = new Cliente(nome, email, documento);
        clienteValidator.validarNovo(cliente); // valida CPF e duplicidade
        clienteRepository.save(cliente);
        return cliente;
    }

    public Cliente atualizar(UUID id, String nome, String email) {
        Cliente cliente = buscarOuFalhar(id);
        clienteValidator.validarAtualizacao(nome, email); // garante dados válidos na atualização
        cliente.atualizar(nome, email);
        clienteRepository.save(cliente);
        return cliente;
    }

    public Cliente buscar(UUID id) {
        return buscarOuFalhar(id);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    // Método utilitário para evitar repetição de código
    private Cliente buscarOuFalhar(UUID id) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado para o ID: " + id);
        }
        return cliente;
    }
}
