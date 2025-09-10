package ports;

import domain.Pedido;

import java.util.List;

public interface PedidoRepository {

    void save(Pedido pedido);

    Pedido findById(String id);

    List<Pedido> findAll();
}
