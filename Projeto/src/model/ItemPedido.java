package model;
import java.math.BigDecimal;

public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private BigDecimal precoVenda;
     // Métodos para alterar quantidade e calcular subtotal

    public ItemPedido(Produto produto, int quantidade, BigDecimal precoVenda) {
        this.produto= produto;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    public int getQuantidade() {
        return quantidade;}

    // Metodo para definir a quantidade e recalcular o subtotal
    public void setQuantidade(int novaQuantidade) {
        if (novaQuantidade >= 0) { // Garante que a quantidade seja não negativa
            this.quantidade = novaQuantidade;
            this.precoVenda = calcularPrecoVenda(); // Recalcula o subtotal quando a quantidade é alterada
        } else {
            System.out.println("A quantidade não pode ser negativa.");}
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }
    public void mostrarPreco() {
        // Acessa o precoProduto através do metodo getter
        BigDecimal precoProd = produto.getPrecoProduto();}

        // Metodo para calcular o subtotal
       private BigDecimal calcularPrecoVenda () {
        
           // Acessa o precoProduto através do metodo getter
           BigDecimal precoProd = produto.getPrecoProduto();
            BigDecimal multiplicador = new BigDecimal(quantidade);
            BigDecimal precoVenda = precoProd.multiply(multiplicador);
            return precoVenda;}
    }


