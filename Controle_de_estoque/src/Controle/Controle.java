import java.util.Scanner;
import java.util.ArrayList;
import Produto.Produto;

public class Controle {
    public Controle(){}

    private Produto estoque[] = new Produto[5];
    private int posicaoAtual = 0;

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
    public int cadastro(){
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
    public void fdsafamovimentacao(){
        System.out.println("MOVIMENTAÇÃO\n"
                            + "1 - ENTRADA\n"
                            + "2 - SAÍDA\n"
                            + "0 - RETORNAR\n"
                            + "OPÇÃO: ");
    }

    private void movimentacao() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("MOVIMENTACAO");
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

    public void movEnt(){
        System.out.println();
                            PRODUTO:\n
                            QTDE ATUAL:\n
                            QTDE ENTRADA:\n
                            QTDE FINAL: ");
    }

    public void movSai(){}

    private void movEnt() {
        String escolha;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("MOVIMENTAÇÃO - ENTRADA DE PRODUTO");
            System.out.println("Nome do produto:");
            String prodNome = sc.nextLine();
            Produto produtoMovimentacao = estoque[i];
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (prodNome.equalsIgnoreCase(estoque[i].getNome())) {
                    controle=false;
                    produtoMovimentacao = estoque[i];
                    System.out.println("QTDE ATUAL : " +  produtoMovimentacao.getQuantidadeEmEstoque());
                    System.out.println("QTDE ENTRADA : ");
                    int quantidadeEntrada = sc.nextInt();
                    System.out.println("QTDE FINAL : " + ( produtoMovimentacao.getQuantidadeEmEstoque() + quantidadeEntrada));
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        produtoMovimentacao.setAdicionarQuantidade(quantidadeEntrada);
                        estoque[i] =  produtoMovimentacao;
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);

            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }


    private void saidaMovimentacao() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("CONSUMO DOS MANTIMENTOS");
            System.out.println("Nome do mantimento");
            String nomeMantimento = scanner.nextLine();

            boolean controle = true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (nomeMantimento.equalsIgnoreCase(mantimentosList[i].getNome())) {
                    controle=false;
                    Mantimentos mantimentosMovimentacao = mantimentosList[i];
                    System.out.println("QTDE ATUAL : " + mantimentosMovimentacao.getQuantidadeEmEstoque());
                    System.out.println("QTDE SAÍDA : ");
                    int quantidadeSaida = scanner.nextInt();
                    System.out.println("QTDE FINAL : " + (mantimentosMovimentacao.getQuantidadeEmEstoque() - quantidadeSaida));
                    if (mantimentosMovimentacao.getQuantidadeEmEstoque() < quantidadeSaida) {
                        System.out.println("Quantidade maior que no estoque, saída não é possível");
                        break;
                    }
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        mantimentosMovimentacao.setDiminuirQuantidade(quantidadeSaida);
                        mantimentosList[i] = mantimentosMovimentacao;
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
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


