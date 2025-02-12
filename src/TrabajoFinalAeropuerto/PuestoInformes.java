package TrabajoFinalAeropuerto;

import java.util.Map;

public class PuestoInformes {
    private Map<String, Object[]> mapaAerolineas;
    

public PuestoInformes(Map<String,Object[]> mapa){
    this.mapaAerolineas = mapa;
}

public synchronized Object[] asignarPuestoAtencion(String vuelo){
    //Devuelve el puesto de atención correpondiente según las primeras 3 letras del código de vuelo
    return mapaAerolineas.get(vuelo.substring(0,3));
}


}
