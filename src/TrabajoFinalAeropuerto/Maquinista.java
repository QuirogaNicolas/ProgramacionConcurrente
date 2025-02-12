package TrabajoFinalAeropuerto;

public class Maquinista implements Runnable{
    private Tren elTren;
    private int[] paradas;

    public Maquinista(Tren tren, int[] paradas){
        this.elTren = tren;
        this.paradas = paradas;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                //Interrumpimos al hilo para que deje de trabajar
                elTren.partir();
                for(int i = 0; i < paradas.length; i++){
                    elTren.parada(paradas[i]);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                for(int j = paradas.length - 1; j >= 0; j--){
                    elTren.parada(paradas[j]);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                elTren.volvimos();
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " dejó de trabajar");
        }
        
    }
}
