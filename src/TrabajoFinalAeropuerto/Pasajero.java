package TrabajoFinalAeropuerto;

import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Pasajero implements Runnable{
    //Atributos de los pasajeros
    private String vuelo;
    private PuestoInformes puestoInforme;
    private Object[] informacion;
    private ScheduledFuture<?> partida;

    
    //Nuevo pasajero
    public Pasajero(String vuelo, PuestoInformes pi, ScheduledFuture<?> partida){
        this.vuelo = vuelo;
        this.puestoInforme = pi;
        this.informacion = new Object[4];
        this.partida = partida;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " tiene el vuelo " + vuelo);
        //Visita PI
        informacion = puestoInforme.asignarPuestoAtencion(vuelo);
        //El hilo puesto de informes le va a asignar un puesto de atención
        System.out.println("El pasajero "+ Thread.currentThread().getName() +" se le asignó el puesto de atención "+ ((PuestoAtencion) informacion[1]).getId());
        try {
            //Visita PA
            ((ColaPuestoAtencion) informacion[0]).consultarLugar();
            //El hilo puesto de atención le va a dar un folleto con la información necesaria para el resto del recorrido
            informacion = ((PuestoAtencion) informacion[1]).hacerCheckIn(vuelo);; 
            //Visita Tren
            ((Tren) informacion[0]).abordar();
            //Se sube
            ((Tren) informacion[0]).bajar((int)informacion[1]); 
            //Considera visitar el FreeShop (solo si tiene tiempo)
            if(partida.getDelay(TimeUnit.SECONDS) >= 5){
                Random tengoGanas = new Random();
                if(tengoGanas.nextInt(2) == 0){
                    //Tiene ganas de entrar al freeshop
                    System.out.println(Thread.currentThread().getName() + " voy a entrar al freeshop");
                    ((Freeshop) informacion[3]).entrarFreeShop();
                    Random quieroComprar = new Random();
                    if(quieroComprar.nextInt(2) == 0){
                        //Decide si comprar algo o no
                        ((Freeshop) informacion[3]).pagarFreeshop(quieroComprar.nextInt(500));
                        ((Freeshop) informacion[3]).salirFreeShop();
                    } else{
                        System.out.println(Thread.currentThread().getName() + " no quiero comprar nada");
                        ((Freeshop) informacion[3]).salirFreeShop();
                    }
                } else{
                    System.out.println(Thread.currentThread().getName() + " no quiero entrar al freeshop");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " no hay tiempo para el freeshop");
            }

            //Espera a hacer el embarque
            System.out.println(Thread.currentThread().getName() + " espero a embarcar");
            if(((Avion) informacion[2]).esperarEmbarque()){
                //Se sube al avión
                System.out.println(Thread.currentThread().getName() + " se subió al avión");
            }else{
                System.out.println(Thread.currentThread().getName() + " la pucha se me fue el avión!");
            }
        } catch (InterruptedException e) {
            //En caso de haber sido interrumpido mientras estaba en alguna espera
            System.out.println(Thread.currentThread().getName() + " se me hizo tarde, me voy!");
        }
        
    }

}
