package TrabajoFinalAeropuerto;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Aeropuerto {
    public static void main(String[] args) {
        // Preguntas al usuario
        System.out.println("¿Cuántos pasajeros habrá?");
        int cantHilos = TecladoIn.readInt();
        System.out.println("¿Cuántos hilos tendrá la cola de los puestos de atención?");
        int cantCola = TecladoIn.readInt();
        System.out.println("¿Cuántos hilos podrán viajar en el tren?");
        int cantTren = TecladoIn.readInt();
        System.out.println("¿Cuánto tiempo tardará en despegar el primer vuelo (en segundos)?");
        int tiempoDespegue = TecladoIn.readInt();

        // Definimos objetos
        Map<String, Object[]> mapaAerolineas = new HashMap<>();
        Map<String, Object[]> mapaTerminales = new HashMap<>();
        Map<String, Avion> vuelosDelDia = new HashMap<>();
        String[] vuelos = new String[] { "FBY300", "AAS150", "ARG50", "FBY125", "AAS90", "ARG630" };
        ScheduledFuture<?>[] timers;
        Random random = new Random();
        int asignado;

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

        // Paso los vuelos del día a cada terminal
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
        Maquinista chofer = new Maquinista(elTren, new int[] { 1, 2, 3 });

        mapaTerminales.put("FBY", new Object[] { elTren, 1, null, terminal1, null });
        mapaTerminales.put("AAS", new Object[] { elTren, 2, null, terminal2, null });
        mapaTerminales.put("ARG", new Object[] { elTren, 3, null, terminal3, null });

        mapaAerolineas.put("ARG",
                new Object[] { colaPAtencion, new PuestoAtencion(1, colaPAtencion, mapaTerminales), null, null, null });
        mapaAerolineas.put("AAS",
                new Object[] { colaPAtencion, new PuestoAtencion(2, colaPAtencion, mapaTerminales), null, null, null });
        mapaAerolineas.put("FBY",
                new Object[] { colaPAtencion, new PuestoAtencion(3, colaPAtencion, mapaTerminales), null, null, null });

        // Se crean los procesos
        // Creo un timer para cada avión
        ScheduledFuture<?> tiempoRestanteAAS150 = partidaVuelo.schedule(() -> {
        }, tiempoDespegue, TimeUnit.SECONDS);
        ScheduledFuture<?> tiempoRestanteAAS90 = partidaVuelo.schedule(() -> {
        }, tiempoDespegue + 10, TimeUnit.SECONDS);
        ScheduledFuture<?> tiempoRestanteARG50 = partidaVuelo.schedule(() -> {
        }, tiempoDespegue + 3, TimeUnit.SECONDS);
        ScheduledFuture<?> tiempoRestanteARG630 = partidaVuelo.schedule(() -> {
        }, tiempoDespegue + 13, TimeUnit.SECONDS);
        ScheduledFuture<?> tiempoRestanteFBY125 = partidaVuelo.schedule(() -> {
        }, tiempoDespegue + 6, TimeUnit.SECONDS);
        ScheduledFuture<?> tiempoRestanteFBY300 = partidaVuelo.schedule(() -> {
        }, tiempoDespegue + 16, TimeUnit.SECONDS);

        timers = new ScheduledFuture<?>[] { tiempoRestanteAAS150, tiempoRestanteAAS90, tiempoRestanteARG50,
                tiempoRestanteARG630, tiempoRestanteFBY125, tiempoRestanteFBY300 };

        EmpleadoEmbarque empleadoEmbarque1 = new EmpleadoEmbarque(tiempoRestanteAAS150, avionAAS150);
        EmpleadoEmbarque empleadoEmbarque2 = new EmpleadoEmbarque(tiempoRestanteAAS90, avionAAS90);
        EmpleadoEmbarque empleadoEmbarque3 = new EmpleadoEmbarque(tiempoRestanteARG50, avionARG50);
        EmpleadoEmbarque empleadoEmbarque4 = new EmpleadoEmbarque(tiempoRestanteARG630, avionARG630);
        EmpleadoEmbarque empleadoEmbarque5 = new EmpleadoEmbarque(tiempoRestanteFBY125, avionFBY125);
        EmpleadoEmbarque empleadoEmbarque6 = new EmpleadoEmbarque(tiempoRestanteFBY300, avionFBY300);

        for (int i = 0; i < cantHilos; i++) {
            // Creamos todos los pasajeros y le asignamos a cada uno un vuelo random
            asignado = random.nextInt(6);
            pool[i] = new Thread(new Pasajero(vuelos[asignado], puestoInforme, timers[asignado]), ("P" + i));
        }

        // Se crean los hilos
        Thread hiloGuardia = new Thread(guardia, "Guardia");
        Thread hiloMaquinista = new Thread(chofer, "Marcos, el maquinista");
        Thread hiloEmp1 = new Thread(empleadoEmbarque1, ("Empleado de embarque vuelo AAS150"));
        Thread hiloEmp2 = new Thread(empleadoEmbarque2, ("Empleado de embarque vuelo AAS90"));
        Thread hiloEmp3 = new Thread(empleadoEmbarque3, ("Empleado de embarque vuelo ARG50"));
        Thread hiloEmp4 = new Thread(empleadoEmbarque4, ("Empleado de embarque vuelo ARG630"));
        Thread hiloEmp5 = new Thread(empleadoEmbarque5, ("Empleado de embarque vuelo FBY125"));
        Thread hiloEmp6 = new Thread(empleadoEmbarque6, ("Empleado de embarque vuelo FBY300"));

        // Se inician
        for (int i = 0; i < cantHilos; i++) {
            pool[i].start();
        }
        hiloGuardia.start();
        hiloMaquinista.start();
        hiloEmp1.start();
        hiloEmp2.start();
        hiloEmp3.start();
        hiloEmp4.start();
        hiloEmp5.start();
        hiloEmp6.start();

        try {
            Thread.sleep((25 + tiempoDespegue) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            hiloGuardia.interrupt();
            elTren.cerrar();
            hiloMaquinista.interrupt();
            for (int i = 0; i < cantHilos; i++) {
                pool[i].interrupt();
                pool[i].join();
            }
            // Esperamos a que terminen los hilos
            hiloGuardia.join();
            hiloMaquinista.join();
            // Estos hilos van a morir solos porque son los encargados de dar la señal de
            // embarque y salida del avión
            hiloEmp1.join();
            hiloEmp2.join();
            hiloEmp3.join();
            hiloEmp4.join();
            hiloEmp5.join();
            hiloEmp6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Cierra el aeropuerto
        System.out.println("Cerró el aeropuerto");
    }
}
