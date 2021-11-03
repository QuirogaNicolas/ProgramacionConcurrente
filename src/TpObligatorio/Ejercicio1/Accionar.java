package TpObligatorio.Ejercicio1;

import java.util.concurrent.Semaphore;

public class Accionar {
    private int a, b, c, w, x, y, z;
    private Semaphore semaforo1;
    private Semaphore semaforo2;
    private Semaphore semaforo3;

    public Accionar(int x, int y, int z) {
        this.semaforo1 = new Semaphore(2);
        this.semaforo2 = new Semaphore(0);
        this.semaforo3 = new Semaphore(0);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void s1() {
        System.out.println(Thread.currentThread().getName() + " comienza a trabajar");
        this.a = x + y;
        System.out.println(Thread.currentThread().getName() + " termino. A = "+this.a);
        semaforo2.release();
    }

    public void s2() {
        System.out.println(Thread.currentThread().getName() + " comienza a trabajar");
        this.b = z - 1;
        System.out.println(Thread.currentThread().getName() + " termino. B = "+ this.b);
        semaforo2.release();
    }

    public void s3() {
        try {
            semaforo2.acquire(2);
            System.out.println(Thread.currentThread().getName() + " comienza a trabajar");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.c = a - b;
        System.out.println(Thread.currentThread().getName() + " termino. C = "+this.c);
        semaforo3.release();
    }

    public void s4() {
        try {
            semaforo3.acquire();
            System.out.println(Thread.currentThread().getName() + " comienza a trabajar");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.w = c + 1;
        System.out.println("El resultado final de w es: " + this.w);
    }
}
