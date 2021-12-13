package Recuperatorio;

public class Filmador implements Runnable {
    private Plataforma laPlataforma;
    private int metaCapitulos;

    public Filmador(Plataforma laPlat, int meta) {
        this.laPlataforma = laPlat;
        this.metaCapitulos = meta;
    }

    public void run() {
        for (int i = 0; i < metaCapitulos; i++) {
            producirCapitulo();
            laPlataforma.publicarCapitulo();
        }
    }

    public void producirCapitulo() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
