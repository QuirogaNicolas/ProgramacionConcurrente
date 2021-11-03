package TpObligatorio.Ejercicio2;

public class Empaquetador implements Runnable {
    private StockCajas lasCajas;

    public Empaquetador(StockCajas lasCajas) {
        this.lasCajas = lasCajas;
    }

    public void run() {
        while (true) {
            lasCajas.empaquetar();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lasCajas.reponerCaja();
        }
    }
}
