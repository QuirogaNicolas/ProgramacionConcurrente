package TpObligatorio3.Ejercicio2;

public class Lector implements Runnable {
    private int hojasLeidas;
    private Libro libroALeer;

    public Lector(Libro elLibro) {
        this.libroALeer = elLibro;
        this.hojasLeidas = 0;
    }

    public void leer() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        boolean termino = false;
        while (!termino) {
            // si no se ha terminado de leer el libro
            termino = libroALeer.empezarLectura(this.hojasLeidas);
            if (!termino) {
                // si todavia no se leyó todo el libro
                leer();
                libroALeer.finalizarLectura();
                this.hojasLeidas++;
            }
        }
    }
}
