package TrabajoFinalAeropuerto;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class PuestoAtencion {
    private ReentrantLock hacerTramite; 
    private Map<String, Object[]> mapaTerminales;
    private ColaPuestoAtencion cola;

    public PuestoAtencion(ColaPuestoAtencion cola,Map<String, Object[]> mapaTerminales ){
        this.hacerTramite = new ReentrantLock(true);
        this.mapaTerminales = mapaTerminales;
        this.cola = cola;
    }

    public Object[] hacerCheckIn(String vuelo){
        hacerTramite.lock();
        System.out.println(Thread.currentThread().getName() + " está haciendo el trámite");
        Object[] info;
        info = mapaTerminales.get(vuelo.substring(0,3));
        hacerTramite.unlock();
        cola.pasaSiguiente();
        return info;
    }

}
