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
        while (!Thread.currentThread().isInterrupted()) {
            //Interrumpimos al hilo para que deje de trabajar
            elTren.partir();
            for(int i = 0; i < paradas.length; i++){
                elTren.parada(i);
            }
            for(int j = paradas.length - 1; j <= 0; j--){
                //Acá le resté uno porque ya estoy en la parada final
                elTren.parada(j);
            }
            elTren.volvimos();
        }
        System.out.println(Thread.currentThread().getName() + " dejó de trabajar");
        
    }

    /*
     for(int i = 0; i <= 2; i++){
        el tren llega a la estación i
        avisa a los pasajeros
        sleep()
        vuelve a arrancar
     
     }
     */
}
