package Controle;

import java.util.Scanner;
import java.util.ArrayList;
import Produto.Produto;

public class Controle {
    public Controle(){}

    private Produto estoque[] = new Produto[5];
    private int posicaoAtual = 0;

    //tela principal
    public void telaPrincipal() {
        int op = 0;
        Scanner sc = new Scanner(System.in);
        do {
            this.tituloMenu();
            op = this.menu();

            switch (op) {
                case 1:
                    this.cadastro();
                    break;
                case 2:
                    this.movimentacao();
                    break;
                case 3:
                    this.relatorioEstoque();
                    break;
                case 0:
                    System.out.println("Saindo do sistema");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (op != 0);

        sc.close();
    }

    //menu principal e sua interface
    public int menu() {
        System.out.println("MENU PRINCIPAL\n"
                            + "1 - CADASTRO DE PRODUTOS\n"
                            + "2 - MOVIMENTAÇÃO\n"
                            + "3 - REAJUSTE DE PREÇOS\n"
                            + "4 - RELATÓRIOS\n"
                            + "0 - FINALIZAR\n");
        return getEscolhaMenu();      
    }
    
    public void tituloMenu() {
        System.out.println("EMPRESA DE IMPORTAÇÃO DE PRODUTOS LTDA.\n"
                            + "SISTEMA DE CONTROLE DE ESTOQUE\n\n");
    }

    //cadastro e demais funções referentes
    public void cadastro(){
        this.tituloMenu();
        int op;
        System.out.println("CADASTRO DE PRODUTOS\n"
                            + "1 - INCLUSÃO\n"
                            + "2 - ALTERAÇÃO\n"
                            + "3 - CONSULTA\n"
                            + "4 - EXCLUSÃO\n"
                            + "0 - RETORNAR\n"
                            + "OPÇÃO: \t");
        op = getEscolhaMenu();

        switch (op){
            case 1:
                incluiProduto();
                break;
            case 2:
                alteraProduto();
                break;
            case 3:
                consultaEstoque();
                break;
            case 4:
                excluiProduto();
                break;
            default:
                System.out.println("Opção inválida!");;
                break;
        }
    }

    private void incluiProduto() {
        String escolha;
        do {
            this.tituloMenu();
            System.out.println("CADASTRO DE PRODUTOS");
            Produto produto = novoProduto();
            escolha = confirmaOperacao();
            if (escolha.equalsIgnoreCase("S")) {
                estoque[posicaoAtual] = produto;
                posicaoAtual++;
            }
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private Produto novoProduto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o nome do produto a ser cadastrado:");
        String nome = sc.nextLine();
        System.out.println("Informe o preço:");
        double preco = sc.nextDouble();
        System.out.println("Informe a unidade de medida");
        String unidade = sc.nextLine();
        System.out.println("Informe a quantidade");
        int quantidade = sc.nextInt();

        Produto produto = new Produto(nome, preco, unidade, quantidade);
        System.out.println("Os dados do produto são:"
                            + "\nNome: " 
                            + nome
                            + "\nPreço: "
                            + preco
                            + "\nUnidade de medida: "
                            + unidade
                            + "\nQuantidade no estoque: "
                            + quantidade);

        sc.close();
        return produto;
    }

    private void alteraProduto() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            this.tituloMenu();
            System.out.println("ALTERAÇÃO DE PRODUTO");
            System.out.println("Informe o nome do mantimento para alterar");
            String nomeConsulta = scanner.nextLine();
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++) {

                if (nomeConsulta.equalsIgnoreCase(estoque[i].getNome())) {
                    controle=false;
                    System.out.println("PRODUTO ENCONTRADO\n");
                    Produto mantimentos = novoProduto();
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        estoque[i] = mantimentos;
                    }
                    break;
                }
            }
            mensagemProdutoNaoExiste(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private void mensagemProdutoNaoExiste(boolean controle) {
        if (controle) {
            System.out.println("Produto não encontrado no estoque");
        }
    }

    private void consultaEstoque() {

        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            this.tituloMenu();
            System.out.println("CONSULTA DE PRODUTO");
            System.out.println("Informe o nome do produto para pesquisa: ");
            String nomeConsulta = scanner.nextLine();
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (nomeConsulta.equalsIgnoreCase(estoque[i].getNome())) {
                    controle=false;
                    System.out.println(estoque[i].toString());
                    break;
                }
            }
            mensagemProdutoNaoExiste(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private void excluiProduto() {

        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            this.tituloMenu();
            System.out.println("EXCLUSÃO DE PRODUTOS");
            System.out.println("Informe o nome do produto a ser excluído:");
            String nomeConsulta = scanner.nextLine();
            boolean controle = true;
            ArrayList<Produto> arrayList = new ArrayList<>();
            arrayList.add(new Produto());
            for (int i = 0; i < posicaoAtual; i++) {
                scanner = new Scanner(System.in);
                Produto mantimentos = arrayList.get(i);
                if (nomeConsulta.equalsIgnoreCase(estoque[i].getNome())) {
                    controle=false;
                    System.out.println(estoque[i].toString());
                    System.out.println("CONFIRMA EXCLUSÃO ( S/N ) ?");
                    escolha = scanner.next();
                    if (escolha.equalsIgnoreCase("S")) {
                        for (int j = i; j < posicaoAtual - 1; j++) {
                            estoque[j] = estoque[j + 1];
                            posicaoAtual--;
                        }
                    }
                    break;
                }
            }
            mensagemProdutoNaoExiste(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }


    //movimentação e demais funcoes referentes

    private void movimentacao() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("MOVIMENTAÇÃO");
        System.out.println("1 - ENTRADA\n" 
                            + "2 - SAÍDA\n"
                            + "0 - RETORNAR\n"
                            + "OPÇÃO  : \n");

        int opMovimentacao = scanner.nextInt();
        switch (opMovimentacao) {
            case 1:
                movEnt();
                break;
            case 2:
                movSai();
                break;
            case 0:
                System.out.println("Retornando para o menu principal");
                break;
            default:
                System.out.println("Opção inválida!");;
                break;
        }
    }

    private void movEnt() {
        String escolha;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("MOVIMENTAÇÃO - ENTRADA DE PRODUTO");
            System.out.println("Nome do produto:");
            String prodNome = sc.nextLine();
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (prodNome.equalsIgnoreCase(estoque[i].getNome())) {
                    controle=false;
                    Produto produtoMovimentacao = estoque[i];
                    produtoMovimentacao = estoque[i];
                    System.out.println("QUANTIDATDE ATUAL : " +  produtoMovimentacao.getQuant());
                    System.out.println("QUANTIDADEE ENTRADA : ");
                    int quantidadeEntrada = sc.nextInt();
                    System.out.println("QUANTIDADE FINAL : " + ( produtoMovimentacao.getQuant() + quantidadeEntrada));
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        produtoMovimentacao.setQuantAdd(quantidadeEntrada);
                        estoque[i] =  produtoMovimentacao;
                    }
                    break;
                }
            }
            mensagemProdutoNaoExiste(controle);

            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }


    private void movSai() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("CONSUMO DOS MANTIMENTOS");
            System.out.println("Nome do mantimento");
            String nomeMantimento = scanner.nextLine();

            boolean controle = true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (nomeMantimento.equalsIgnoreCase(estoque[i].getNome())) {
                    controle=false;
                    Produto produtoMovimentacao = estoque[i];;
                    System.out.println("QTDE ATUAL : " + produtoMovimentacao.getQuant());
                    System.out.println("QTDE SAÍDA : ");
                    int quantidadeSaida = scanner.nextInt();
                    System.out.println("QTDE FINAL : " + (produtoMovimentacao.getQuant() - quantidadeSaida));
                    if (produtoMovimentacao.getQuant() < quantidadeSaida) {
                        System.out.println("Quantidade maior que no estoque, saída não é possível");
                        break;
                    }
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        produtoMovimentacao.setQuantSub(quantidadeSaida);
                        estoque[i] = produtoMovimentacao;
                    }
                    break;
                }
            }
            mensagemProdutoNaoExiste(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    //relatório do estoque
    private void relatorioEstoque() {
        this.tituloMenu();
        System.out.println("RELATÓRIO DO ESTOQUE");
        for (int i = 0; i < posicaoAtual; i++) {
            System.out.println("\n");
            System.out.println("PRODUTOS: \n"
                                + "POSIÇÃO NO ESTOQUE: "
                                + i
                                + "\n"
                                + estoque[i]);

        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\n");
        System.out.println("APERTE QUALQUER LETRA + ENTER PARA CONTINUAR");
        scanner.next();
    }



    //funcoes genericas
    public String getRepetirOperacao() {
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.println("REPETIR OPERAÇÃO ( S/N ) ? ");
        escolha = scanner.next();
        return escolha;
    }

    public String confirmaOperacao() {
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.println("CONFIRMA OPERAÇÃO ( S/N ) ?");
        escolha = scanner.next();
        return escolha;
    }

    public int getEscolhaMenu() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.next());
    }
}


