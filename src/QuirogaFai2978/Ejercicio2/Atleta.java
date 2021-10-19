package QuirogaFai2978.Ejercicio2;

public class Atleta implements Runnable {
    private Testigo elTestigo;

    public Atleta(Testigo elTestigo) {
        this.elTestigo = elTestigo;
    }

    public void run() {
        elTestigo.tomarTestigo();
        System.out.println(Thread.currentThread().getName() + " comienza a correr");
        try {
            Thread.sleep((int) (Math.random() * (11 - 8) + 9) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " termino la carrera");
        elTestigo.soltarTestigo();
    }

}
