package TpObligatorio.Ejercicio1;

public class Main {
    public static void main(String[] args) {
        Accionar accionar = new Accionar(1, 2, 3);
        Thread hilo1 = new Thread(new HiloOperador("s1", accionar), "s1");
        Thread hilo2 = new Thread(new HiloOperador("s2", accionar), "s2");
        Thread hilo3 = new Thread(new HiloOperador("s3", accionar), "s3");
        Thread hilo4 = new Thread(new HiloOperador("s4", accionar), "s4");
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
    }
}
