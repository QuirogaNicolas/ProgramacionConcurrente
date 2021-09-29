package TP4.Ejercicio4;
import java.util.concurrent.Semaphore;

public class Procesos{
    private Semaphore semA= new Semaphore(1);
    private Semaphore semB= new Semaphore(0);
    private Semaphore semC= new Semaphore(0);
    
    public void escribirA(int cantVeces){
        try {
            semA.acquire();
            for(int i=0; i<=cantVeces; i++){
                System.out.print("A");
            }
            semB.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void escribirB(int cantVeces){
        try {
            semB.acquire();
            for(int i=0; i<=cantVeces; i++){
                System.out.print("B");
            }
            semC.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void escribirC(int cantVeces){
        try {
            semC.acquire();
            for(int i=0; i<=cantVeces; i++){
                System.out.print("C");
            }
            semA.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}