package model;
import java.math.BigDecimal;

public class Produto {

    private int idContador;
    private String nomeProduto;
    private String descricaoProduto;
    private BigDecimal precoProduto;

    public Produto(String nomeProduto, String descricaoProduto, BigDecimal precoProduto) {

        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.precoProduto = precoProduto;

    }
//get's
    public int getIdContador() {
        return idContador;
    }
    public String getNomeProduto() {
        return nomeProduto;
    }
    public String getDescricaoProduto() {
        return descricaoProduto;
    }
    public BigDecimal getPrecoProduto() {
        return precoProduto;
    }
//set's
    public void setPrecoProduto(BigDecimal precoProduto) {
        if (precoProduto != null) { // Validação para não ter produtos com preço zero
            this.precoProduto = precoProduto;
        } else {
            System.out.println("Preço inválido, insira um valor acima de R$0");
        }
    }

    public void incrementarContador() {
        // Obter o valor atual
        int valorAtual = this.getIdContador();
        //Incrementar o valor
        valorAtual++;
       this.idContador = valorAtual;
    }
}
