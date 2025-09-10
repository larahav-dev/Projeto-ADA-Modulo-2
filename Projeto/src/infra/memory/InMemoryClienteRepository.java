package infra.memory;

import domain.Cliente;
import ports.ClienteRepository;

import java.util.*;

public class InMemoryClienteRepository implements ClienteRepository {
    private final Map<UUID, Cliente> store = new HashMap<>();

    @Override
    public void save(Cliente cliente) {
        store.put(cliente.getId(), cliente);
    }

    @Override
    public Cliente findById(UUID id) {
        return store.get(id);
    }

    @Override
    public boolean existePorDocumento(String documento) {
        return store.values().stream().anyMatch(c -> c.getDocumento().equals(documento));
    }

    @Override
    public List<Cliente> findAll() {
        return new ArrayList<>(store.values());
    }
}
