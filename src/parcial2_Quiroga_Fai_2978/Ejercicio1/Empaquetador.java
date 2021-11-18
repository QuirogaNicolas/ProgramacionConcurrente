package parcial2_Quiroga_Fai_2978.Ejercicio1;

public class Empaquetador implements Runnable {
    private Almacen elAlmacen;

    public Empaquetador(Almacen elAlmacen) {
        this.elAlmacen = elAlmacen;
    }

    public void run() {
        while (true) {
            elAlmacen.empaquetarJugo();
            empaquetar();
            elAlmacen.empaquetarVino();
            empaquetar();
        }
    }

    public void empaquetar() {
        // simulamos el tiempo de empaquetado
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
