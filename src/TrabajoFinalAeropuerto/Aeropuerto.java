package TrabajoFinalAeropuerto;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Exchanger;

public class Aeropuerto {
    public static void main(String[] args){
        //int horarioAtencion = 2;
        Map<String, PuestoAtencion> mapaAerolineas = new HashMap<>();
        Exchanger<Object> e1 = new Exchanger<Object>();

        mapaAerolineas.put("ARG", new PuestoAtencion("Puesto A1", "Aerolíneas Argentinas"));
        mapaAerolineas.put("AAS", new PuestoAtencion("Puesto B2", "American Airlines"));
        mapaAerolineas.put("FBY", new PuestoAtencion("Puesto C3", "Flybondi"));

        //Se crean los procesos
        Pasajero p1 = new Pasajero(1,"Flybondi","FBY300",e1);
        Pasajero p2 = new Pasajero(2,"Flybondi","FBY300",e1);
        Pasajero p3 = new Pasajero(3,"Flybondi","FBY300",e1);

        PuestoInformes pi1 = new PuestoInformes(e1, 4, mapaAerolineas);

        //Se crean los hilos
        //Thread h1 = new Thread(p1)
        Thread h1 = new Thread(p1);
        Thread h2 = new Thread(p2);
        Thread h3 = new Thread(p3);
        Thread h4 = new Thread(pi1);

        //Se inician
        //h1.start()
        h4.start();
        h1.start();
        h2.start();
        h3.start();

        try {
            //Ejecutamos por 5 segundos para probar
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        pi1.detener();

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
