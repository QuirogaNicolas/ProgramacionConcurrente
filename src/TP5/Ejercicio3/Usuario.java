package TP5.Ejercicio3;

public class Usuario implements Runnable {
    Buffer elbuffer;
    Object impresion;

    public Usuario(Object impresion, Buffer buffer) {
        this.impresion = impresion;
        this.elbuffer = buffer;
    }

    public void run() {
        while (true) {
            System.out.println("usuario " + Thread.currentThread().getName() + " accede al buffer");
            elbuffer.ingresar(impresion);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
