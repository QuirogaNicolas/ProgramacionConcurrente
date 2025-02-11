package TrabajoFinalAeropuerto;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class Aeropuerto {
    public static void main(String[] args){
        System.out.println("¿Cuántos pasajeros habrá?");
        int cantHilos = TecladoIn.readInt();
        System.out.println("¿Cuántos puestos de informes habrá?");
        int cantPi = TecladoIn.readInt();
        System.out.println("¿Cuántos hilos tendrán las colas de los puestos de atención?");
        int cantCola = TecladoIn.readInt();
        System.out.println("¿Cuántos hilos podrán viajar en el tren?");
        int cantTren = TecladoIn.readInt();
        System.out.println("¿Cuánto tiempo tardan en despegar los vuelos (en segundos)?");
        int tiempoDespegue = TecladoIn.readInt();
        //int horarioAtencion = 2;
        Map<String, Object[]> mapaAerolineas = new HashMap<>();
        Map<String, Object[]> mapaTerminales = new HashMap<>();
        String[] vuelos = new String[]{"FBY300","AAS150","ARG50"};
        Random random = new Random();
        
        Thread[] pool = new Thread[cantHilos];

        ScheduledExecutorService partidaVueloFBY = Executors.newScheduledThreadPool(1);

        ColaPuestoAtencion c1 = new ColaPuestoAtencion(cantCola);
        ColaPuestoAtencion c2 = new ColaPuestoAtencion(cantCola);
        ColaPuestoAtencion c3 = new ColaPuestoAtencion(cantCola);

        Guardia g1 = new Guardia(c1);
        Guardia g2 = new Guardia(c2);
        Guardia g3 = new Guardia(c3);

        Avion a1 = new Avion();
        Avion a2 = new Avion();
        Avion a3 = new Avion();

        Freeshop f1 = new Freeshop();
        Freeshop f2 = new Freeshop();
        Freeshop f3 = new Freeshop();
        
        PuestoInformes[] pi = new PuestoInformes[cantPi];
        for(int i = 0; i < cantPi; i++){
            pi[i] = new PuestoInformes(mapaAerolineas);
        }

        Tren elTren = new Tren(cantTren);
        Maquinista chofer = new Maquinista(elTren, new int[]{1,2,3});

        mapaTerminales.put("FBY", new Object[]{elTren, 1,a1,f1});
        mapaTerminales.put("AAS", new Object[]{elTren, 2,a2,f2});
        mapaTerminales.put("ARG", new Object[]{elTren, 3,a3,f3});

        mapaAerolineas.put("ARG", new Object[]{c1,new PuestoAtencion(c1, mapaTerminales), null, null});
        mapaAerolineas.put("AAS", new Object[]{c2,new PuestoAtencion(c2, mapaTerminales), null, null});
        mapaAerolineas.put("FBY", new Object[]{c3,new PuestoAtencion(c3, mapaTerminales), null, null});
        
        //Se crean los procesos
        //Solo quiero el timer, no quiero ninguna acción después de eso (por eso lo pongo en vacío)
        ScheduledFuture<?> tiempoRestante = partidaVueloFBY.schedule(() -> {}, tiempoDespegue, TimeUnit.SECONDS);

        EmpleadoEmbarque em1 = new EmpleadoEmbarque(tiempoRestante, a1);
        EmpleadoEmbarque em2 = new EmpleadoEmbarque(tiempoRestante, a2);
        EmpleadoEmbarque em3 = new EmpleadoEmbarque(tiempoRestante, a3);

        for(int i = 0; i < cantHilos; i++){
            //Creamos todos los pasajeros y le asignamos a cada uno un vuelo random
            pool[i] = new Thread(new Pasajero(vuelos[random.nextInt(3)],pi[random.nextInt(cantPi)], tiempoRestante),("P"+i));
        }

        //Se crean los hilos
        Thread h5 = new Thread(g1,"Guardia 1");
        Thread h6 = new Thread(g2,"Guardia 2");
        Thread h7 = new Thread(g3,"Guardia 3");
        Thread h8 = new Thread(chofer, "Marcos, el maquinista");
        Thread h9 = new Thread(em1, ("Empleado de embarque vuelo " + vuelos[0]));
        Thread h10 = new Thread(em2, ("Empleado de embarque vuelo " + vuelos[1]));
        Thread h11 = new Thread(em3, ("Empleado de embarque vuelo " + vuelos[2]));

        //Se inician
        for(int i = 0; i < cantHilos;i++){
            pool[i].start();
        }
        h5.start();
        h6.start();
        h7.start();
        h8.start();
        h9.start();
        h10.start();
        h11.start();

        try {
            //Ejecutamos por 50 segundos
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        

         try {
            //Esperamos a que terminen los hilos
            h5.interrupt();
            h6.interrupt();
            h7.interrupt();
            h8.interrupt();
            h5.join();
            h6.join();
            h7.join();
            h8.join();
            h9.join();
            h10.join();
            h11.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Cierra el aeropuerto
        System.out.println("terminamos");
    }
}
