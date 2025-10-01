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
import ports.ClienteRepository;
import ports.Notifier;
import ports.PedidoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    private static final Scanner scCadastro = new Scanner(System.in);

    public static void main(String[] args) {
        // Ports (interfaces) + Adapters (implementações)
        InMemoryClienteRepository clienteRepo = new InMemoryClienteRepository();
        InMemoryProdutoRepository produtoRepo = new InMemoryProdutoRepository();
        PedidoRepository pedidoRepo  = new InMemoryPedidoRepository();
        Notifier notifier = new EmailNotifier();

        // Validadores
        ClienteValidator clienteValidator = new ClienteValidator(clienteRepo);
        ProdutoValidator produtoValidator = new ProdutoValidator();

        // Application services (casos de uso)
        ClienteService clienteService = new ClienteService(clienteRepo, clienteValidator);
        ProdutoService produtoService = new ProdutoService(produtoRepo, produtoValidator);
        PedidoService pedidoService   = new PedidoService(pedidoRepo, notifier, new DefaultPricingPolicy());


        int escolha;
        //controle das funções do catalogo de compras
        do {
            System.out.println("\n Bem vindo ao E-Commerce Ada! Selecione a opção que melhor lhe atender!");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Cadastrar produto");
            System.out.println("3. Atualizar cliente");
            System.out.println("4. Aualizar produto");
            System.out.println("5. Listar clientes");
            System.out.println("6. Listar produtos");
            System.out.println("7. Iniciar novo pedido");
            System.out.println("8. Realizar pagamento");
            System.out.println("9. Entregar pedido");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            Scanner sc = new Scanner(System.in);
            escolha = Integer.parseInt(sc.nextLine());
            System.out.println("Opção escolhida:" + escolha);

            switch (escolha) {
                case 1 -> cadastrarCliente(clienteService);
                case 2 -> cadastrarProduto(produtoService);
                case 3 -> atualizarCliente(clienteService);
                case 4 -> atualizarProduto(produtoService);
                case 5 -> listarClientes(clienteRepo);
                case 6 -> listarProdutos(produtoRepo);
                case 7 -> novoPedido(clienteService, produtoService,pedidoService);
                case 8 -> pagamento(pedidoService);
                case 9 -> entrega(pedidoService);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (escolha != 0);}


    public static void cadastrarCliente(ClienteService clienteService) {

        //Faz o cadastro de um novo cliente
        System.out.println("\n ---Cadastro de um novo Cliente--- \n ---Digite os seguites dados:");
        System.out.println("Nome: ");
        String nome = scCadastro.nextLine();
        System.out.println("Email: ");
        String email = scCadastro.nextLine();
        System.out.println("Documento (CPF): ");
        String documento = scCadastro.nextLine();

       Cliente cliente = clienteService.cadastrar(nome, email, documento);
       System.out.println(cliente);
    }

    public static void cadastrarProduto(ProdutoService produtoService) {
        //Faz o cadastro de um novo produto
        System.out.println("\n ---Cadastro de um novo Produto--- \n ---Digite as seguintes informações:");
        System.out.print("Nome do produto: ");
        String nome = scCadastro.nextLine();

        System.out.print("Descrição:");
        String descricao = scCadastro.nextLine();

        System.out.print("Preço: ");
        BigDecimal preco = new BigDecimal(scCadastro.nextLine());

        Produto produto = produtoService.cadastrar(nome, descricao, preco);
        System.out.println(produto);
    }

    public static void atualizarCliente(ClienteService clienteService) {

        System.out.println("\n --Atualização de Clientes--- \n ---Digite os seguites dados:");
        System.out.print("Id do Cliente: ");
        UUID id;
        try {
            id = UUID.fromString(scCadastro.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("ID inválido. Reinicie o processo.");
            return;
        }
        Cliente buscar= clienteService.buscar(id);
        System.out.println(buscar);
        //TODO permitir com que o campo em branco demonstre que não será feita alteração em algum campo
        System.out.println("Novo nome: ");
        String novoNome = scCadastro.nextLine();

        System.out.println("Novo email: ");
        String novoEmail = scCadastro.nextLine();

        Cliente cliente = clienteService.atualizar(id, novoNome, novoEmail);
        System.out.println(cliente);

    }
    public static void atualizarProduto(ProdutoService produtoService) {
        System.out.println("\n --Atualização de Produtos--- \n ---Digite os seguites dados:");
        System.out.print("Id do Produto: ");
        int id;
        try {
            id = Integer.parseInt(scCadastro.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("ID inválido. Reinicie o processo.");
            return;
        }
        Produto buscar = produtoService.buscar(id);
        System.out.println(buscar);
        //TODO permitir com que o campo em branco demonstre que não será feita alteração em algum campo
        System.out.println("Novo nome: ");
        String novoNomeProd = scCadastro.nextLine();

        System.out.println("Nova descrição: ");
        String novaDescricaoProd = scCadastro.nextLine();

        System.out.println("Novo Preço: ");
        BigDecimal novoPreco = new BigDecimal(scCadastro.nextLine());

       Produto produtoatt = produtoService.atualizar(id, novoNomeProd, novaDescricaoProd, novoPreco);
       System.out.println(produtoatt);
    }
    public static void listarClientes(InMemoryClienteRepository clienteRepo) {
            List<Cliente> listaDeClientes = clienteRepo.findAll();

            System.out.println("--- Lista de Clientes ---");
            for (Cliente cliente : listaDeClientes) {
                System.out.println(cliente);

            }
        }
    public static void listarProdutos(InMemoryProdutoRepository produtoRepo) {
        List<Produto> listaDeProdutos = produtoRepo.findAll();

        System.out.println("--- Produtos cadastrados ---");
        for (Produto produto : listaDeProdutos) {
            System.out.println(produto);
        }
    }
    public static void novoPedido(ClienteService clienteService, ProdutoService produtoService, PedidoService pedidoService) {
        System.out.println("\n--- Novo Pedido ---");

        // Selecionar cliente
        System.out.print("Digite o ID do cliente (UUID): ");
        UUID clienteId;
        try {
            clienteId = UUID.fromString(scCadastro.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("UUID inválido.");
            return;
        }

        Cliente cliente = clienteService.buscar(clienteId);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        //  pedido
        String pedidoId = pedidoService.criarPedido(cliente);
        System.out.println("Pedido criado com ID: " + pedidoId);

        boolean adicionando = true;
        while (adicionando) {
            System.out.println("\n--- Menu do Pedido ---");
            System.out.println("1. Adicionar produto");
            System.out.println("2. Alterar quantidade de produto");
            System.out.println("3. Remover produto");
            System.out.println("4. Finalizar pedido");
            System.out.println("0. Cancelar pedido");
            System.out.print("Escolha uma opção: ");
            String opcao = scCadastro.nextLine();

            switch (opcao) {
                case "1" -> {
                    System.out.print("ID do produto (int): ");
                    int produtoId;
                    try {produtoId = Integer.parseInt(scCadastro.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido.");
                        break;}

                    Produto produto = produtoService.buscar(produtoId);
                    if (produto == null) {
                        System.out.println("Produto não encontrado.");
                        break;}

                    System.out.print("Quantidade: ");
                    int quantidade;
                    try {
                        quantidade = Integer.parseInt(scCadastro.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Quantidade inválida.");
                        break;}

                    System.out.print("Preço negociado (ou deixe em branco para usar o padrão): ");
                    String precoStr = scCadastro.nextLine();
                    BigDecimal precoNegociado;
                    try {
                        precoNegociado = precoStr.isBlank()
                                ? produto.getPreco()
                                : new BigDecimal(precoStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Preço inválido.");
                        break;}

                    pedidoService.adicionarItem(pedidoId, produto, quantidade, precoNegociado);
                    System.out.println("Produto adicionado ao pedido.");}

                case "2" -> {
                    System.out.print("ID do produto a alterar (int): ");
                    int produtoId = Integer.parseInt(scCadastro.nextLine());

                    System.out.print("Nova quantidade: ");
                    int novaQuantidade = Integer.parseInt(scCadastro.nextLine());

                    pedidoService.alterarQuantidade(pedidoId, produtoId, novaQuantidade);
                    System.out.println("Quantidade atualizada.");
                }

                case "3" -> {
                    System.out.print("ID do produto a remover (int): ");
                    int produtoId = Integer.parseInt(scCadastro.nextLine());

                    pedidoService.removerItem(pedidoId, produtoId);
                    System.out.println("Produto removido.");
                }

                case "4" -> {
                    pedidoService.finalizarPedido(pedidoId);
                    System.out.println("Pedido finalizado com sucesso!");
                    adicionando = false;
                }

                case "0" -> {
                    System.out.println("Pedido cancelado.");
                    adicionando = false;
                }

                default -> System.out.println("Opção inválida.");
            }
        }
    }
    public static void pagamento(PedidoService pedidoService) {
        System.out.println("\n--- Realizar Pagamento ---");
        System.out.print("Digite o ID do pedido: ");
        String pedidoId = scCadastro.nextLine();

        try {
            pedidoService.realizarPagamento(pedidoId);
            System.out.println("Pagamento realizado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    public static  void entrega(PedidoService pedidoService) {
        System.out.println("\n--- Entregar Pedido ---");
        System.out.print("Digite o ID do pedido: ");
        String pedidoId = scCadastro.nextLine();

        try {
            pedidoService.entregarPedido(pedidoId);
            System.out.println("Pedido entregue com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}


