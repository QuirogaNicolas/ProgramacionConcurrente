package TrabajoFinalAeropuerto;

public class PuestoInformes implements runnable {
    private int idPi;
    private Exchanger<Object> exchanger;
    private Map<String, PuestoAtencion> mapaAerolineas;
    private boolean abierto;

public puestoInforme(Exchanger ex, int id, Map mapa){
    this.exchanger = ex;
    this.idPi = id;
    this.mapaAerolineas = mapa;
    this.abierto = true;
}

private PuestoAtencion asignarPuestoAtencion(String vuelo){
    return mapaAerolineas.get(vuelo.substring(0,3)) //devuelve el puesto de atención correpondiente según las primeras 3 letras del código de vuelo
}

public void detener(){
    this.abierto = false;
}

public void run() {
    String vuelo = "";
    while(abierto){
        vuelo = exchanger.exchange(null);
        System.out.println("PI recibió el vuelo: " + vuelo);
        exchanger.exchange(asignarPuestoAtencion(vuelo))
    }
}

}
