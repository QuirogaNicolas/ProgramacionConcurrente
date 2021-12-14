package Recuperatorio;

public class Socio implements Runnable {
    // Los hilos socios tendran la plataforma donde ver la serie
    private Plataforma laPlataforma;
    // Su idioma preferido
    private int eleccion;
    // Y sabe la cantidad de capitulos que tendra la serie
    private int capitulosDeLaSerie;

    public Socio(Plataforma laPlataforma, int capitulos) {
        this.laPlataforma = laPlataforma;
        this.capitulosDeLaSerie = capitulos;
        this.eleccion = (int) Math.floor(Math.random() * 2);
    }

    public void run() {
        for (int j = 0; j < 3; j++) {
            // El usuario verá la serie j veces
            // Esto lo hago para que sea mas facil leer
            // Pero puede mirarla infinitamente con un while (true)
            // Aleatoriamente se elegira un idioma para ver la serie
            if (eleccion == 1) {
                // El socio elige ver la serie en ingles
                // Mostramos por pantalla el idioma que eligio para que sea mas facil de seguir
                System.out.println(Thread.currentThread().getName() + " verá la serie en ingles");
                for (int i = 0; i < capitulosDeLaSerie; i++) {
                    // Vemos la serie completa
                    // Pasa como parametro el capitulo que debe ver
                    laPlataforma.verSerieIngles(i);
                    // Simulamos como que el capitulo es visto
                    verCapitulo(i);
                }
            } else {
                // El socio elige ver la serie en espaniol
                System.out.println(Thread.currentThread().getName() + " verá la serie en español");
                for (int i = 0; i < capitulosDeLaSerie; i++) {
                    // Vemos la serie completa
                    laPlataforma.verSerieEspaniol(i);
                    // Simulamos como que el capitulo es visto
                    verCapitulo(i);
                }
            }
            System.out.println(Thread.currentThread().getName() + " Termino de ver la serie. La volvera a ver");
        }
    }

    public void verCapitulo(int capitulo) {
        // Este metodo simula que el capitulo es visto
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " termino de ver el capitulo " + capitulo);
    }
}
