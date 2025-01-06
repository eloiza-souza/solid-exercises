package lsp.good;

public class Quadrado implements Forma{
    private int altura;
    private int largura;

    public Quadrado(int altura, int largura) {
        this.altura = altura;
        this.largura = largura;
    }

    @Override
    public int getArea() {
        return this.altura * this.largura;
    }
}
