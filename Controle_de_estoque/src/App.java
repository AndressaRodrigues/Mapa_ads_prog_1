import java.util.Scanner;
import Controle.Controle;
import Produto.Produto;


public class App {

    private Produto estoque[] = new Produto[5];
    private int posicaoAtual = 0;
    public static void main(String[] args) throws Exception {
        Controle app = new Controle();
        app.telaPrincipal();
    }
}



