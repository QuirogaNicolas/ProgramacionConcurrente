package TP4.Ejercicio9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Actividades {
    Lock lockHamaca;
    Lock lockComedero;
    Lock lockRueda;
    public Actividades(){
        this.lockComedero= new ReentrantLock();
        this.lockRueda= new ReentrantLock();
        this.lockHamaca= new ReentrantLock();
    }
    public void comer(){
        lockComedero.lock();
        //El hamster come...
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lockComedero.unlock();
    }
    public void correr(){
        lockRueda.lock();
        //El hamster corre...
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lockRueda.unlock();
    }
    public void hamacarse(){
        lockHamaca.lock();
        //El hamster intenta dormir hamacandose...
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lockHamaca.unlock();
    }
}
