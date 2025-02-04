package TrabajoFinalAeropuerto;

import java.util.Map;
import java.util.concurrent.Exchanger;

public class PuestoInformes implements Runnable {
    private int idPi;
    private Exchanger<Object> exchanger;
    private Map<String, PuestoAtencion> mapaAerolineas;
    private boolean abierto;

public PuestoInformes(Exchanger<Object> ex, int id, Map<String,PuestoAtencion> mapa){
    this.exchanger = ex;
    this.idPi = id;
    this.mapaAerolineas = mapa;
    this.abierto = true;
}

private PuestoAtencion asignarPuestoAtencion(String vuelo){
    return mapaAerolineas.get(vuelo.substring(0,3)); //devuelve el puesto de atención correpondiente según las primeras 3 letras del código de vuelo
}

public void detener(){
    this.abierto = false;
}

public void run() {
    String vuelo = "";
    while(abierto){
        try {
            vuelo = (String) exchanger.exchange(null);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("PI recibió el vuelo: " + vuelo);
        try {
            exchanger.exchange(asignarPuestoAtencion(vuelo));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

}
