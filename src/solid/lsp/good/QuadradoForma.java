package solid.lsp.good;

public class QuadradoForma implements Forma{
    private int lado;


    public QuadradoForma(int lado) {
        this.lado = lado;
    }

    @Override
    public int getArea() {
        return this.lado * this.lado;
    }
}
