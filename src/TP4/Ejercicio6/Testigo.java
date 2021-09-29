package TP4.Ejercicio6;

import java.util.concurrent.Semaphore;

public class Testigo {
    private Semaphore sem_testigo= new Semaphore(1);
    public void tomarTestigo(){
        try {
            sem_testigo.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void soltarTestigo(){
        sem_testigo.release();
    }
}
