package PracticaParciales.Aeropuerto;

import java.util.concurrent.Semaphore;

public class PistaSemaforo {
    private Semaphore semAterrizar;
    private Semaphore semDespegar;
    
    public PistaSemaforo(){
        this.semAterrizar= new Semaphore(1);
        this.semDespegar= new Semaphore(1);
    }
    public void despegar(){
        try {
            System.out.println(Thread.currentThread().getName()+" quiere despegar");
            semDespegar.acquire();
            System.out.println(Thread.currentThread().getName()+" tiene permiso para despegar");
            semAterrizar.acquire();
            System.out.println(Thread.currentThread().getName()+" dice: no hay nadie aterrizando");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally{
            System.out.println(Thread.currentThread().getName()+" ha despegado con exito");
            semAterrizar.release();
        }
        
    }
    public void aterrizar(){
        try{
            System.out.println(Thread.currentThread().getName()+" quiere aterrizar");
            semAterrizar.acquire();
            System.out.println(Thread.currentThread().getName()+" tiene permiso para aterrizar");
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally{
            System.out.println(Thread.currentThread().getName()+" ha aterrizado con exito");
            semAterrizar.release();
            semDespegar.release();
        }
    }
    public void estacionar(){
        //considero que no es necesario coordinarse para estacionar 
        //esto debido que pueden haber varios hangares donde dejar el avion
        try {
            System.out.println(Thread.currentThread().getName()+" esta estacionando");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+" ha estacionado con exito");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
