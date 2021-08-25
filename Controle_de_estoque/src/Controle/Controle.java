import java.util.Scanner;

public class Controle {
    public Controle(){}



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

    public int cadastro(){
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
                cadastrarMantimentos();
                break;
            case 2:
                alterarMantimento();
                break;
            case 3:
                consultarMantimento();
                break;
            case 4:
                excluirMantimento();
                break;
            default:
                System.out.println("Opção inválida!");;
                break;
        }
    }

    public void inclui() {
        String escolha;
        do {
            this.tituloMenu();
            System.out.println("CADASTRO DE PRODUTOS");
            Produto produto = inclui();
            escolha = confirmaOperacao();
            if (escolha.equalsIgnoreCase("S")) {
                estoque[posicaoAtual] = produto;
                posicaoAtual++;
            }
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private Produto incluiProduto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o nome do mantimentos");
        String nome = scanner.nextLine();
        System.out.println("Informe a unidade de medida");
        scanner = new Scanner(System.in);
        String unidade = scanner.nextLine();
        System.out.println("Informe a quantidade");
        int quantidade = scanner.nextInt();

        Mantimentos mantimentos = new Mantimentos();
        mantimentos.setNome(nome);
        mantimentos.setUnidade(unidade);
        mantimentos.setQuantidadeEmEstoque(quantidade);
        return mantimentos;
    }

    public void alteraProduto(){

        System.out.println("ALTERAÇÃO DE PRODUTO\n"
                            NOME:\n"
                            PREÇO:
                            UNIDADE:
                            QUANTIDADE:");
    }

    public void consulta(){
        System.out.println("CONSULTA DE PRODUTO\n
                            NOME:\n
                            PREÇO:\n
                            UNIDADE:\n
                            QUANTIDADE:\n");
    }

    public void exclui(){
        System.out.println("EXCLUSÃO DE PRODUTO\n
                            NOME:\n
                            PREÇO:\n
                            UNIDADE:\n
                            QUANTIDADE:\n");
    }

    public void movimentacao(){
        System.out.println("MOVIMENTAÇÃO\n"
                            + "1 - ENTRADA\n"
                            + "2 - SAÍDA\n"
                            + "0 - RETORNAR\n"
                            + "OPÇÃO: ");
    }

    public void movEnt(){
        System.out.println("MOVIMENTAÇÃO - ENTRADA DE PRODUTO\n
                            PRODUTO:\n
                            QTDE ATUAL:\n
                            QTDE ENTRADA:\n
                            QTDE FINAL: ");
    }

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


