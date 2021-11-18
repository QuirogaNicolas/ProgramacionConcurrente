package parcial2_Quiroga_Fai_2978.Ejercicio1;

public class Embotellador implements Runnable {
    private Almacen elAlmacen;
    private int tipo;

    public Embotellador(Almacen elAlmacen, int tipo) {
        this.elAlmacen = elAlmacen;
        this.tipo = tipo;
    }

    public void run() {
        // independientemente de cual sea el tipo de embotellador, solo dejaremos que
        // cada uno haga 20 botellas porque sino se sobrecalientan
        if (this.tipo == 1) {
            // es embotellador de jugo
            for (int i = 0; i < 20; i++) {
                this.elAlmacen.llenarCajaJugo();
                llenado();
            }
        } else {
            // es embotellador de vino
            for (int i = 0; i < 20; i++) {
                this.elAlmacen.llenarCajaVino();
                llenado();
            }
        }
    }

    public void llenado() {
        // simulamos el llenado de una botella
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
