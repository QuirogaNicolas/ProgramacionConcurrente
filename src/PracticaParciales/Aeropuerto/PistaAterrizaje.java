package PracticaParciales.Aeropuerto;

import java.util.concurrent.Semaphore;

public class PistaAterrizaje {
    Semaphore semAterrizar;
    Semaphore semDespegar;
    Semaphore semEstacionar;
    public PistaAterrizaje(){
        Semaphore semAterrizar= new Semaphore(1);
        Semaphore semDespegar= new Semaphore(1);
        Semaphore semEstacionar= new Semaphore(1);
    }
    public void despegar(){

    }
    public void aterrizar(){

    }
    public void estacionar(){
        
    }
}
