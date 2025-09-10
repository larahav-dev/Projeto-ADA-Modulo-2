package infra.memory;

import domain.Produto;
import ports.ProdutoRepository;

import java.util.*;

public class InMemoryProdutoRepository implements ProdutoRepository {

    private final Map<Integer, Produto> store = new LinkedHashMap<>();
    private int sequenciaId = 1;

    @Override
    public void save(Produto produto) {
        if (produto.getId() == null) {
            produto.definirId(nextId());
        }
        store.put(produto.getId(), produto);
    }

    @Override
    public Produto findById(Integer id) {
        return store.get(id);
    }

    @Override
    public List<Produto> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Integer nextId() {
        return sequenciaId++;
    }
}
