package TrabajoFinalAeropuerto;

import java.util.Map;

public class PuestoInformes {
    private Map<String, Object[]> mapaAerolineas;
    private boolean desocupado; 
    

public PuestoInformes(Map<String,Object[]> mapa){
    this.mapaAerolineas = mapa;
    this.desocupado = true;
}

public synchronized Object[] asignarPuestoAtencion(String vuelo){
    while (!desocupado) {
        try {
            this.wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " interrumpido en la espera del puesto de informes");
        }
    }
    desocupado = false;
    //Devuelve el puesto de atención correpondiente según las primeras 3 letras del código de vuelo
    return mapaAerolineas.get(vuelo.substring(0,3));
}

public synchronized void liberarPuesto(){
    desocupado = true; 
    notify();
}

}
