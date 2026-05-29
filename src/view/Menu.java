package view;

import java.util.Scanner;

import model.Cliente;
import service.ClienteService;

public class Menu {

    private Scanner scanner;
    private ClienteService service;

    public Menu() {
        scanner = new Scanner(System.in);
        service = new ClienteService();
    }

    public void iniciar() {

        if (!fazerLogin()) {
            System.out.println("Login inválido.");
            return;
        }

        int opcao;

        do {

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Consultar cliente");
            System.out.println("3 - Contagem por sexo");
            System.out.println("4 - Listar clientes por faixa etária");
            System.out.println("5 - Cliente mais jovem");
            System.out.println("6 - Cliente mais velho");
            System.out.println("7 - Excluir cliente");
            System.out.println("8 - Atualizar cliente");
            System.out.println("9 - Listar todos clientes");
            System.out.println("10 - Sair");

            opcao = lerInteiro(">: ");

            switch (opcao) {

                case 1:
                    cadastrarCliente();
                    break;

                case 2:
                    consultarCliente();
                    break;

                case 3:
                    contagemSexo();
                    break;

                case 4:
                    listarFaixaEtaria();
                    break;

                case 5:
                    clienteMaisNovo();
                    break;

                case 6:
                    clienteMaisVelho();
                    break;

                case 7:
                    excluirCliente();
                    break;

                case 8:
                    atualizarCliente();
                    break;

                case 9:
                    listarClientes();
                    break;

                case 10:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 10);
    }

    private boolean fazerLogin() {

        System.out.println("===== LOGIN =====");

        int numero = lerInteiro("Número: ");

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        return service.login(numero, senha);
    }

    private void cadastrarCliente() {

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        int idade = lerInteiro("Idade: ");

        char sexo = lerSexo();

        double saldo = lerDouble("Saldo: ");

        boolean resultado = service.cadastrarCliente(
                nome,
                cpf,
                idade,
                sexo,
                saldo
        );

        if (resultado) {
            System.out.println("Cliente cadastrado com sucesso.");
        } else {
            System.out.println("Erro ao cadastrar cliente.");
        }
    }

    private void consultarCliente() {

        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();

        Cliente cliente = service.buscarCliente(nome);

        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private void contagemSexo() {

        System.out.println("Masculino: "
                + service.contarMasculino());

        System.out.println("Feminino: "
                + service.contarFeminino());
    }

    private void listarFaixaEtaria() {

        int min = lerInteiro("Idade mínima: ");
        int max = lerInteiro("Idade máxima: ");

        service.listarFaixaEtaria(min, max);
    }

    private void clienteMaisNovo() {

        Cliente cliente = service.clienteMaisNovo();

        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("Nenhum cliente cadastrado.");
        }
    }

    private void clienteMaisVelho() {

        Cliente cliente = service.clienteMaisVelho();

        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("Nenhum cliente cadastrado.");
        }
    }

    private void excluirCliente() {

        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();

        if (!service.clienteExiste(nome)) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        boolean resultado = service.removerCliente(nome);

        if (resultado) {
            System.out.println("Cliente removido.");
        }
    }

    private void atualizarCliente() {

        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();

        if (!service.clienteExiste(nome)) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        int idade = lerInteiro("Nova idade: ");

        char sexo = lerSexo();

        double saldo = lerDouble("Novo saldo: ");

        boolean resultado = service.atualizarCliente(
                nome,
                idade,
                sexo,
                saldo
        );

        if (resultado) {
            System.out.println("Cliente atualizado.");
        } else {
            System.out.println("Erro ao atualizar cliente.");
        }
    }
    
    private void listarClientes() {
        service.listarClientes();
    }

    private int lerInteiro(String msg) {

        while (true) {

            System.out.print(msg);

            if (scanner.hasNextInt()) {

                int valor = scanner.nextInt();
                scanner.nextLine();

                return valor;
            }

            System.out.println("Digite um número inteiro válido.");
            scanner.nextLine();
        }
    }

    private double lerDouble(String msg) {

        while (true) {

            System.out.print(msg);

            if (scanner.hasNextDouble()) {

                double valor = scanner.nextDouble();
                scanner.nextLine();

                return valor;
            }

            System.out.println("Digite um valor numérico válido.");
            scanner.nextLine();
        }
    }

    private char lerSexo() {

        while (true) {

            System.out.print("Sexo (M/F): ");

            String entrada = scanner.nextLine().toUpperCase();

            if (!entrada.isEmpty()) {

                char sexo = entrada.charAt(0);

                if (sexo == 'M' || sexo == 'F') {
                    return sexo;
                }
            }

            System.out.println("Digite apenas M ou F.");
        }
    }
}