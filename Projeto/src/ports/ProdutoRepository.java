package ports;

import domain.Produto;
import java.util.List;

public interface ProdutoRepository {

    void save(Produto produto);

    Produto findById(Integer id);

    List<Produto> findAll();

    Integer nextId();
}
