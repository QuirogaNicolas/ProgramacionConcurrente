package TpObligatorio.Ejercicio2;

public class Embotellador implements Runnable {
    private StockCajas lasCajas;

    public Embotellador(StockCajas lasCajas) {
        this.lasCajas = lasCajas;
    }

    public void run() {
        while (true) {
            lasCajas.llenar();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
