package TrabajoFinalAeropuerto;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PuestoInformes {
    private Lock lock;
    private Map<String, Object[]> mapaAerolineas;
    

public PuestoInformes(int id, Map<String,Object[]> mapa){
    this.lock = new ReentrantLock();
    this.mapaAerolineas = mapa;
}

public Object[] asignarPuestoAtencion(String vuelo){
    lock.lock();
    try {
        //devuelve el puesto de atención correpondiente según las primeras 3 letras del código de vuelo
        return mapaAerolineas.get(vuelo.substring(0,3));
    } finally {
        lock.unlock();
    }
     
    
}
/* 
public void detener(){
    this.abierto = false;
}

public void run() {
    String vuelo;
    while(abierto){
        try {
            vuelo = (String) exchanger.exchange(null);
            System.out.println("PI recibió el vuelo: " + vuelo);
            PuestoAtencion puestoAsignado = asignarPuestoAtencion(vuelo);
            exchanger.exchange(puestoAsignado);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}*/

}
