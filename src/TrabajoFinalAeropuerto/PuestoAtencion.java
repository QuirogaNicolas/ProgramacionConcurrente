package TrabajoFinalAeropuerto;

import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PuestoAtencion {
    private ReentrantLock EsperarFila; 
    private Condition hayLugar;
    

    private ReentrantLock hacerTramite; 
    private Condition siguiente;
    
    private String id;
    private int lugaresFila;
    private Map<String, Integer> mapaTerminales;



    public PuestoAtencion(String idPuesto, int lugaresFila){
        this.id = idPuesto;
        this.lugaresFila = lugaresFila;
        this.EsperarFila = new ReentrantLock();
        this.hayLugar = EsperarFila.newCondition();
        this.hacerTramite = new ReentrantLock(true);
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
        lugaresFila -= 1;
        EsperarFila.unlock();
    }
    
    public int hacerCheckIn(String vuelo){
        hacerTramite.lock();
        System.out.println(Thread.currentThread().getName() + " está haciendo el trámite");
        int numTerminal;
        numTerminal = mapaTerminales.get(vuelo.substring(0,3));
        hacerTramite.unlock();
        System.out.println(Thread.currentThread().getName() + " ya se le asignó su terminal");
        return numTerminal;
    }

    public void pasaSiguiente(){
        
    }

}
