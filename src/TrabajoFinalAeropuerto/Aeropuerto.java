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
        //Preguntas al usuario
        System.out.println("¿Cuántos pasajeros habrá?");
        int cantHilos = TecladoIn.readInt();
        System.out.println("¿Cuántos hilos tendrán las colas de los puestos de atención?");
        int cantCola = TecladoIn.readInt();
        System.out.println("¿Cuántos hilos podrán viajar en el tren?");
        int cantTren = TecladoIn.readInt();
        System.out.println("¿Cuánto tiempo tardan en despegar los vuelos (en segundos)?");
        int tiempoDespegue = TecladoIn.readInt();
        System.out.println("¿Cuánto tiempo va a estar abierto el aeropuerto (en segundos)?");
        int tiempoAeropuertoAbierto = TecladoIn.readInt();
       
        //Definimos objetos
        Map<String, Object[]> mapaAerolineas = new HashMap<>();
        Map<String, Object[]> mapaTerminales = new HashMap<>();
        Map<String, Avion> vuelosDelDia = new HashMap<>();
        String[] vuelos = new String[]{"FBY300","AAS150","ARG50","FBY125","AAS90","ARG630"};
        Random random = new Random();
        
        Thread[] pool = new Thread[cantHilos];

        ScheduledExecutorService partidaVuelo = Executors.newScheduledThreadPool(1);

        ColaPuestoAtencion colaPAtencion = new ColaPuestoAtencion(cantCola);

        Guardia guardia = new Guardia(colaPAtencion);

        Avion avionFBY300 = new Avion();
        Avion avionAAS150 = new Avion();
        Avion avionARG50 = new Avion();
        Avion avionFBY125 = new Avion();
        Avion avionAAS90 = new Avion();
        Avion avionARG630 = new Avion();

        //TENGO QUE PASARLE LOS VUELOS DEL DÍA
        vuelosDelDia.put("FBY300", avionFBY300);
        vuelosDelDia.put("FBY125", avionFBY125);
        Terminal terminal1 = new Terminal(vuelosDelDia);
        vuelosDelDia.clear();
        vuelosDelDia.put("AAS150", avionAAS150);
        vuelosDelDia.put("AAS90", avionAAS90);
        Terminal terminal2 = new Terminal(vuelosDelDia);
        vuelosDelDia.clear();
        vuelosDelDia.put("ARG50", avionARG50);
        vuelosDelDia.put("ARG630", avionARG630);
        Terminal terminal3 = new Terminal(vuelosDelDia);
        
        PuestoInformes puestoInforme = new PuestoInformes(mapaAerolineas);

        Tren elTren = new Tren(cantTren);
        Maquinista chofer = new Maquinista(elTren, new int[]{1,2,3});

        mapaTerminales.put("FBY", new Object[]{elTren, 1,null,terminal1, null});
        mapaTerminales.put("AAS", new Object[]{elTren, 2,null,terminal2, null});
        mapaTerminales.put("ARG", new Object[]{elTren, 3,null,terminal3, null});

        mapaAerolineas.put("ARG", new Object[]{colaPAtencion,new PuestoAtencion(1, colaPAtencion, mapaTerminales), null, null, null});
        mapaAerolineas.put("AAS", new Object[]{colaPAtencion,new PuestoAtencion(2, colaPAtencion, mapaTerminales), null, null, null});
        mapaAerolineas.put("FBY", new Object[]{colaPAtencion,new PuestoAtencion(3, colaPAtencion, mapaTerminales), null, null, null});
        
        //Se crean los procesos
        //Solo quiero el timer, no quiero ninguna acción después de eso (por eso lo pongo en vacío)
        ScheduledFuture<?> tiempoRestante = partidaVuelo.schedule(() -> {}, tiempoDespegue, TimeUnit.SECONDS);

        EmpleadoEmbarque empleadoEmbarque1 = new EmpleadoEmbarque(tiempoRestante, avionAAS150);
        EmpleadoEmbarque empleadoEmbarque2 = new EmpleadoEmbarque(tiempoRestante, avionAAS90);
        EmpleadoEmbarque empleadoEmbarque3 = new EmpleadoEmbarque(tiempoRestante, avionARG50);
        EmpleadoEmbarque empleadoEmbarque4 = new EmpleadoEmbarque(tiempoRestante, avionARG630);
        EmpleadoEmbarque empleadoEmbarque5 = new EmpleadoEmbarque(tiempoRestante, avionFBY125);
        EmpleadoEmbarque empleadoEmbarque6 = new EmpleadoEmbarque(tiempoRestante, avionFBY300);

        for(int i = 0; i < cantHilos; i++){
            //Creamos todos los pasajeros y le asignamos a cada uno un vuelo random
            pool[i] = new Thread(new Pasajero(vuelos[random.nextInt(6)],puestoInforme, tiempoRestante),("P"+i));
        }

        //Se crean los hilos
        Thread h5 = new Thread(guardia,"Guardia");
        Thread h8 = new Thread(chofer, "Marcos, el maquinista");
        Thread h9 = new Thread(empleadoEmbarque1, ("Empleado de embarque vuelo AAS150"));
        Thread h10 = new Thread(empleadoEmbarque2, ("Empleado de embarque vuelo AAS90"));
        Thread h11 = new Thread(empleadoEmbarque3, ("Empleado de embarque vuelo ARG50"));
        Thread h12 = new Thread(empleadoEmbarque4, ("Empleado de embarque vuelo ARG630"));
        Thread h13 = new Thread(empleadoEmbarque5, ("Empleado de embarque vuelo FBY125"));
        Thread h14 = new Thread(empleadoEmbarque6, ("Empleado de embarque vuelo FBY300"));

        //Se inician
        for(int i = 0; i < cantHilos;i++){
            pool[i].start();
        }
        h5.start();
        h8.start();
        h9.start();
        h10.start();
        h11.start();
        h12.start();
        h13.start();
        h14.start();
        
        try {
            Thread.sleep(tiempoAeropuertoAbierto * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("a mimiiiiiiir los hiloooos");

        try {
             h5.interrupt();
             elTren.cerrar();
             h8.interrupt();
            for(int i = 0; i < cantHilos; i++){
                pool[i].interrupt();
                pool[i].join();
            }
            //Esperamos a que terminen los hilos
            h5.join();
            h8.join();
            //Estos hilos van a morir solos porque son los encargados de dar la señal de embarque y salida del avión
            h9.join();
            h10.join();
            h11.join();
            h12.join();
            h13.join();
            h14.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Cierra el aeropuerto
        System.out.println("Cerró el aeropuerto");
    }
}
