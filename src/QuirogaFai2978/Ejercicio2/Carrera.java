package QuirogaFai2978.Ejercicio2;

public class Carrera {
    public static void main(String[] args) {
        Testigo testigoEquipo1 = new Testigo();
        Testigo testigoEquipo2 = new Testigo();

        Thread Francesca = new Thread(new Atleta(testigoEquipo1), "Francesca");
        Thread Tomas = new Thread(new Atleta(testigoEquipo1), "Tomas");
        Thread Franco = new Thread(new Atleta(testigoEquipo2), "Franco");
        Thread Facundo = new Thread(new Atleta(testigoEquipo2), "Facundo");

        Francesca.start();
        Tomas.start();
        Franco.start();
        Facundo.start();
    }
}
