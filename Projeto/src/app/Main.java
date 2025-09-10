package app;

import application.ClienteService;
import application.PedidoService;
import application.ProdutoService;
import domain.Cliente;
import domain.Produto;
import domain.pricing.DefaultPricingPolicy;
import domain.validation.ClienteValidator;
import domain.validation.ProdutoValidator;
import infra.EmailNotifier;
import infra.memory.InMemoryClienteRepository;
import infra.memory.InMemoryPedidoRepository;
import infra.memory.InMemoryProdutoRepository;
import ports.Notifier;
import ports.ClienteRepository;
import ports.PedidoRepository;
import ports.ProdutoRepository;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        // Ports (interfaces) + Adapters (implementações)
        ClienteRepository clienteRepo = new InMemoryClienteRepository();
        ProdutoRepository produtoRepo = new InMemoryProdutoRepository();
        PedidoRepository pedidoRepo  = new InMemoryPedidoRepository();
        Notifier notifier = new EmailNotifier();

        // Validadores
        ClienteValidator clienteValidator = new ClienteValidator(clienteRepo);
        ProdutoValidator produtoValidator = new ProdutoValidator();

        // Application services (casos de uso)
        ClienteService clienteService = new ClienteService(clienteRepo, clienteValidator);
        ProdutoService produtoService = new ProdutoService(produtoRepo, produtoValidator);
        PedidoService pedidoService   = new PedidoService(pedidoRepo, notifier, new DefaultPricingPolicy());

        // Fluxo de exemplo
        Cliente c = clienteService.cadastrar("Larah", "larah@email.com", "37637105822");
        Produto p = produtoService.cadastrar("Livro Java", "Iniciantes", new BigDecimal("100.00"));

        String pedidoId = pedidoService.criarPedido(c);
        pedidoService.adicionarItem(pedidoId, p, 2, new BigDecimal("90.00")); // preço negociado
        pedidoService.alterarQuantidade(pedidoId, p.getId(), 3);
        pedidoService.finalizarPedido(pedidoId);
        pedidoService.realizarPagamento(pedidoId);
        pedidoService.entregarPedido(pedidoId);

        System.out.println("Fluxo concluído com sucesso!");
    }
}