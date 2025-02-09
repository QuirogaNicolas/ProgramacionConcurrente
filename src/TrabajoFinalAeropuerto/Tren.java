package TrabajoFinalAeropuerto;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Tren {
    private ReentrantLock lock;
    private Condition meBajo;
    private Condition meSubo;
    private Condition partimos;
    private int capacidad;
    private int terminalActual;

    public Tren(int capacidad){
        this.lock = new ReentrantLock();
        this.meBajo = lock.newCondition();
        this.meSubo = lock.newCondition();
        this.partimos = lock.newCondition();
        this.capacidad = capacidad;
        this.terminalActual = 0;
    }

    public void abordar(){
        lock.lock();
        while(capacidad == 0){
            try {
                System.out.println(Thread.currentThread().getName() + " espera subirse al tren");
                meSubo.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        capacidad--;
        System.out.println(Thread.currentThread().getName() + " se subió al tren");
        if(capacidad == 0){
            System.out.println(Thread.currentThread().getName() + " da la señal");
            partimos.signal();
        }
        lock.unlock();
    }

    public void bajar(int terminalPasajero){
        lock.lock();
        while (terminalActual != terminalPasajero) {
            try {
                meBajo.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " yo me bajé en la parada "+ terminalActual + " y me correspondía "+ terminalPasajero);
        capacidad++;
        lock.unlock();
    }

    public void parada(int numParada){
        lock.lock();
        terminalActual = numParada;
        System.out.println(Thread.currentThread().getName() + " dice: llegamos a la parada "+terminalActual);
        meBajo.signalAll();
        lock.unlock();
    }

    public void volvimos(){
        lock.lock();
        terminalActual = 0;
        meSubo.signalAll();
        System.out.println(Thread.currentThread().getName() + " dice: volvió el tren!");
        lock.unlock();
    }

    public void partir(){
        lock.lock();
        try {
            partimos.await(10,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " dice: se va el tren!");
        lock.unlock();
    }

}
