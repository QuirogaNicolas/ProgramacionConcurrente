package PracticaParciales.Aeropuerto;

public class Avion implements Runnable {
    private PistaSemaforo pistaAsignada;
    public Avion(PistaSemaforo pista){
        this.pistaAsignada= pista;
    }
    @Override
    public void run() {
        this.pistaAsignada.despegar();
        this.pistaAsignada.aterrizar();
        this.pistaAsignada.estacionar();
    }
}
