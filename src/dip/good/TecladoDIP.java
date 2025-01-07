package dip.good;

public class TecladoDIP implements DispositivoEntrada{

    @Override
    public void usar() {
        System.out.println("Digitando...");
    }
}
