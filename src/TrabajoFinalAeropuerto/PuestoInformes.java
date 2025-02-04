package TrabajoFinalAeropuerto;

public class PuestoInformes implements runnable {
    private int idPi;
    private Exchanger<Object> exchanger;

public puestoInforme(Exchanger ex, int id){
    this.exchanger = ex;
    this.idPi = id;
}

private asignarPuestoAtencion(String vuelo){
    
}

public void run() {
    String vuelo = "";
    while(true){
        vuelo = exchanger.exchange(null);
        System.out.println("PI recibió el vuelo: " + vuelo);
        exchanger.exchange(asignarPuestoAtencion(vuelo))
    }
}

}
