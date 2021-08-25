import java.util.Scanner;
import Controle.Controle;
import Produto.Produto;


public class App {

    private Produto estoque[] = new Produto[5];
    private int posicaoAtual = 0;
    public static void main(String[] args) throws Exception {
       telaPrincipal();
    }
}

private static void telaPrincipal() {
    int op = 0;

    Controle app = new Controle();
    do {
        app.tituloMenu();
        op = app.menu();

        switch (op) {
            case 1:
                app.cadastro();
                break;
            case 2:
                menuMovimentacao();
                break;
            case 3:
                relatorioDeMantimentos();
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

