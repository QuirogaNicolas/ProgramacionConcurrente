package PracticaParciales.Aeropuerto;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PistaAterrizaje {
    Lock lockAterrizar;
    Lock lockDespegar;
    Lock lockEstacionar;
    public PistaAterrizaje(){
        lockAterrizar= new ReentrantLock();
        lockDespegar= new ReentrantLock();
        lockEstacionar= new ReentrantLock();
    }
    //INTENTAR MAÑANAAA
    //siento que con semaforos se solucionaria la prioridad
    public void despegar(){
        try {
            //Necesito mejorar la prioridad pero no puedo usar Synchronized 
            //Capaz con un lockConsultar?????
            System.out.println(Thread.currentThread().getName()+" quiere despegar");
            lockDespegar.lock();
            System.out.println(Thread.currentThread().getName()+" tiene permiso para despegar");
            lockAterrizar.lock();
            System.out.println(Thread.currentThread().getName()+" dice: no hay nadie aterrizando");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally{
            System.out.println(Thread.currentThread().getName()+" ha despegado con exito");
            lockAterrizar.unlock();
            lockDespegar.unlock();
        }
        
    }
    public void aterrizar(){
        try{
            System.out.println(Thread.currentThread().getName()+" quiere aterrizar");
            lockAterrizar.lock();
            System.out.println(Thread.currentThread().getName()+" tiene permiso para aterrizar");
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally{
            System.out.println(Thread.currentThread().getName()+" ha aterrizado con exito");
            lockAterrizar.unlock();
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
