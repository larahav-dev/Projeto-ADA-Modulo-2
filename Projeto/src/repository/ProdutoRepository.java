package repository;
import model.Produto;

import java.util.*;

public class ProdutoRepository {
        private final Map<Integer, Produto> produtos  = new LinkedHashMap<>();

        public void novoProduto(Produto produto){
            produtos.put(produto.getIdContador(), produto);}

        public Produto pesquisarProduto(int idContador){
            return produtos.get(idContador);}

        public List<Produto> todosProdutos() {
            return new ArrayList<>(produtos.values());}
    }

