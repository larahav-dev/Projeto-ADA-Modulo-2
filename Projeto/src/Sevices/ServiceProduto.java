package Sevices;
import model.Produto;
import repository.ProdutoRepository;
import java.math.BigDecimal;
import java.util.List;

public class ServiceProduto {
private final ProdutoRepository produtoRepository;

public ServiceProduto(ProdutoRepository produtoRepository) {
    this.produtoRepository= produtoRepository; }

public void cadastrarProduto(Produto produto){
    ValidacaoProduto.validarProduto(produto.getNomeProduto(), produto.getDescricaoProduto());
    produtoRepository.novoProduto(produto);}

        public Produto pesquisarProduto (Integer idContador){
        Produto produto = produtoRepository.pesquisarProduto(idContador);
        if(produto == null){
           System.out.println("Produto não encontrado/Produto não cadastrado!");
        } return produtoRepository.pesquisarProduto(idContador); }

        public void atualizarProduto(Integer idContador, String atualizarProduto, String atualizarDescricao, BigDecimal atualizarPrecoProduto ){
        Produto produto = pesquisarProduto(idContador);
        ValidacaoProduto.validarProdutoAtualizado(atualizarProduto,atualizarDescricao, atualizarPrecoProduto );
        produto.atualizarProduto(atualizarProduto,atualizarDescricao,atualizarPrecoProduto);}


    //listar todos os produtos
        public List<Produto> todosProdutos(){
        return produtoRepository.todosProdutos();
        }
}
