package Recuperatorio;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Plataforma {
    // locks y condiciones para regular la concurrencia
    private ReentrantLock lockVerSerie;
    private Condition serieEspaniol;
    private Condition serieIngles;
    private ReentrantLock lockTraducir;
    private Condition hayParaTraducir;
    // variables
    private int cantCapitulos;
    private int cantPublicados;
    private int cantTraducidos;
    // estructuras donde guardaremos los capitulos
    private String[] capitulosIngles;
    private String[] capitulosEspaniol;
    private String[] capitulosParaTraducir;

    public Plataforma(int cantCapitulos) {
        // la empresa fimadora le comunica a la plataforma la cantidad de episodios que
        // tendra la serie
        this.cantCapitulos = cantCapitulos;
        this.cantPublicados = 0;
        this.cantTraducidos = 0;
        this.lockVerSerie = new ReentrantLock();
        this.serieEspaniol = lockVerSerie.newCondition();
        this.serieIngles = lockVerSerie.newCondition();
        this.lockTraducir = new ReentrantLock();
        this.hayParaTraducir = lockTraducir.newCondition();
        this.capitulosEspaniol = new String[this.cantCapitulos];
        this.capitulosIngles = new String[this.cantCapitulos];
        this.capitulosParaTraducir = new String[this.cantCapitulos];
    }

    public void publicarCapitulo() {
        // la empresa productora finaliza la fimacion del capitulo y lo publica en la
        // plataforma
        lockVerSerie.lock();
        lockTraducir.lock();
        System.out.println("+++ " + Thread.currentThread().getName() + " publica un nuevo capitulo");
        this.capitulosEspaniol[cantPublicados] = "Capitulo " + cantPublicados + 1;
        this.capitulosParaTraducir[cantPublicados] = "Capitulo " + cantPublicados + 1;
        /*
         * No recuerdo si el signal se hace antes de hacer el unlock
         * 
         * o si se hace despues del unlock
         */
        System.out.println(
                Thread.currentThread().getName() + " avisa a los socios que ven en espaniol y a los traductores");
        serieEspaniol.signalAll();
        hayParaTraducir.signalAll();
        lockVerSerie.unlock();
        lockTraducir.unlock();
    }

    public void tomarParaTraducir() {
        // los hilos traductores van a traducir los capitulos en espaniol
        lockTraducir.lock();
        System.out.println(Thread.currentThread().getName() + " quiere traducir");
        while (cantPublicados == cantTraducidos) {
            System.out.println(Thread.currentThread().getName() + " dice: no hay nada para traducir");
            // si todos los capitulos publicados fueron traducidos entonces esperamos
            try {
                hayParaTraducir.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " quiere traducir");
        }
        /*
         * Deberia sumar uno a la variable
         * cantTraducidos acá o la hora de publicar el capitulo?
         * Porque no se pierde el lock hasta hacer el unlock
         */
        System.out.println(Thread.currentThread().getName() + " toma un capitulo y lo va a traducir");
    }

    public void publicarCapituloTraducido() {
        /*
         * Tengo que recordar que tal vez los traductores traducen
         * y no los publican en el lugar que deben...
         * tal vez publican primero el capitulo 2 y despues el 1.
         * Esto esta mal, tengo que ver si pasa, y si pasa arreglarlo
         */
        System.out.println(Thread.currentThread().getName() + " publica el capitulo traducido");
        this.cantTraducidos++;
        capitulosIngles[cantTraducidos - 1] = "Capitulo " + cantTraducidos;
        lockVerSerie.lock();
        // les avisamos a los socios que ven en ingles que el capitulo ya esta publicado
        System.out.println(Thread.currentThread().getName() + " avisa a los socios que ven la serie en ingles");
        serieIngles.signalAll();
        // liberamos los locks
        lockVerSerie.unlock();
        lockTraducir.unlock();
    }

    public void verSerieIngles(int capituloPorVer) {
        lockVerSerie.lock();
        System.out.println(Thread.currentThread().getName() + " quiere ver la serie");
        while (capitulosIngles[capituloPorVer] == null) {
            System.out.println(Thread.currentThread().getName() + " dice: no hay nada para ver");
            try {
                serieIngles.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " quiere ver la serie");
        }
        /*
         * Deberia dormir en la estructura del socio
         * lo voy a agregar despues
         */
        System.out.println("--- " + Thread.currentThread().getName());
        lockVerSerie.unlock();
    }

    public void verSerieEspaniol(int capituloPorVer) {
        lockVerSerie.lock();
        System.out.println(Thread.currentThread().getName() + " quiere ver la serie");
        while (capitulosEspaniol[capituloPorVer] == null) {
            System.out.println(Thread.currentThread().getName() + " dice: no hay nada para ver");
            try {
                serieEspaniol.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " quiere ver la serie");
        }
        /*
         * Deberia dormir en la estructura del socio
         * lo voy a agregar despues
         */
        System.out.println("--- " + Thread.currentThread().getName());
        lockVerSerie.unlock();
    }
}
