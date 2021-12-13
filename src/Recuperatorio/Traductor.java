package Recuperatorio;

public class Traductor implements Runnable {
    private Plataforma laPlataforma;

    public Traductor(Plataforma laPlataforma) {
        this.laPlataforma = laPlataforma;
    }

    public void run() {
        while (true) {
            laPlataforma.tomarParaTraducir();
            traducirCapitulo();
            laPlataforma.publicarCapituloTraducido();
        }
    }

    public void traducirCapitulo() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
