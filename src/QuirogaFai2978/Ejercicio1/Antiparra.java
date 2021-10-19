package QuirogaFai2978.Ejercicio1;

import java.util.concurrent.Semaphore;

public class Antiparra {
    private Semaphore estaDisponible;

    public Antiparra() {
        this.estaDisponible = new Semaphore(1);
    }

    public boolean tomarAntiparra() {
        return estaDisponible.tryAcquire();
    }

    public void devolverAntiparra() {
        this.estaDisponible.release();
    }
}
