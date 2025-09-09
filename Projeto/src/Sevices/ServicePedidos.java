package Sevices;

public class PedidoService {
    private PedidoRepository pedidoRepository;
    private EmailService emailService;

    public PedidoService(PedidoRepository repo, EmailService emailService) {
        this.pedidoRepository = repo;
        this.emailService = emailService;
    }

    public void criarPedido(String id, Cliente cliente) {
        Pedido pedido = new Pedido(id, cliente);
        pedidoRepository.salvar(pedido);
    }

    public void adicionarItem(String pedidoId, Produto produto, int quantidade, double precoVenda) {
        Pedido pedido = pedidoRepository.buscarPorId(pedidoId);
        pedido.adicionarItem(produto, quantidade, precoVenda);
        pedidoRepository.salvar(pedido);
    }

    public void removerItem(String pedidoId, String produtoId) {
        Pedido pedido = pedidoRepository.buscarPorId(pedidoId);
        pedido.removerItem(produtoId);
        pedidoRepository.salvar(pedido);
    }

    public void alterarQuantidade(String pedidoId, String produtoId, int novaQuantidade) {
        Pedido pedido = pedidoRepository.buscarPorId(pedidoId);
        pedido.alterarQuantidade(produtoId, novaQuantidade);
        pedidoRepository.salvar(pedido);
    }

    public void finalizarPedido(String pedidoId) {
        Pedido pedido = pedidoRepository.buscarPorId(pedidoId);
        pedido.finalizarPedido();
        emailService.enviar(pedido.getCliente().getEmail(), "Seu pedido está aguardando pagamento.");
        pedidoRepository.salvar(pedido);
    }

    public void realizarPagamento(String pedidoId) {
        Pedido pedido = pedidoRepository.buscarPorId(pedidoId);
        pedido.realizarPagamento();
        emailService.enviar(pedido.getCliente().getEmail(), "Pagamento confirmado.");
        pedidoRepository.salvar(pedido);
    }

    public void entregarPedido(String pedidoId) {
        Pedido pedido = pedidoRepository.buscarPorId(pedidoId);
        pedido.entregarPedido();
        emailService.enviar(pedido.getCliente().getEmail(), "Pedido entregue.");
        pedidoRepository.salvar(pedido);
    }
}
