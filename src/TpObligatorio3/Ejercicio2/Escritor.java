package TpObligatorio3.Ejercicio2;

public class Escritor implements Runnable {
    private Libro libroAEscribir;

    public Escritor(Libro elLibro) {
        this.libroAEscribir = elLibro;
    }

    public void escribir() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        boolean termino = false;
        while (!termino) {
            // si no se ha terminado de escribir el libro
            termino = libroAEscribir.empezarEscritura();
            if (!termino) {
                // si todavia no se escribio todo el libro
                escribir();
                libroAEscribir.finalizarEscritura();
            }
        }
    }
}
