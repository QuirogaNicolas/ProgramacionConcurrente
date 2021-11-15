package TpObligatorio3.Ejercicio2;

import java.util.concurrent.Semaphore;

public class Libro {
    private Semaphore semEscritor;
    private Semaphore semLector;
    private int cantLectores;
    private int cantEscritores;
    private int cantHojasEscritas;
    private int cantHojasTotales;

    public Libro(int cantHojasTotales) {
        this.semLector = new Semaphore(1);
        this.semEscritor = new Semaphore(1);
        this.cantEscritores = 0;
        this.cantLectores = 0;
        this.cantHojasTotales = cantHojasTotales;
        this.cantHojasEscritas = 0;
    }

    public boolean empezarLectura(int hojasLeidas) {
        boolean yaLeyoTodo = false;
        if (hayEscrito(hojasLeidas)) {
            // el lector verifica que haya hojas nuevas para leer
            // obtiene el semaforo de lector para que no pueda acceder otro lector o
            // escritor
            try {
                semLector.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " esta leyendo");
            // sumamos un lector a la cantidad de lectores
            this.cantLectores++;
        } else {
            // si no hay hojas nuevas por leer verificamos si ya se termino de escribir el
            // libro
            if (hojasLeidas == cantHojasTotales) {
                System.out.println(Thread.currentThread().getName() + " dice: \"ya leí todo el libro \"");
                System.out.println(hojasLeidas+" - "+cantHojasTotales);
                yaLeyoTodo = true;
            }
        }
        return yaLeyoTodo;
    }

    public boolean empezarEscritura() {
        boolean seEscribioTodo = false;
        if (!finalizado()) {
            // si todavia no se escribieron la cantidad de hojas deseadas
            try {
                semLector.acquire();
                semEscritor.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " esta escribiendo");
            this.cantEscritores++;
        } else {
            System.out.println(Thread.currentThread().getName() + " dice: \"ya se escribio todo el libro\"");
            seEscribioTodo = true;
        }
        return seEscribioTodo;
    }

    public void finalizarLectura() {
        // simplemente terminamos de leer, por eso hacemos un release al semaforo de
        // lector
        this.cantLectores--;
        semLector.release();
    }

    public void finalizarEscritura() {
        // Este metodo solamente agrega una hoja a la cantidad de hojas del libro
        // (porque el escritor la acaba de redactar)
        this.cantHojasEscritas++;
        this.cantEscritores--;
        // indicamos que ha terminado de escribir
        System.out.println(Thread.currentThread().getName() + " terminó de escribir");
        // liberamos los semaforos para que pueda acceder un escritor o un lector
        semLector.release();
        semEscritor.release();
    }

    public boolean hayEscrito(int hojasLeidas) {
        return hojasLeidas < cantHojasEscritas;
    }

    public boolean finalizado() {
        return cantHojasEscritas >= cantHojasTotales;
    }
}
