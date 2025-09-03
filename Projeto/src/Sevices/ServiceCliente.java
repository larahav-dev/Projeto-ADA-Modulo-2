        package Sevices;
import model.Cliente;
import repository.ClienteRepository;
import java.util.UUID;
import java.util.List;
public class ServiceCliente {
private final ClienteRepository clienteRepository;
    public ServiceCliente (ClienteRepository clienteRepository) {
            this.clienteRepository = clienteRepository;
    }
public void cadastrarCliente(Cliente cliente) {
        clienteRepository.salvar(cliente);
}

                    
public Cliente buscarCliente(UUID id) {
    return clienteRepository.buscarPorId(id);
}
public void atualisarCliente(UUID id, String novoNome, String novoEmail) {
            Cliente cliente = buscarCliente(id);
            //verifica se o cliente existe
 }
public List<Cliente> listarClientes(){
    return clienteRepository.listarTodos();
}

            

    }



