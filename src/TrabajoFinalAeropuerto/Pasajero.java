package TrabajoFinalAeropuerto;

import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Pasajero implements Runnable{
    //Atributos de los pasajeros
    private int idPasajero;
    private String aerolineaPasajero; //capaz aerolinea está de más
    private String vuelo;
    private PuestoInformes puestoInforme;
    private Object[] informacion;
    private ScheduledFuture<?> partida;
    private boolean meSubi;

    
    //Nuevo pasajero
    public Pasajero(int id, String aerolinea, String vuelo, PuestoInformes pi, Tren elTren, ScheduledFuture<?> partida){
        this.idPasajero = id;
        this.aerolineaPasajero = aerolinea;
        this.vuelo = vuelo;
        this.puestoInforme = pi;
        this.informacion = new Object[4];
        this.partida = partida;
        this.meSubi = false;
    }

    @Override
    public void run() {
        //Visita PI
        informacion = puestoInforme.asignarPuestoAtencion(vuelo);
        //El hilo puesto de informes le va a asignar un puesto de atención y aerolinea según el vuelo que tenga
        System.out.println("El pasajero "+ Thread.currentThread().getName() +" se le asignó el puesto de atención");
        
        //Visita PA
        ((ColaPuestoAtencion) informacion[0]).consultarLugar();

        //El hilo puesto de atención le va a dar un folleto con la información necesaria para el resto del recorrido
        informacion = ((PuestoAtencion) informacion[1]).hacerCheckIn(vuelo);; 

        //Visita Tren
        ((Tren) informacion[0]).abordar();
        //se sube
        ((Tren) informacion[0]).bajar((int)informacion[1]); //El tren le debe avisar en qué terminal está y el pasajero comparar con la terminal que se le asigno

        //Considera visitar el FreeShop (solo si tiene tiempo)
        if(partida.getDelay(TimeUnit.SECONDS) >= 5){
            Random tengoGanas = new Random();
            if(tengoGanas.nextInt(2) == 0){
                //Tiene ganas de entrar al freeshop
                System.out.println(Thread.currentThread().getName() + " voy a entrar al freeshop");
                ((Freeshop) informacion[3]).entrarFreeShop();
                Random quieroComprar = new Random();
                if(quieroComprar.nextInt(2) == 0){
                    ((Freeshop) informacion[3]).pagarFreeshop(quieroComprar.nextInt(500));
                    ((Freeshop) informacion[3]).salirFreeShop();
                }
            }
        }

        //Espera a hacer el embarque
        System.out.println(Thread.currentThread().getName() + " espero a embarcar");
        if(((Avion) informacion[2]).esperarEmbarque()){
            System.out.println(Thread.currentThread().getName() + " se subió al avión");
        }
    }

}
