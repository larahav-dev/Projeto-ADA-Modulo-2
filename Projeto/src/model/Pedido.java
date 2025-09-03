package model;
        
import java.time.LocalDate;

public class Pedido {
    private String id;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private LocalDate dataCriacao;
    private StatusPedido status;
    private StatusPagamento statusPagamento;
    }