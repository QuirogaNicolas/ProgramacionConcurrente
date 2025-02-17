package TrabajoFinalAeropuerto;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Tren {
    private ReentrantLock lock;
    private Condition meBajoTerminal1;
    private Condition meBajoTerminal2;
    private Condition meBajoTerminal3;
    private Condition meSubo;
    private int capacidad;
    private int terminalActual;
    private CyclicBarrier barreraParaSalida;
    private boolean cerrado;

    public Tren(int capacidad){
        this.lock = new ReentrantLock();
        this.meBajoTerminal1 = lock.newCondition();
        this.meBajoTerminal2 = lock.newCondition();
        this.meBajoTerminal3 = lock.newCondition();
        this.meSubo = lock.newCondition();
        this.capacidad = capacidad;
        this.terminalActual = 0;
        this.barreraParaSalida = new CyclicBarrier(capacidad + 1);
        this.cerrado = false;
    }

    public void abordar() throws InterruptedException{
        //Los pasajeros abordan el tren siempre que haya lugar
        lock.lock();
            try {
                while(capacidad == 0 || barreraParaSalida.isBroken()){
                    System.out.println(Thread.currentThread().getName() + " espera subirse al tren");
                    meSubo.await();
                }
                capacidad--;
                System.out.println(Thread.currentThread().getName() + " se subió al tren");
                try {
                    barreraParaSalida.await();
                } catch (BrokenBarrierException e) {
                    System.out.println(Thread.currentThread().getName() + " la barrera esta rota!");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().getName() + " interrumpido en la barrera");
                    throw e;
                }
            } catch (InterruptedException e) {
                //Interrupidos los pasajeros que están esperando cuando cierra el aeropuerto
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " interrumpido en la espera del tren");
                throw e;
            } finally{
                lock.unlock();
            }
    }
 


    public void bajar(int terminalPasajero){
        //Los pasajeros esperan a llegar a la parada que les corresponde
        lock.lock();
        while (terminalActual != terminalPasajero) {
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
        }
        System.out.println(Thread.currentThread().getName() + " yo me bajé en la parada "+ terminalActual + " y me correspondía "+ terminalPasajero);
        capacidad++;
        lock.unlock();
    }

    public void parada(int numParada){
        //El maquinista avisa a los pasajeros que llegó a una parada
        lock.lock();
        terminalActual = numParada;
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
        if (barreraParaSalida.isBroken()) {
            //Resetear solo si está rota
            barreraParaSalida.reset(); 
        }
        System.out.println(Thread.currentThread().getName() + " dice: volvió el tren!");
        lock.unlock();
    }

    public void partir() throws InterruptedException{
        //El maquinista espera a que se llene el tren o 5 segundos (lo que pase primero)
        
            try {
                barreraParaSalida.await();
            } catch (BrokenBarrierException e) {
                if(cerrado){
                    System.out.println(Thread.currentThread().getName() + " la estación cerró!");
                    Thread.currentThread().interrupt();
                    throw new InterruptedException();
                }
                System.out.println(Thread.currentThread().getName() + " dice: se va el tren!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw e;
            }
    }

    public void cerrar(){
        cerrado = true;
    }

}
