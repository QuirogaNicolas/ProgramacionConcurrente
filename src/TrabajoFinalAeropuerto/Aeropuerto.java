package TrabajoFinalAeropuerto;

import java.util.HashMap;
import java.util.Map;

public class Aeropuerto {
    public static void main(String[] args){
        //int horarioAtencion = 2;
        Map<String, Object[]> mapaAerolineas = new HashMap<>();
        

        ColaPuestoAtencion c1 = new ColaPuestoAtencion(3);
        ColaPuestoAtencion c2 = new ColaPuestoAtencion(3);
        ColaPuestoAtencion c3 = new ColaPuestoAtencion(3);

        mapaAerolineas.put("ARG", new Object[]{c1,new PuestoAtencion(c1)});
        mapaAerolineas.put("AAS", new Object[]{c2,new PuestoAtencion(c2)});
        mapaAerolineas.put("FBY", new Object[]{c3,new PuestoAtencion(c3)});

        //Se crean los procesos
        PuestoInformes pi1 = new PuestoInformes( 4, mapaAerolineas);
        
        Pasajero p1 = new Pasajero(1,"Flybondi","FBY300",pi1);
        Pasajero p2 = new Pasajero(2,"American Airlines","AAS150",pi1);
        Pasajero p3 = new Pasajero(3,"Aerolineas Argentinas","ARG50",pi1);
        Pasajero p4 = new Pasajero(3,"Aerolineas Argentinas","ARG68",pi1);


        //Se crean los hilos
        //Thread h1 = new Thread(p1)
        Thread h1 = new Thread(p1);
        Thread h2 = new Thread(p2);
        Thread h3 = new Thread(p3);
        Thread h4 = new Thread(p4);

        //Se inician
        //h1.start()
        h1.start();
        h2.start();
        h3.start();
        h4.start();

        try {
            //Ejecutamos por 5 segundos para probar
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        

         try {
            //Esperamos a que terminen los hilos
            h1.join();
            h2.join();
            h3.join();
            h4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Cierra el aeropuerto
        System.out.println("terminamos");
    }
}
