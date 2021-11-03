package TP5.Ejercicio1;

public class Fumador implements Runnable {
    private int id;
    private SalaFumadores sala;

    public Fumador(int id, SalaFumadores sala) {
        this.id = id;
        this.sala = sala;
    }

    public void run() {
        while (true) {
            // ...
            try {
                sala.entraFumar(id);
                System.out.println(Thread.currentThread().getName() + " esta fumando");
                Thread.sleep(1000);
                sala.terminaFumar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
