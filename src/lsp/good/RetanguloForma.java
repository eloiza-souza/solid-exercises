package lsp.good;

public class RetanguloForma implements Forma {

    private int altura;
    private int largura;

    public RetanguloForma(int altura, int largura) {
        this.altura = altura;
        this.largura = largura;
    }

    @Override
    public int getArea() {
        return this.altura * this.largura;
    }
}
