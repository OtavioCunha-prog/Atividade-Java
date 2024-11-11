//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package br.com.fecaf.controller;

import br.com.fecaf.model.Cliente;
import br.com.fecaf.model.Conta;
import java.util.Random;
import java.util.Scanner;

public class Menu {
    Scanner scanner;
    Conta referenciaConta;
    Cliente referenciaCliente;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.referenciaConta = new Conta();
        this.referenciaCliente = new Cliente();
    }

    public void executarMenu() {
        boolean exit = false;

        while(!exit) {
            System.out.println("/------------------------------------/");
            System.out.println("/------------ Banco FECAF -----------/");
            System.out.println("/------------------------------------/");
            System.out.println("/ 1 - Login                          /");
            System.out.println("/ 2 - Criar Conta                    /");
            System.out.println("/ 3 - Sair                           /");
            System.out.println("/------------------------------------/");
            System.out.println("Escolha uma opção: ");
            int userOption = this.scanner.nextInt();
            this.scanner.nextLine();
            switch (userOption) {
                case 1:
                    Login login = new Login();
                    Conta contaCliente = login.realizarLogin(this.referenciaConta, this.referenciaCliente);
                    if (contaCliente != null) {
                        this.acessarConta(contaCliente);
                    }
                    break;
                case 2:
                    Cliente cliente = new Cliente();
                    cliente.cadastrarCliente();
                    cliente.exibirInformacoes();
                    Conta conta = new Conta();
                    conta.criarConta(cliente);
                    conta.exibirPerfil();
                    this.referenciaCliente.adicionarClienteList(cliente);
                    this.referenciaConta.adicionarContaList(conta);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    exit = true;
                    break;
                default:
                    System.out.println("Escolha uma opção válida !");
                    this.promoverIdeias();
            }
        }

    }

    public void acessarConta(Conta conta) {
        boolean exitConta = false;

        while(!exitConta) {
            conta.exibirPerfil();
            System.out.println("/---------------------------------------/");
            System.out.println("/------------  Menu Conta --------------/");
            System.out.println("/---------------------------------------/");
            System.out.println("/ 1 - Consultar Saldo                   /");
            System.out.println("/ 2 - Realizar Deposito                 /");
            System.out.println("/ 3 - Realizar Saque                    /");
            System.out.println("/ 4 - Realizar Transferencia            /");
            System.out.println("/ 5 - Sair                              /");
            System.out.println("/---------------------------------------/");
            int userOption = this.scanner.nextInt();
            this.scanner.nextLine();
            switch (userOption) {
                case 1:
                    conta.consultarSaldo();
                    this.promoverIdeias();
                    break;
                case 2:
                    System.out.println("/------- Deposito ---------/");
                    System.out.print("Informe o Valor para Deposito: ");
                    double valorDeposito = this.scanner.nextDouble();
                    this.scanner.nextLine();
                    conta.realizarDeposito(valorDeposito);
                    conta.consultarSaldo();
                    this.promoverIdeias();
                    break;
                case 3:
                    System.out.println("/------- Saque ---------/");
                    System.out.print("Informe o Valor para Saque: ");
                    double valorSaque = this.scanner.nextDouble();
                    this.scanner.nextLine();
                    conta.realizarSaque(valorSaque);
                    conta.consultarSaldo();
                    this.promoverIdeias();
                    break;
                case 4:
                    System.out.println("/------- Transferência ---------/");
                    System.out.print("Informe o CPF do destinatário: ");
                    long cpfDestinatario = this.scanner.nextLong();
                    System.out.print("Informe o Valor de transferência: ");
                    double valorTransferencia = this.scanner.nextDouble();
                    Cliente clienteDestino = this.referenciaCliente.pesquisarCliente(cpfDestinatario);
                    if (clienteDestino != null) {
                        Conta contaDestino = this.referenciaConta.pesquisarConta(clienteDestino);
                        if (contaDestino != null) {
                            if (conta.realizarTransferencia(valorTransferencia)) {
                                contaDestino.realizarDeposito(valorTransferencia);
                                System.out.println("Transferência realizada com sucesso!");
                            } else {
                                System.out.println("Saldo insuficiente para realizar a transferência.");
                            }
                        } else {
                            System.out.println("Conta do destinatário não encontrada.");
                        }
                    } else {
                        System.out.println("Cliente destinatário não encontrado.");
                    }

                    conta.consultarSaldo();
                    this.promoverIdeias();
                    break;
                case 5:
                    exitConta = true;
                    break;
                default:
                    System.out.println("Escolha uma opção válida!");
                    this.promoverIdeias();
            }
        }

    }

    public void promoverIdeias() {
        Random random = new Random();
        int optionIdeias = random.nextInt(4);
        switch (optionIdeias) {
            case 1 -> System.out.println("As melhores taxas estão aqui !");
            case 2 -> System.out.println("Investir é mirar no sucesso !");
            case 3 -> System.out.println("O banco FECAF é lider em boa reputação !");
            default -> System.out.println("Serviços exclusivos para você !");
        }

    }
}
