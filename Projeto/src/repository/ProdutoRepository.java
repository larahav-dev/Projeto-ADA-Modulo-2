package repository;
import model.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    public class ProdutoRepository {
        private final Map<Integer, Produto> produtos  = new HashMap<>();

        public void armazenar(Produto produto){
            produtos.put(produto.getIdContador(), produto);}

        public Produto pesquisarProduto(int idContador){
            return produtos.get(idContador);}

        public List<Produto> TodosProdutos() {
            return new ArrayList<>(produtos.values());}
    }

