package TrabajoFinalAeropuerto;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ColaPuestoAtencion {
    private ReentrantLock EsperarFila; 
    private Condition hayLugar;
    private Condition siguiente;
    private int lugaresFila;

    public ColaPuestoAtencion(int lugares){
        this.lugaresFila = lugares;
        this.EsperarFila = new ReentrantLock();
        this.hayLugar = EsperarFila.newCondition();
        this.siguiente = EsperarFila.newCondition();
    }

    public void consultarLugar(){
        EsperarFila.lock();
        while (lugaresFila == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " espera en el hall para entrar a la fila");
                hayLugar.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " entró en la fila");
        lugaresFila--;
        EsperarFila.unlock();
    }

    public void anunciarHall() throws InterruptedException{
        //Anunciamos a los pasajeros esperando en el hall que hay un nuevo lugar en la fila
        EsperarFila.lock();
        System.out.println(Thread.currentThread().getName() + " espera a llamar a alguien del hall");
        //Esto se tendría que repetir, hay que ver si hago que se repita acá o en el hilo
        try {
            siguiente.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            EsperarFila.unlock();
            throw e;
        }
        lugaresFila++;
        hayLugar.signalAll();
        System.out.println(Thread.currentThread().getName() + " anuncia que hay un lugar en la fila");
        EsperarFila.unlock();
    }

    public void pasaSiguiente(){
        //Se le avisa al guardia que el pasajero actual ya terminó el tramite y puede llamar a alguien más para hacer la fila
        EsperarFila.lock();
        System.out.println(Thread.currentThread().getName() + " le avisa al guardia que haga pasar a alguien más a la fila");
        siguiente.signal();
        EsperarFila.unlock();
    }
}
