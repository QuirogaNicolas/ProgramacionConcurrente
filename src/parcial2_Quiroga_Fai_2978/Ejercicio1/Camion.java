package parcial2_Quiroga_Fai_2978.Ejercicio1;

public class Camion implements Runnable {
    private Almacen elAlmacen;

    public Camion(Almacen elAlmacen) {
        this.elAlmacen = elAlmacen;
    }

    public void run() {
        while (true) {
            elAlmacen.repartirJugo();
            try {
                // simulamos el tiempo de reparto
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            elAlmacen.volverABase();
            // Esperamos a que madure el vino
            madurarElVino();
            elAlmacen.repartirVino();
            try {
                // simulamos el tiempo de reparto
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            elAlmacen.volverABase();
        }
    }

    public void madurarElVino() {
        // simulamos que esperamos y dejamos madurar el vino
        System.out.println(Thread.currentThread().getName() + " espera a que madure el vino");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
