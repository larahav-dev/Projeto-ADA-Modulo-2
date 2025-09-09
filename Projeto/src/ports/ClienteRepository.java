package ports;

import domain.Cliente;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository {

    void save(Cliente cliente);

    Cliente findById(UUID id);

    boolean existePorDocumento(String documento);

    List<Cliente> findAll();
}
