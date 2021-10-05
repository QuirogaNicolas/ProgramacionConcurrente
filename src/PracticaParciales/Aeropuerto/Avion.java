package PracticaParciales.Aeropuerto;

public class Avion implements Runnable {
    private PistaAterrizaje pistaAsignada;
    public Avion(PistaAterrizaje pista){
        this.pistaAsignada= pista;
    }
    @Override
    public void run() {
        this.pistaAsignada.despegar();
        this.pistaAsignada.aterrizar();
        this.pistaAsignada.estacionar();
    }
}
