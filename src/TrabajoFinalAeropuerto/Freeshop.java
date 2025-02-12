package TrabajoFinalAeropuerto;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Freeshop {
    private Semaphore semEntrar = new Semaphore(3, true);
    private Caja caja1 = new Caja();
    private Caja caja2 = new Caja();

    public void entrarFreeShop(){
        try {
            semEntrar.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pagarFreeshop(int valorCompra){
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
        System.out.println(Thread.currentThread().getName() + " se fue del freeshop");
        semEntrar.release();
    }
}
