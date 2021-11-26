package TrabajoConAdicionales.TrenConLatch;

public class ControlTren implements Runnable {
    private Estacion laEstacion;

    public ControlTren(Estacion laEstacion) {
        this.laEstacion = laEstacion;
    }

    public void empezarElcontrol() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            laEstacion.realizarControl();
        }
    }
}
