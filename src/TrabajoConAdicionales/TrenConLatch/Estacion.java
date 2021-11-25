package TrabajoConAdicionales.TrenConLatch;

import java.util.concurrent.CountDownLatch;

public class Estacion {
    private CountDownLatch latchParaSalir;
    private CountDownLatch latchParaSubirAlTren;
    private CountDownLatch latchParaBajarDelTren;
    private CountDownLatch latchParaEmpezarControl;
    private Tren elTren;
    private ControlTren elControl;

    public Estacion() {
        //aca se inicializan todos los atributos
    }

    public void prepararseParaSalir() {
        try {
            //el tren se va a quedar esperando que el contador de latchParaSalir llegue a 0 para, justamente, salir
            this.latchParaSalir.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elTren.hacerRecorrido();
    }

    public synchronized void comprarTicket() {
        //simplemente compra los tickets????
    }

    public synchronized void subirseAlTren() {
        //bajamos el latch del control para que cuando llegue a 0 el control del tren comience
        this.latchParaEmpezarControl.countDown();
    }

    public void realizarControl() {
        try {
            this.latchParaEmpezarControl.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        elControl.empezarElcontrol();
    }

}
