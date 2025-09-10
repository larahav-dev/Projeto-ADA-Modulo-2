package application;

import domain.Cliente;
import domain.Pedido;
import domain.Produto;
import domain.pricing.PricingPolicy;
import ports.Notifier;
import ports.PedidoRepository;

import java.math.BigDecimal;

public class PedidoService {
    private final PedidoRepository repo;
    private final Notifier notifier;
    private final PricingPolicy pricing;

    public PedidoService(PedidoRepository repo, Notifier notifier, PricingPolicy pricing) {
        this.repo = repo;
        this.notifier = notifier;
        this.pricing = pricing;
    }

    public String criarPedido(Cliente cliente) {
        String pedidoId = "PED-" + System.nanoTime();
        Pedido pedido = new Pedido(pedidoId, cliente);
        repo.save(pedido);
        return pedidoId;
    }

    public void adicionarItem(String pedidoId, Produto produto, int quantidade, BigDecimal precoUnitarioSolicitado) {
        Pedido pedido = obter(pedidoId);
        BigDecimal precoAplicado = pricing.apply(precoUnitarioSolicitado);
        pedido.adicionarItem(produto, quantidade, precoAplicado);
        repo.save(pedido);
    }

    public void removerItem(String pedidoId, int produtoId) {
        Pedido pedido = obter(pedidoId);
        pedido.removerItem(produtoId);
        repo.save(pedido);
    }

    public void alterarQuantidade(String pedidoId, int produtoId, int novaQuantidade) {
        Pedido pedido = obter(pedidoId);
        pedido.alterarQuantidade(produtoId, novaQuantidade);
        repo.save(pedido);
    }

    public void finalizarPedido(String pedidoId) {
        Pedido pedido = obter(pedidoId);
        pedido.finalizar();
        notifier.notify(pedido.getCliente().getEmail(), "Seu pedido está aguardando pagamento.");
        repo.save(pedido);
    }

    public void realizarPagamento(String pedidoId) {
        Pedido pedido = obter(pedidoId);
        pedido.pagar();
        notifier.notify(pedido.getCliente().getEmail(), "Pagamento confirmado.");
        repo.save(pedido);
    }

    public void entregarPedido(String pedidoId) {
        Pedido pedido = obter(pedidoId);
        pedido.entregar();
        notifier.notify(pedido.getCliente().getEmail(), "Pedido entregue.");
        repo.save(pedido);
    }

    private Pedido obter(String pedidoId) {
        Pedido pedido = repo.findById(pedidoId);
        if (pedido == null) throw new IllegalArgumentException("Pedido não encontrado");
        return pedido;
    }
}
