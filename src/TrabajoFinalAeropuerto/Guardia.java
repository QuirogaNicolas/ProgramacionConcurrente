package TrabajoFinalAeropuerto;


public class Guardia implements Runnable{
    private ColaPuestoAtencion cola;

    @Override
    public void run() {
        while (true) {
            //OJO CON ESTO
            cola.anunciarHall();
        }
    }
    
    
}
