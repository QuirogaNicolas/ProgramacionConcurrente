package QuirogaFai2978.Ejercicio2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Testigo {
    private Lock lockTestigo = new ReentrantLock();

    public void tomarTestigo() {
        lockTestigo.lock();
    }

    public void soltarTestigo() {
        lockTestigo.unlock();
    }
}
