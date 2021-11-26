package TrabajoConAdicionales.TrenConLatch;

public class Pasajero implements Runnable {
    private Estacion laEstacion;

    public Pasajero(Estacion laEstacion) {
        this.laEstacion = laEstacion;
    }

    public void run() {
        laEstacion.comprarTicket();
        laEstacion.subirseAlTren();
        laEstacion.intentarBajar();
        bajar();
        laEstacion.haBajado();
    }

    public void bajar() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
