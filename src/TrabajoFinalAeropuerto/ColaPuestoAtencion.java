package TrabajoFinalAeropuerto;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ColaPuestoAtencion {
    private ReentrantLock EsperarFila;
    private Condition hayLugar;
    private Condition siguiente;
    private int lugaresFila;

    public ColaPuestoAtencion(int lugares) {
        this.lugaresFila = lugares;
        this.EsperarFila = new ReentrantLock();
        this.hayLugar = EsperarFila.newCondition();
        this.siguiente = EsperarFila.newCondition();
    }

    public void consultarLugar() throws InterruptedException {
        // Los pasajeros van a consultar si hay lugar en la fila y esperar en el hall si
        // no hay lugar
        EsperarFila.lock();
        try {
            while (lugaresFila == 0) {
                System.out.println(Thread.currentThread().getName() + " espera en el hall para entrar a la fila");
                hayLugar.await();
            }
            System.out.println(Thread.currentThread().getName() + " entró en la fila");
            lugaresFila--;
        } catch (InterruptedException e) {
            // Si el aeropuerto cerró y hay pasajeros esperando un lugar en la fila entonces
            // se van a despertar
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " interrumpido en hall");
            throw e;
        } finally {
            EsperarFila.unlock();
        }

    }

    public void anunciarHall() throws InterruptedException {
        // Anunciamos a los pasajeros esperando en el hall que hay un nuevo lugar en la
        // fila
        EsperarFila.lock();
        System.out.println(Thread.currentThread().getName() + " espera a llamar a alguien del hall");
        try {
            siguiente.await();
            lugaresFila++;
            hayLugar.signalAll();
            System.out.println(Thread.currentThread().getName() + " anuncia que hay un lugar en la fila");
        } catch (InterruptedException e) {
            // Cuando cierre el aeropuerto, si el guardia está esperando a esperar una
            // señal, se lo despierta para terminar
            Thread.currentThread().interrupt();
            throw e;
        } finally {
            EsperarFila.unlock();
        }
    }

    public void pasaSiguiente() {
        // Se le avisa al guardia que el pasajero actual ya terminó el tramite y puede
        // llamar a alguien más para hacer la fila
        EsperarFila.lock();
        System.out.println(
                Thread.currentThread().getName() + " le avisa al guardia que haga pasar a alguien más a la fila");
        siguiente.signal();
        EsperarFila.unlock();
    }
}
