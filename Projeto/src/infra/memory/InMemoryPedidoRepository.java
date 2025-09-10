package infra.memory;

import domain.Pedido;
import ports.PedidoRepository;

import java.util.*;

public class InMemoryPedidoRepository implements PedidoRepository {
    private final Map<String, Pedido> store = new LinkedHashMap<>();

    @Override
    public void save(Pedido pedido) {
        store.put(pedido.getId(), pedido);
    }

    @Override
    public Pedido findById(String id) {
        return store.get(id);
    }

    @Override
    public List<Pedido> findAll() {
        return new ArrayList<>(store.values());
    }
}
