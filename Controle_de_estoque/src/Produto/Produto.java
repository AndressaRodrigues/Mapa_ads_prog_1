package Produto;
public class Produto {
    private String nome;
    private double preco;
    private String unidade;
    private int quant;

    public Produto() {}

    public Produto(String nome, double preco, String un, int quant) {
        this.nome = nome;
        this.preco = preco;
        this.unidade = un;
        this.quant = quant;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuant() { return quant; };

    public void setQuant(int quant) {
        this.quant = quant;
    };

    public void setQuantAdd(int quant) { this.quant += quant;};

    public void setQuantSub(int quant) { this.quant -= quant;};

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

}
