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
        System.out.println(Thread.currentThread().getName()+" esta comiendo");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" ha comido");
        lockComedero.unlock();
    }
    public void correr(){
        lockRueda.lock();
        //El hamster corre...
        System.out.println(Thread.currentThread().getName()+" esta corriendo");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" ha terminado de correr");
        lockRueda.unlock();
    }
    public void hamacarse(){
        lockHamaca.lock();
        //El hamster intenta dormir hamacandose...
        System.out.println(Thread.currentThread().getName()+" esta durmiendo");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" ha terminado de dormir");
        lockHamaca.unlock();
    }
}
