        package Sevices;
import model.Cliente;
import repository.ClienteRepository;
import src.sevices.ValidarCliente;

import java.util.UUID;
import java.util.List;
public class ServiceCliente {
private final ClienteRepository clienteRepository;
    public ServiceCliente (ClienteRepository clienteRepository) {
            this.clienteRepository = clienteRepository;
    }
public void cadastrarCliente(Cliente cliente) {
        ValidarCliente.validarCriacao(cliente.getNome (), cliente.getEmail(), cliente.getDocumento());
        clienteRepository.salvar(cliente);
}

                    
public Cliente buscarCliente(UUID id) {
            Cliente cliente = clienteRepository.buscarPorId(id);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }
    return clienteRepository.buscarPorId(id);
}
public void atualisarCliente(UUID id, String novoNome, String novoEmail) {
            Cliente cliente = buscarCliente(id);
        ValidarCliente.validarAtualizacao(novoNome, novoEmail);
        cliente.atualizarDados(novoNome, novoEmail);        

            //verifica se o cliente existe
 }
public List<Cliente> listarClientes(){
    return clienteRepository.listarTodos();
}

            

    }



