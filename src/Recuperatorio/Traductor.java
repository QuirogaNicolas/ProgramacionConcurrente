package Recuperatorio;

public class Traductor implements Runnable {
    // Los hilos traductores tendran la plataforma donde accederan para trabajar
    private Plataforma laPlataforma;

    public Traductor(Plataforma laPlataforma) {
        this.laPlataforma = laPlataforma;
    }

    public void run() {
        // Se tendra una variable "terminamos" que devolvera el lugar del arreglo donde
        // el traductor debe poner el capitulo traducido
        // O devolvera -1 cuando todos los capitulos hayan sido traducidos
        int terminamos = 0;
        while (terminamos != -1) {
            // mientras no hayan sido traducido todos los capitulos iteraremos
            terminamos = laPlataforma.tomarParaTraducir();
            if (terminamos != -1) {
                // Si pudimos tomar un capitulo sin traducir
                // Lo traduciremos
                traducirCapitulo();
                // Y lo publicaremos en su lugar correspondiente
                laPlataforma.publicarCapituloTraducido(terminamos);
            }
        }
        // Cuando todos los capitulos fueron traducidos indicaremos por pantalla que
        // terminamos de trabajar
        System.out.println(Thread.currentThread().getName() + " ha terminado de traducir todos los capitulos");
    }

    public void traducirCapitulo() {
        // Este metodo simula la traduccion del capitulo
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
