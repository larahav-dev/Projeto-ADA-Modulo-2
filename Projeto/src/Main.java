//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PedidoRepository repo = new PedidoRepositoryMemoria();
        EmailService email = new EmailServiceConsole();
        PedidoService service = new PedidoService(repo, email);

        Cliente cliente = new Cliente("1", "Larah", "12345678900", "larah@email.com");
        Produto produto = new Produto("p1", "Livro Java", 100.0);

        service.criarPedido("pedido1", cliente);
        service.adicionarItem("pedido1", produto, 2, 90.0);
        service.alterarQuantidade("pedido1", "p1", 3);
        service.finalizarPedido("pedido1");
        service.realizarPagamento("pedido1");
        service.entregarPedido("pedido1");
    }
}
