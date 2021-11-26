package TrabajoConAdicionales.TrenConLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Estacion {
    private CountDownLatch latchParaSalir;
    private CountDownLatch latchParaBajarDelTren;
    private CountDownLatch latchParaEmpezarControl;
    private Tren elTren;
    private ControlTren elControl;
    private VendedorTickets elVendedor;
    private Semaphore semaforoVendedor;
    private Semaphore semaforoComprar;
    private Semaphore semaforoSubir;
    private Semaphore semaforoBajar;

    public Estacion() {
        // aca se inicializan todos los atributos
    }

    public void prepararseParaSalir() {
        try {
            // el tren se va a quedar esperando que el contador de latchParaSalir llegue a 0
            // para, justamente, salir
            this.latchParaSalir.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elTren.hacerRecorrido();
    }

    public void comprarTicket() {
        try {
            semaforoComprar.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " desea comprar un ticket");
        if (elVendedor.ticketsRestantes() > 0) {
            semaforoVendedor.release();
        } else {
            System.out.println(
                    "El vendedor le comunica al " + Thread.currentThread().getName() + " que no hay tickets restantes");
        }
        semaforoComprar.release();
    }

    public void subirseAlTren() {
        try {
            semaforoSubir.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // bajamos el latch del control para que cuando llegue a 0 el control del tren
        // comience
        if (latchParaEmpezarControl.getCount() >= 0) {
            this.latchParaEmpezarControl.countDown();
        } else {
            /*
             * esto no pasaria nunca en teoria
             */
            System.out.println("El " + Thread.currentThread().getName() + " no llego a subirse al tren :(");
        }
        semaforoSubir.release();
    }

    public void realizarControl() {
        try {
            this.latchParaEmpezarControl.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        elControl.empezarElcontrol();
    }

    public void vender() {
        try {
            semaforoVendedor.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El vendedor le esta vendiendo un ticket");
        elVendedor.venderTicket();
    }

    public void intentarBajar() {
        try {
            semaforoBajar.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void haBajado() {
        semaforoBajar.release();
    }
}
