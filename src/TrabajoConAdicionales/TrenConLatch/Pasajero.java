package TrabajoConAdicionales.TrenConLatch;


public class Pasajero implements Runnable{
    private Estacion laEstacion;
    public Pasajero(Estacion laEstacion){
        this.laEstacion= laEstacion;
    }
    public void run(){
        laEstacion.comprarTicket();
        laEstacion.subirseAlTren();
    }
}
