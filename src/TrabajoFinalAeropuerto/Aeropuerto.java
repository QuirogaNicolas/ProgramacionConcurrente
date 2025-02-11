package TrabajoFinalAeropuerto;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Aeropuerto {
    public static void main(String[] args){
        //int horarioAtencion = 2;
        Map<String, Object[]> mapaAerolineas = new HashMap<>();
        Map<String, Object[]> mapaTerminales = new HashMap<>();
        
        ScheduledExecutorService partidaVueloFBY = Executors.newScheduledThreadPool(1);

        ColaPuestoAtencion c1 = new ColaPuestoAtencion(1);
        ColaPuestoAtencion c2 = new ColaPuestoAtencion(1);
        ColaPuestoAtencion c3 = new ColaPuestoAtencion(1);

        Guardia g1 = new Guardia(c1);
        Guardia g2 = new Guardia(c2);
        Guardia g3 = new Guardia(c3);

        Avion a1 = new Avion();
        Avion a2 = new Avion();
        Avion a3 = new Avion();

        Freeshop f1 = new Freeshop();
        Freeshop f2 = new Freeshop();
        Freeshop f3 = new Freeshop();
        
        PuestoInformes pi1 = new PuestoInformes( 4, mapaAerolineas);
        Tren elTren = new Tren(3);
        Maquinista chofer = new Maquinista(elTren, new int[]{1,2,3});

        mapaTerminales.put("FBY", new Object[]{elTren, 1,a1,f1});
        mapaTerminales.put("AAS", new Object[]{elTren, 2,a2,f2});
        mapaTerminales.put("ARG", new Object[]{elTren, 3,a3,f3});

        mapaAerolineas.put("ARG", new Object[]{c1,new PuestoAtencion(c1, mapaTerminales), null, null});
        mapaAerolineas.put("AAS", new Object[]{c2,new PuestoAtencion(c2, mapaTerminales), null, null});
        mapaAerolineas.put("FBY", new Object[]{c3,new PuestoAtencion(c3, mapaTerminales), null, null});
        
        //Se crean los procesos
        //Solo quiero el timer, no quiero ninguna acción después de eso (por eso lo pongo en vacío)
        ScheduledFuture<?> tiempoRestante = partidaVueloFBY.schedule(() -> {}, 10, TimeUnit.SECONDS);

        //Pasajero p1 = new Pasajero(1,"Flybondi","FBY300",pi1);
        //Pasajero p2 = new Pasajero(2,"American Airlines","AAS150",pi1);
        //Pasajero p3 = new Pasajero(3,"Aerolineas Argentinas","ARG50",pi1);
        //Pasajero p4 = new Pasajero(3,"Aerolineas Argentinas","ARG68",pi1);

        EmpleadoEmbarque em1 = new EmpleadoEmbarque(tiempoRestante, a1);

        Pasajero p1 = new Pasajero(1,"Flybondi","FBY300",pi1, elTren, tiempoRestante);
        Pasajero p2 = new Pasajero(2,"Flybondi","FBY300",pi1, elTren, tiempoRestante);
        Pasajero p3 = new Pasajero(3,"Flybondi","FBY300",pi1, elTren, tiempoRestante);
        Pasajero p4 = new Pasajero(4,"Flybondi","FBY300",pi1, elTren, tiempoRestante);



        //Se crean los hilos
        Thread h1 = new Thread(p1,"p1");
        Thread h2 = new Thread(p2,"p2");
        Thread h3 = new Thread(p3,"p3");
        Thread h4 = new Thread(p4,"p4");
        Thread h5 = new Thread(g1,"g1");
        Thread h6 = new Thread(g2,"g2");
        Thread h7 = new Thread(g3,"g3");
        Thread h8 = new Thread(chofer, "Marcos, el chofer");
        Thread h9 = new Thread(em1, "em1");

        //Se inician
        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();
        h6.start();
        h7.start();
        h8.start();
        h9.start();


        try {
            //Ejecutamos por 5 segundos para probar
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        

         try {
            //Esperamos a que terminen los hilos
            h5.interrupt();
            h6.interrupt();
            h7.interrupt();
            h8.interrupt();
            h1.join();
            h2.join();
            h3.join();
            h4.join();
            h5.join();
            h6.join();
            h7.join();
            h8.join();
            h9.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Cierra el aeropuerto
        System.out.println("terminamos");
    }
}
