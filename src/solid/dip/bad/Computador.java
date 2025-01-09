package solid.dip.bad;

public class Computador {
    private Teclado teclado;

    public Computador() {
        this.teclado = new Teclado();
    }

    public void escrever(){
        teclado.digitar();
    }
}
