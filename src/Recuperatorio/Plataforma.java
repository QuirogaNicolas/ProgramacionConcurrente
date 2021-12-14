package Recuperatorio;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Plataforma {
    // Locks para regular la concurrencia
    private ReentrantLock lockVerSerieEspaniol;
    private ReentrantLock lockVerSerieIngles;
    private ReentrantLock lockTraducir;
    // Las concidiciones
    private Condition hayCapsEspaniol;
    private Condition hayCapsIngles;
    private Condition hayParaTraducir;
    // Variables
    // En "cantCapitulos" tendremos la cantidad de capitulos que se harán
    private int cantCapitulos;
    // En "cantPublicados" tendremos la cantidad de capitulos que han sido
    // publicados (Espaniol)
    private int cantPublicados;
    // En "cantTraducidos" tendremos la cantidad de capitulos traducidos (Ingles)
    private int cantTraducidos;
    // Estructuras donde guardaremos los capitulos
    private String[] capitulosIngles;
    private String[] capitulosEspaniol;
    private String[] capitulosParaTraducir;

    public Plataforma(int cantCapitulos) {
        // La empresa fimadora le comunica a la plataforma la cantidad de episodios que
        // Tendra la serie
        this.cantCapitulos = cantCapitulos;
        this.cantPublicados = 0;
        this.cantTraducidos = 0;
        this.lockVerSerieEspaniol = new ReentrantLock();
        this.lockVerSerieIngles = new ReentrantLock();
        this.hayCapsEspaniol = lockVerSerieEspaniol.newCondition();
        this.hayCapsIngles = lockVerSerieIngles.newCondition();
        this.lockTraducir = new ReentrantLock();
        this.hayParaTraducir = lockTraducir.newCondition();
        this.capitulosEspaniol = new String[this.cantCapitulos];
        this.capitulosIngles = new String[this.cantCapitulos];
        this.capitulosParaTraducir = new String[this.cantCapitulos];
    }

    public void publicarCapitulo() {
        // La empresa productora finaliza la fimacion del capitulo y lo publica en la
        // Plataforma
        System.out.println("+++ " + Thread.currentThread().getName() + " publica un nuevo capitulo +++");
        System.out.println(cantPublicados + 1);
        // Es agregado al arreglo de capitulos disponibles para ver en espaniol
        this.capitulosEspaniol[cantPublicados] = "Capitulo " + cantPublicados + 1;
        // Es agregado al arreglo de capitulos disponibles para traducir
        this.capitulosParaTraducir[cantPublicados] = "Capitulo " + cantPublicados + 1;
        // Avisamos a los usuarios y traductores que hay un capitulo nuevo disponible
        System.out.println(
                Thread.currentThread().getName() + " avisa a los socios que ven en espaniol y a los traductores");
        cantPublicados++;
        lockVerSerieEspaniol.lock();
        hayCapsEspaniol.signalAll();
        lockVerSerieEspaniol.unlock();
        lockTraducir.lock();
        hayParaTraducir.signalAll();
        lockTraducir.unlock();
    }

    public int tomarParaTraducir() {
        // Los hilos traductores van a traducir los capitulos en espaniol
        // Inicializamos la variable "terminamos" en -1
        int terminamos = -1;
        System.out.println(Thread.currentThread().getName() + " quiere traducir");
        lockTraducir.lock();
        if (cantCapitulos != cantTraducidos) {
            // Si aun no fueron traducidos todos los capitulos los traductores entran acá
            while (cantPublicados == cantTraducidos) {
                // Mientras todos los capitulos publicados hayan sido traducidos o
                // Esten siendo traducidos esperaremos a que el hilo filmador publique
                System.out.println(Thread.currentThread().getName() + " dice: no hay nada para traducir");
                try {
                    hayParaTraducir.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " quiere traducir");
            }
            // Una vez tomamos el capitulo
            // Lo indicamos por pantalla
            System.out.println(Thread.currentThread().getName() + " toma un capitulo y lo va a traducir");
            // Guardamos el numero de capitulo que traduciremos
            terminamos = cantTraducidos;
            // Indicamos que el capitulo va a ser traducido
            this.cantTraducidos++;
        }
        lockTraducir.unlock();
        return terminamos;
    }

    public void publicarCapituloTraducido(int posicionDelCapitulo) {
        // Cuando los traductores quieran publicar el capitulo traducido entraran acá
        System.out.println(Thread.currentThread().getName() + " quiere publicar capitulo traducido");
        lockTraducir.lock();
        System.out
                .println("/// " + Thread.currentThread().getName() + " publica el capitulo traducido en el lugar ///");
        // Agregan el capitulo en su lugar correspondiente
        capitulosIngles[posicionDelCapitulo] = "Capitulo " + (posicionDelCapitulo + 1);
        lockVerSerieIngles.lock();
        // Les avisamos a los socios que ven en ingles que el capitulo ya esta publicado
        System.out.println(Thread.currentThread().getName() + " avisa a los socios que ven la serie en ingles");
        hayCapsIngles.signalAll();
        lockVerSerieIngles.unlock();
        lockTraducir.unlock();
    }

    public void verSerieIngles(int capituloPorVer) {
        // Los socios que quieran ver la serie en ingles entraran acá
        System.out.println(Thread.currentThread().getName() + " quiere ver la serie");
        lockVerSerieIngles.lock();
        while (capitulosIngles[capituloPorVer] == null) {
            // Mientras no haya sido agregado un nuevo capitulo esperaran
            System.out.println(Thread.currentThread().getName() + " dice: no hay nada para ver");
            try {
                hayCapsIngles.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " quiere ver la serie");
        }
        // Indicamos que el socio ha seleccionado para ver el nuevo capitulo
        System.out.println(
                "--- " + Thread.currentThread().getName() + " selecciona el capitulo " + (capituloPorVer + 1) + " ---");
        lockVerSerieIngles.unlock();
    }

    public void verSerieEspaniol(int capituloPorVer) {
        // Los socios que quieran ver la serie en espaniol entraran acá
        System.out.println(Thread.currentThread().getName() + " quiere ver la serie");
        lockVerSerieEspaniol.lock();
        while (capitulosEspaniol[capituloPorVer] == null) {
            // Mientras no haya sido agregado un nuevo capitulo esperaran
            System.out.println(Thread.currentThread().getName() + " dice: no hay nada para ver");
            try {
                hayCapsEspaniol.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " quiere ver la serie");
        }
        // Indicamos que el socio ha seleccionado para ver el nuevo capitulo
        System.out.println(
                "--- " + Thread.currentThread().getName() + " selecciona el capitulo " + (capituloPorVer + 1) + " ---");
        lockVerSerieEspaniol.unlock();
    }
}
