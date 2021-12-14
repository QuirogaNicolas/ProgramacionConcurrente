package Recuperatorio;

public class Filmador implements Runnable {
    // El hilo Filmador tendra la plataforma donde publicar los capitulos
    private Plataforma laPlataforma;
    // Y tendra la cantidad de capitulos que debe producir
    private int metaCapitulos;

    public Filmador(Plataforma laPlat, int meta) {
        this.laPlataforma = laPlat;
        this.metaCapitulos = meta;
    }

    public void run() {
        // Iteraremos hasta haber producido todos los capitulos pactados
        for (int i = 0; i < metaCapitulos; i++) {
            // Producimos el capitulo
            producirCapitulo();
            // Lo publicamos en la plataforma
            laPlataforma.publicarCapitulo();
        }
    }

    public void producirCapitulo() {
        // Este metodo se encarga de simular la produccion del capitulo
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
