package TrabajoConAdicionales.TrenConLatch;

public class Tren implements Runnable {
    private Estacion laEstacion;
    private int capacidad;

    public Tren(Estacion laEstacion, int capacidad) {
        this.laEstacion = laEstacion;
        this.capacidad = capacidad;
    }

    public void run() {
        laEstacion.prepararseParaSalir();
    }

    public void hacerRecorrido() {
        System.out.println("El tren sale de la estacion...");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("El tren ha llegado a destino!");
    }

    public int getCapacidad() {
        return this.capacidad;
    }
}
