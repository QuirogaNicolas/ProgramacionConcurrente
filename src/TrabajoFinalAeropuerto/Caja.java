package TrabajoFinalAeropuerto;

import java.util.concurrent.Semaphore;

public class Caja {
    private Semaphore semPagar = new Semaphore(1,true);
    
    public void pagar(int valorCompra){
        //Los pasajeros van a esperar su turno para pagar
        try {
            semPagar.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " pagó " + valorCompra + " pesos!!!");
        //Sale de la caja
        semPagar.release();
    }
    
    public boolean intentarPagar(int valorCompra){
        //Los pasajeros intentan pagar, se fijan si hay un lugar libre
        boolean pago = false;
        if(semPagar.tryAcquire()){
            System.out.println(Thread.currentThread().getName() + " pagó " + valorCompra + " pesos!!!");
            pago = true;
            //Sale de la caja
            semPagar.release();
        }
        return pago;
    }



}
