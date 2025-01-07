package dip.good;

public class ComputadorDIP {
    private DispositivoEntrada dispositivoEntrada;

    public ComputadorDIP(DispositivoEntrada dispositivoEntrada) {
        this.dispositivoEntrada = dispositivoEntrada;
    }

    public void escrever(){
        dispositivoEntrada.usar();
    }
}
