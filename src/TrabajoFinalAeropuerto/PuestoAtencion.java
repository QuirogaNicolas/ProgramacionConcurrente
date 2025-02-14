package TrabajoFinalAeropuerto;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class PuestoAtencion {
    private ReentrantLock hacerTramite; 
    private Map<String, Object[]> mapaTerminales;
    private ColaPuestoAtencion cola;
    private int id;

    public PuestoAtencion(int id, ColaPuestoAtencion cola,Map<String, Object[]> mapaTerminales ){
        this.hacerTramite = new ReentrantLock(true);
        this.mapaTerminales = mapaTerminales;
        this.cola = cola;
        this.id = id;
    }

    public Object[] hacerCheckIn(String vuelo){
        //Los pasajeros van a dar su código de vuelo y les van a asignar toda la información necesaria para el resto de su travesía 
        hacerTramite.lock();
        System.out.println(Thread.currentThread().getName() + " está haciendo el trámite");
        Object[] info;
        info = mapaTerminales.get(vuelo.substring(0,3));
        cola.pasaSiguiente();
        hacerTramite.unlock();
        return info;
    }

    public int getId(){
        return id;
    }
}
