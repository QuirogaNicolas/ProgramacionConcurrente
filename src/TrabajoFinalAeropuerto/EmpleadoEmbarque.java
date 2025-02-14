package TrabajoFinalAeropuerto;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class EmpleadoEmbarque implements Runnable{
    private ScheduledFuture<?> partida;
    private Avion avion;

    public EmpleadoEmbarque(ScheduledFuture<?> partida, Avion avion){
        this.partida = partida;
        this.avion = avion;
    }

    public void run(){
        while (partida.getDelay(TimeUnit.SECONDS) > 0) {
            try {
                //Si todavía falta, el hilo duerme
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " va a iniciar el embarque");
        //Iniciamos el embarque y les damos 2 segundos más de espera
        avion.iniciarEmbarque();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " anuncia el despegue");
        avion.despegue();
        System.out.println(Thread.currentThread().getName() + " terminó de trabajar");
    }

}
