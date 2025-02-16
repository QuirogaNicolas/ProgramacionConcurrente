package TrabajoFinalAeropuerto;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Tren {
    private ReentrantLock lock;
    private Condition meBajoTerminal1;
    private Condition meBajoTerminal2;
    private Condition meBajoTerminal3;
    private Condition meSubo;
    private Condition partimos;
    private int capacidad;
    private int terminalActual;

    public Tren(int capacidad){
        this.lock = new ReentrantLock();
        this.meBajoTerminal1 = lock.newCondition();
        this.meBajoTerminal2 = lock.newCondition();
        this.meBajoTerminal3 = lock.newCondition();
        this.meSubo = lock.newCondition();
        this.partimos = lock.newCondition();
        this.capacidad = capacidad;
        this.terminalActual = 0;
    }

    public void abordar() throws InterruptedException{
        //Los pasajeros abordan el tren siempre que haya lugar
        lock.lock();
        while(capacidad == 0){
            try {
                System.out.println(Thread.currentThread().getName() + " espera subirse al tren");
                meSubo.await();
            } catch (InterruptedException e) {
                //Interrupidos los pasajeros que están esperando cuando cierra el aeropuerto
                Thread.currentThread().interrupt();
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " interrumpido en la espera del tren");
                throw e;
            }
        }
        capacidad--;
        System.out.println(Thread.currentThread().getName() + " se subió al tren");
        if(capacidad == 0){
            //El último en subir le avisa al maquinista que se llenó el tren
            System.out.println(Thread.currentThread().getName() + " da la señal");
            partimos.signal();
        }
        lock.unlock();
    }
 


    public void bajar(int terminalPasajero){
        //Los pasajeros esperan a llegar a la parada que les corresponde
        lock.lock();
        try {
            switch (terminalPasajero) {
                case 1:
                    meBajoTerminal1.await();
                    break;
                case 2:
                    meBajoTerminal2.await();
                    break;
                case 3:
                    meBajoTerminal3.await();
                    break;
            }
        } catch (InterruptedException e) {
            
        }
        System.out.println(Thread.currentThread().getName() + " yo me bajé en la parada "+ terminalActual + " y me correspondía "+ terminalPasajero);
        capacidad++;
        lock.unlock();
    }

    public void parada(int numParada){
        //El maquinista avisa a los pasajeros que llegó a una parada
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " dice: llegamos a la parada "+terminalActual);
        switch (numParada) {
            case 1:
                meBajoTerminal1.signalAll();
                break;
            case 2:
                meBajoTerminal2.signalAll();
                break;
            case 3:
                meBajoTerminal3.signalAll();
                break;
        }
        lock.unlock();
    }

    public void volvimos(){
        //El maquinista avisa que ya volvió a los pasajeros que están esperando para subir al tren
        lock.lock();
        terminalActual = 0;
        meSubo.signalAll();
        System.out.println(Thread.currentThread().getName() + " dice: volvió el tren!");
        lock.unlock();
    }

    public void partir() throws InterruptedException{
        //El maquinista espera a que se llene el tren o 5 segundos (lo que pase primero)
        lock.lock();
        try {
            partimos.await(5,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            lock.unlock();
            throw e;
        }
        System.out.println(Thread.currentThread().getName() + " dice: se va el tren!");
        lock.unlock();
    }

}
