package Controle;

import java.util.Scanner;
import java.util.ArrayList;
import Produto.Produto;

public class Controle {
    public Controle(){}

    private Produto[] estoque = new Produto[50];
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
                    reajustePreco();
                    break;
                case 4:
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
        if(posicaoAtual < estoque.length) {
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

            } while (escolha.equalsIgnoreCase("S") && (posicaoAtual < estoque.length));
        } else {
            System.out.println("Estoque atingiu sua capacidade máxima! Por favor, exclua ou remova algum produto\n\n");
        }
    }

    private Produto novoProduto() {
        String nome = null, unidade = null;
        double preco =0;
        int quantidade = 0;
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Informe o nome do produto a ser cadastrado:");
            nome = sc.nextLine();
            System.out.println("Informe o preço:");
            preco = sc.nextDouble();
            System.out.println("Informe a unidade de medida");
            sc.nextLine();
            unidade = sc.nextLine();
            System.out.println("Informe a quantidade");
            quantidade = sc.nextInt();


        } catch (Exception e) {
            System.out.println("Erro! Você digitou um alfanumérico no lugar de um número.");
        }
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


        return produto;
    }

    private void alteraProduto() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            this.tituloMenu();
            System.out.println("ALTERAÇÃO DE PRODUTO");
            System.out.println("Informe o nome do produto para alterar");
            String nomeConsulta = scanner.nextLine();
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++) {

                if (nomeConsulta.equalsIgnoreCase(estoque[i].getNome())) {
                    controle=false;
                    System.out.println("PRODUTO ENCONTRADO\n");
                    Produto produto = novoProduto();
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        estoque[i] = produto;
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

    // reajusta o preço
    private void reajustePreco() {
        String escolha;
        do {
            Scanner sc = new Scanner(System.in);
            this.tituloMenu();
            System.out.println("ALTERAÇÃO DE PREÇO DE PRODUTO PRODUTO");
            System.out.println("Informe o nome do produto para alterar");
            String nomeConsulta = sc.nextLine();
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++) {

                if (nomeConsulta.equalsIgnoreCase(estoque[i].getNome())) {
                    controle=false;
                    System.out.println("PRODUTO ENCONTRADO\n");
                    System.out.println("VALOR ATUAL: " + estoque[i].getPreco());
                    System.out.println("DIGITE O NOVO VALOR: ");
                    double novoPreco = sc.nextDouble();
                    System.out.println("O NOVO PREÇO SERA: " + novoPreco);
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        estoque[i].setPreco(novoPreco);
                    }
                    break;
                }
            }
            mensagemProdutoNaoExiste(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
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
                    System.out.println("PRODUTO ENCONTRADO\n");
                    System.out.println("Produto: "
                            + estoque[i].getNome()
                            + "\nPreço: "
                            + estoque[i].getPreco()
                            + "\nUnidade de medida: "
                            + estoque[i].getUnidade()
                            + "\nQuantidade em estoque: "
                            + estoque[i].getQuant());
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
            ArrayList<Produto> arrayList = new ArrayList();
            arrayList.add(new Produto());
            for (int i = 0; i < posicaoAtual; i++) {
                scanner = new Scanner(System.in);
                Produto produtos = arrayList.get(i);
                if (nomeConsulta.equalsIgnoreCase(estoque[i].getNome())) {
                    controle=false;
                    System.out.println("PRODUTO ENCONTRADO\n");
                    System.out.println("Produto: "
                                        + estoque[i].getNome()
                                        + "\nPreço: "
                                        + estoque[i].getPreco()
                                        + "\nUnidade de medida: "
                                        + estoque[i].getUnidade()
                                        + "\nQuantidade em estoque: "
                                        + estoque[i].getQuant());
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
        Scanner sc = new Scanner(System.in);
        System.out.println("MOVIMENTAÇÃO");
        System.out.println("1 - ENTRADA\n" 
                            + "2 - SAÍDA\n"
                            + "0 - RETORNAR\n"
                            + "OPÇÃO  : \n");

        int opMovimentacao = getEscolhaMenu();
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
                    System.out.println("QUANTIDADE ATUAL : " +  produtoMovimentacao.getQuant());
                    System.out.println("QUANTIDADE ENTRADA : ");
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
            System.out.println("SAIDA DE PRODUTOS DO ESTOQUE");
            System.out.println("Nome do produto:");
            String nomeMantimento = scanner.nextLine();

            boolean controle = true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (nomeMantimento.equalsIgnoreCase(estoque[i].getNome())) {
                    controle=false;
                    Produto produtoMovimentacao = estoque[i];;
                    System.out.println("QUANTIDADE ATUAL : " + produtoMovimentacao.getQuant());
                    System.out.println("QUANTIDADE SAÍDA : ");
                    int quantidadeSaida = scanner.nextInt();
                    System.out.println("QUANTIDADE FINAL : " + (produtoMovimentacao.getQuant() - quantidadeSaida));
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
                                + i+1
                                + "\n"
                                + "\nProduto: "
                                + estoque[i].getNome()
                                + "\nPreço: "
                                + estoque[i].getPreco()
                                + "\nUnidade de medida: "
                                + estoque[i].getUnidade()
                                + "\nQuantidade em estoque: "
                                + estoque[i].getQuant());

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
        return escolha.toUpperCase();
    }

    public String confirmaOperacao() {
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.println("CONFIRMA OPERAÇÃO ( S/N ) ?");
        escolha = scanner.next();
        return escolha.toUpperCase();
    }

    public int getEscolhaMenu() {
        Scanner sc = new Scanner(System.in);
        int escolha = 0;
        try{
            escolha =  Integer.parseInt(sc.next());
        } catch (NumberFormatException e) {
            System.out.println("Você digitou uma opção diferente das disponíveis");
        }
        return escolha;
    }
}


