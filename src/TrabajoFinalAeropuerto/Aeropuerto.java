package TrabajoFinalAeropuerto;

import java.util.HashMap;
import java.util.Map;

public class Aeropuerto {
    public static void main(String[] args){
        //int horarioAtencion = 2;
        Map<String, Object[]> mapaAerolineas = new HashMap<>();
        Map<String, Integer> mapaTerminales = new HashMap<>();
        

        ColaPuestoAtencion c1 = new ColaPuestoAtencion(1);
        ColaPuestoAtencion c2 = new ColaPuestoAtencion(1);
        ColaPuestoAtencion c3 = new ColaPuestoAtencion(1);

        Guardia g1 = new Guardia(c1);
        Guardia g2 = new Guardia(c2);
        Guardia g3 = new Guardia(c3);

        mapaTerminales.put("FBY", 1);
        mapaTerminales.put("AAS", 2);
        mapaTerminales.put("ARG", 3);

        mapaAerolineas.put("ARG", new Object[]{c1,new PuestoAtencion(c1, mapaTerminales)});
        mapaAerolineas.put("AAS", new Object[]{c2,new PuestoAtencion(c2, mapaTerminales)});
        mapaAerolineas.put("FBY", new Object[]{c3,new PuestoAtencion(c3, mapaTerminales)});
        
        //Se crean los procesos
        PuestoInformes pi1 = new PuestoInformes( 4, mapaAerolineas);
        
        //Pasajero p1 = new Pasajero(1,"Flybondi","FBY300",pi1);
        //Pasajero p2 = new Pasajero(2,"American Airlines","AAS150",pi1);
        //Pasajero p3 = new Pasajero(3,"Aerolineas Argentinas","ARG50",pi1);
        //Pasajero p4 = new Pasajero(3,"Aerolineas Argentinas","ARG68",pi1);
        Pasajero p1 = new Pasajero(1,"Flybondi","FBY300",pi1);
        Pasajero p2 = new Pasajero(2,"Flybondi","FBY300",pi1);
        Pasajero p3 = new Pasajero(3,"Flybondi","FBY300",pi1);
        Pasajero p4 = new Pasajero(4,"Flybondi","FBY300",pi1);


        //Se crean los hilos
        Thread h1 = new Thread(p1,"p1");
        Thread h2 = new Thread(p2,"p2");
        Thread h3 = new Thread(p3,"p3");
        Thread h4 = new Thread(p4,"p4");
        Thread h5 = new Thread(g1,"g1");
        Thread h6 = new Thread(g2,"g2");
        Thread h7 = new Thread(g3,"g3");

        //Se inician
        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();
        h6.start();
        h7.start();

        try {
            //Ejecutamos por 5 segundos para probar
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        

         try {
            //Esperamos a que terminen los hilos
            h5.interrupt();
            h6.interrupt();
            h7.interrupt();
            h1.join();
            h2.join();
            h3.join();
            h4.join();
            h5.join();
            h6.join();
            h7.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Cierra el aeropuerto
        System.out.println("terminamos");
    }
}
