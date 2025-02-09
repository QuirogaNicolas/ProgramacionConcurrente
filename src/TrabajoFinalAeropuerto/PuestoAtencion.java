package TrabajoFinalAeropuerto;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class PuestoAtencion {
    private ReentrantLock hacerTramite; 
    private Map<String, Integer> mapaTerminales;
    private ColaPuestoAtencion cola;

    public PuestoAtencion(ColaPuestoAtencion cola,Map<String, Integer> mapaTerminales ){
        this.hacerTramite = new ReentrantLock(true);
        this.mapaTerminales = mapaTerminales;
        this.cola = cola;
    }

    public int hacerCheckIn(String vuelo){
        hacerTramite.lock();
        System.out.println(Thread.currentThread().getName() + " está haciendo el trámite");
        int numTerminal;
        numTerminal = mapaTerminales.get(vuelo.substring(0,3));
        hacerTramite.unlock();
        cola.pasaSiguiente();
        return numTerminal;
    }

}
