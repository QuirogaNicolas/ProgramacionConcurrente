package TP6.Ejercicio3;

public class Impresora implements Runnable {
    Buffer elBuffer;

    public Impresora(Buffer elBuffer) {
        this.elBuffer = elBuffer;
    }

    public void run() {
        while (true) {
            imprimir(elBuffer.obtener());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void imprimir(Object aImprimir) {
        System.out.println(Thread.currentThread().getName() + " imprime: " + aImprimir);
    }
}
