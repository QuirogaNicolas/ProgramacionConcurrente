package parcial2_Quiroga_Fai_2978.Reintento.Ejercicio1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Almacen {
    private ReentrantLock lockEmbotelladorJugo;
    private ReentrantLock lockEmbotelladorVino;
    private ReentrantLock lockEmpaquetador;
    private Condition noHayCajas;
    private Condition cajaVinoLlena;
    private Condition cajaJugoLlena;
    private Caja cajaDeJugo;
    private Caja cajaDeVino;
    private int capacidadDeCajas;
    public Almacen(int capacidadDeCajas){
        this.lockEmbotelladorJugo= new ReentrantLock(true);
        this.lockEmbotelladorVino= new ReentrantLock(true);
        this.lockEmpaquetador= new ReentrantLock(true);
        this.noHayCajas= lockEmpaquetador.newCondition();
        this.cajaJugoLlena= lockEmbotelladorJugo.newCondition();
        this.cajaVinoLlena= lockEmbotelladorVino.newCondition();
        this.capacidadDeCajas= capacidadDeCajas;
        this.cajaDeJugo= new Caja(capacidadDeCajas);
        this.cajaDeVino= new Caja(capacidadDeCajas);
    } 

    public void ponerJugo(){
        try{
            lockEmbotelladorJugo.lock();
            while(!cajaDeJugo.hayEspacio()){
                System.out.println(Thread.currentThread().getName()+" no puede poner jugo porque la caja esta llena");
                cajaJugoLlena.await();
                /* ahora tengo que avisarle al hilo empaquetador que la caja se lleno pero tengo que ver que no todos
                los hilos que se quedan trabados avisen */
                noHayCajas.signal();
            }
            System.out.println(Thread.currentThread().getName()+" pone una botella de jugo en la caja");
            cajaDeJugo.ponerBotella();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        lockEmbotelladorJugo.unlock();
    }

    public void ponerVino(){
        try{
            lockEmbotelladorVino.lock();
            while(!cajaDeVino.hayEspacio()){
                System.out.println(Thread.currentThread().getName()+" no puede poner vino porque la caja esta llena");
                cajaVinoLlena.await();
                /* ahora tengo que avisarle al hilo empaquetador que la caja se lleno pero tengo que ver que no todos
                los hilos que se quedan trabados avisen */
                noHayCajas.signal();
            }
            System.out.println(Thread.currentThread().getName()+" pone una botella de vino en la caja");
            cajaDeVino.ponerBotella();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        lockEmbotelladorVino.unlock();
    }
     
    public void empaquetar(){
        try {
            noHayCajas.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}
