package Recuperatorio;

public class Tester {
    public static void main(String[] args) {
        System.out.println("Ingrese la cantidad de capitulos que tendra la serie");
        int cantCapitulos = 10;
        Plataforma laPlataforma = new Plataforma(cantCapitulos);
        Thread laFilmadora = new Thread(new Filmador(laPlataforma, cantCapitulos), "la Filmadora");
        Thread Traductor1 = new Thread(new Traductor(laPlataforma), "traductor 1");
        Thread Traductor2 = new Thread(new Traductor(laPlataforma), "traductor 2");
        Thread socios[] = new Thread[5];
        for (int i = 0; i < 5; i++) {
            socios[i] = new Thread(new Socio(laPlataforma, cantCapitulos), "socio " + i);
        }
        for (int i = 0; i < 5; i++) {
            socios[i].start();
        }
        laFilmadora.start();
        Traductor1.start();
        Traductor2.start();
    }
}
