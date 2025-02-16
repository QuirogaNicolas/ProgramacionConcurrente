package TrabajoFinalAeropuerto;

import java.util.HashMap;
import java.util.Map;

public class Terminal {
    Freeshop freeshop;
    Map<String, Avion> vuelosDelDia;

    public Terminal(Map<String, Avion> vuelosDelDia){
        this.freeshop = new Freeshop();
        this.vuelosDelDia = new HashMap<>(vuelosDelDia);
    }   

    public Avion buscarPuertaEmbarque(String vuelo){
        return vuelosDelDia.get(vuelo);
    }

    public Freeshop localizarFreeshop(){
        return freeshop;
    }

}
