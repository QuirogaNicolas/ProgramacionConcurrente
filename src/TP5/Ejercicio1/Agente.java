package TP5.Ejercicio1;

import java.util.Random;

public class Agente implements Runnable {
    private SalaFumadores sala;
    private Random r;

    public Agente(SalaFumadores sala) {
        this.sala = sala;
        r = new Random();
    }

    public void run() {
        while (true) {
            sala.colocar(r.nextInt(3) + 1);
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
