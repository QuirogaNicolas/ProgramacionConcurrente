package QuirogaFai2978.Ejercicio1;

import java.util.concurrent.Semaphore;

public class Snorkel {
    private Semaphore estaDisponible;

    public Snorkel() {
        this.estaDisponible = new Semaphore(1);
    }

    public boolean tomarSnorkel() {
        return estaDisponible.tryAcquire();
    }

    public void devolverSnorkel() {
        this.estaDisponible.release();
    }
}
