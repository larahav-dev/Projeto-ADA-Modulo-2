        package repository;

import model.Cliente;
import java.util.*;
import java.util.UUID;

public class ClienteRepository {
    private final Map<UUID, Cliente> clientes = new HashMap<>();
//essa é uma classe que armazena os clientes em um mapa onde a chave é o id do cliente, e o valor é objeto cliente
    public void salvar(Cliente cliente) {
        clientes.put(cliente.getId(), cliente);
    }

    public Cliente buscarPorId(UUID id) {
        return clientes.get(id);
    }

    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes.values());
    }

}
