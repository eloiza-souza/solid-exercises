package lsp.good;

public class Retangulo implements Forma {

    private int altura;
    private int largura;

    public Retangulo(int altura, int largura) {
        this.altura = altura;
        this.largura = largura;
    }

    @Override
    public int getArea() {
        return this.altura * this.largura;
    }
}
