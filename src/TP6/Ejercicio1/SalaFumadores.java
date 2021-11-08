package TP6.Ejercicio1;

public class SalaFumadores {
    private int tabaco;
    private int papel;
    private int fosforo;

    public SalaFumadores() {
        //Es medio al pedo este constructor pero bueno...
        this.tabaco = 0;
        this.papel = 0;
        this.fosforo = 0;
    }

    public void colocar(int aColocar) {
        switch (aColocar) {
        case 1:
            this.tabaco = 0;
            this.papel = 1;
            this.fosforo = 1;
            break;
        case 2:
            this.tabaco = 1;
            this.papel = 0;
            this.fosforo = 1;
            break;
        case 3:
            this.tabaco = 1;
            this.papel = 1;
            this.fosforo = 0;
            break;
        }
    }

    public synchronized void entraFumar(int id) {
        switch (id) {
        // Segun la id del hilo es la condicion que vamos a chequear
        // Cada hilo tiene una reserva infinita de UNO de los tres ingredientes
        case 1:
            System.out.println(Thread.currentThread().getName()+ " intenta fumar");
            while (papel == 1 && fosforo == 1) {
                try {
                    System.out.println(Thread.currentThread().getName()+ " no lo logra");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            break;
        case 2:
            System.out.println(Thread.currentThread().getName()+ " intenta fumar");
            while (tabaco == 1 && fosforo == 1) {
                try {
                    System.out.println(Thread.currentThread().getName()+ " no lo logra");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            break;
        case 3:
            System.out.println(Thread.currentThread().getName()+ " intenta fumar");
            while (papel == 1 && tabaco == 1) {
                try {
                    System.out.println(Thread.currentThread().getName()+ " no lo logra");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            break;
        }
    }

    public synchronized void terminaFumar() {
        System.out.println(Thread.currentThread().getName()+" terminó de fumar");
        this.notifyAll();
    }
}
