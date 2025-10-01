package application;

import domain.Produto;
import domain.validation.ProdutoValidator;
import ports.ProdutoRepository;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoValidator produtoValidator;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoValidator produtoValidator) {
        this.produtoRepository = produtoRepository;
        this.produtoValidator = produtoValidator;
    }

    public Produto cadastrar(String nome, String descricao, BigDecimal preco) {
        Produto produto = new Produto(nome, descricao, preco);
        produtoValidator.validarNovo(produto);
        produtoRepository.save(produto);
        return produto;
    }

    public Produto atualizar(Integer id, String novoNomeProd, String novaDescricaoProd, BigDecimal novoPreco) {
        Produto produto = buscarOuFalhar(id);
        produtoValidator.validarAtualizacao(novoNomeProd, novaDescricaoProd, novoPreco);
        produto.atualizar(novoNomeProd, novaDescricaoProd, novoPreco);
        produtoRepository.save(produto);
        return produto;
    }

    public Produto buscar(Integer id) {
        return buscarOuFalhar(id);
    }

    // Método utilitário para evitar repetição de código
    private Produto buscarOuFalhar(Integer id) {
        Produto produto = produtoRepository.findById(id);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado para o ID: " + id);
        }
        return produto;
    }
}
