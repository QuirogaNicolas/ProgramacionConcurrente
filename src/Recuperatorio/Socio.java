package Recuperatorio;

public class Socio implements Runnable {
    private Plataforma laPlataforma;
    private int capitulosVistos;
    private int eleccion;

    public Socio(Plataforma laPlataforma) {
        this.laPlataforma = laPlataforma;
        this.capitulosVistos = 0;
        this.eleccion = (int) Math.random() * 2;
    }

    public void run() {
        if (eleccion == 1) {
            // El socio elige ver la serie en ingles
            while (true) {
                laPlataforma.verSerieIngles(capitulosVistos);
                /*
                 * Tendria que mandarlo a dormir aca
                 * simulando que estoy viendo el capitulo pero bueno
                 * tal vez mas adelante
                 */
                capitulosVistos++;
            }
        } else {
            // El socio elige ver la serie en espaniol
            while (true) {
                laPlataforma.verSerieEspaniol(capitulosVistos);
                /*
                 * Tendria que mandarlo a dormir aca
                 * simulando que estoy viendo el capitulo pero bueno
                 * tal vez mas adelante
                 */
                capitulosVistos++;
            }
        }

    }
}
