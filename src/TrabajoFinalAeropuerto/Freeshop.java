package TrabajoFinalAeropuerto;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Freeshop {
    private Semaphore semEntrar = new Semaphore(3, true);
    private Caja caja1 = new Caja();
    private Caja caja2 = new Caja();

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
        if (caja1.intentarPagar(valorCompra) || caja2.intentarPagar(valorCompra)) {
            pago = true;       
        }
        if (!pago){
            Random tirarMoneda = new Random();
            //Elije de manera aleatoria entre las dos cajas
            if(tirarMoneda.nextInt(2) == 0){
                caja1.pagar(valorCompra);
            }else{
                caja2.pagar(valorCompra);
            }
        }
    }

    public void salirFreeShop(){
        //Salen los pasajeros del freeshop
        System.out.println(Thread.currentThread().getName() + " se fue del freeshop");
        semEntrar.release();
    }
}
