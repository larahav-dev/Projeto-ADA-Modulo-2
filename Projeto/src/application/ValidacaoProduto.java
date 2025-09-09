package application;
import java.math.BigDecimal;

public class ValidacaoProduto {
    /*Para verificar se uma string está "em branco" em Java
    (ou seja, vazia ou contendo apenas espaços em branco), devo usar o metodo isBlank()*/


public static void validarProduto(String nomeProduto, String descricaoProduto){
    if (nomeProduto==null || nomeProduto.isBlank()){
        System.out.println("Nome obrigatório, por favor, informe o nome do produto!");}

    if (descricaoProduto==null || descricaoProduto.isBlank()){
        System.out.println("Descrição obrigatória, por favor, informe a descrição do produto!");}
    }

public static void validarProdutoAtualizado(String atualizarProduto,String atualizarDescricao, BigDecimal atualizarPrecoProduto){

    if (atualizarProduto==null || atualizarProduto.isBlank()){
        System.out.println("Nome obrigatório, por favor, informe o nome do produto!");}

    if (atualizarDescricao==null || atualizarDescricao.isBlank()){
        System.out.println("Descrição obrigatória, por favor, informe a descrição do produto!");}

    if (atualizarPrecoProduto == null ){
        System.out.println("Preço inválido, insira um valor acima de R$0");}
    }
}
