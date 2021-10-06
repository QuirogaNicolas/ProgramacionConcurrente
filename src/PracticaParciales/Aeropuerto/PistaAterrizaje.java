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
    public void despegar(){
        try {
            lockAterrizar.lock();
            lockDespegar.lock();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally{
            lockAterrizar.unlock();
            lockDespegar.unlock();
        }
        
    }
    public void aterrizar(){
        try{
            lockDespegar.lock();
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally{
            lockAterrizar.unlock();
        }
    }
    public void estacionar(){
        //considero que no es necesario coordinarse para estacionar 
        //esto debido que pueden haber varios hangares donde dejar el avion
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
