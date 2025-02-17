package TrabajoFinalAeropuerto;


public class Guardia implements Runnable{
    private ColaPuestoAtencion cola;

    public Guardia(ColaPuestoAtencion cola){
        this.cola = cola;
    }

    @Override
    public void run() {
        //El día laboral del guardia
        try {
            while (!Thread.currentThread().isInterrupted()) {
                //Interrumpimos al hilo para que deje de trabajar
                cola.anunciarHall();
            }  
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " dejó de trabajar");
        }
    }
}
