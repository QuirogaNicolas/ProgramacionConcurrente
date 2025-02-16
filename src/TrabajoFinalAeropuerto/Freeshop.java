package TrabajoFinalAeropuerto;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Freeshop {
    private Semaphore semEntrar = new Semaphore(3, true);
    private Semaphore caja1 = new Semaphore(1,true);
    private Semaphore caja2 = new Semaphore(1,true);

    public void entrarFreeShop() throws InterruptedException{
        //Los pasajeros van a entrar al freeshop solo si hay lugar disponible, en este caso, los freeshop tienen lugar para 3 personas
        try {
            semEntrar.acquire();
        } catch (InterruptedException e) {
            //Se va a interrumpir a los pasajeros que estén esperando en la fila del freeshop cuando cierre el aeropuerto
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " interrumpido en la espera de entrar al freeshop");
            throw e;
        }
    }

    public void pagarFreeshop(int valorCompra){
        //Primero los pasajeros intentan pagar en cada caja por si hay alguna vacía. Si las dos están ocupadas eligen una al azar 
        boolean pago = false;
        if (intentarPagarCaja1(valorCompra) || intentarPagarCaja2(valorCompra)) {
            pago = true;       
        }
        if (!pago){
            Random tirarMoneda = new Random();
            //Elije de manera aleatoria entre las dos cajas
            if(tirarMoneda.nextInt(2) == 0){
                pagarCaja1(valorCompra);
            }else{
                pagarCaja2(valorCompra);
            }
        }
    }

    public void salirFreeShop(){
        //Salen los pasajeros del freeshop
        System.out.println(Thread.currentThread().getName() + " se fue del freeshop");
        semEntrar.release();
    }

    public void pagarCaja1(int valorCompra){
        //Los pasajeros van a esperar su turno para pagar
        try {
            caja1.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " pagó " + valorCompra + " pesos!!!");
        //Sale de la caja
        caja1.release();
    }
    
    public boolean intentarPagarCaja1(int valorCompra){
        //Los pasajeros intentan pagar, se fijan si hay un lugar libre
        boolean pago = false;
        if(caja1.tryAcquire()){
            System.out.println(Thread.currentThread().getName() + " pagó " + valorCompra + " pesos!!!");
            pago = true;
            //Sale de la caja
            caja1.release();
        }
        return pago;
    }

    public void pagarCaja2(int valorCompra){
        //Los pasajeros van a esperar su turno para pagar
        try {
            caja2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " pagó " + valorCompra + " pesos!!!");
        //Sale de la caja
        caja2.release();
    }
    
    public boolean intentarPagarCaja2(int valorCompra){
        //Los pasajeros intentan pagar, se fijan si hay un lugar libre
        boolean pago = false;
        if(caja2.tryAcquire()){
            System.out.println(Thread.currentThread().getName() + " pagó " + valorCompra + " pesos!!!");
            pago = true;
            //Sale de la caja
            caja2.release();
        }
        return pago;
    }
}
