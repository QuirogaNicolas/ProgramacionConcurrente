package Recuperatorio;

import parcial2_Quiroga_Fai_2978.Ejercicio1.TecladoIn;

public class Tester {
    public static void main(String[] args) {
        System.out.println("Ingrese la cantidad de capitulos que tendra la serie");
        int cantCapitulos = TecladoIn.readInt();
        Plataforma laPlataforma = new Plataforma(cantCapitulos);
        Thread laFilmadora = new Thread(new Filmador(laPlataforma, cantCapitulos), "la Filmadora");
        Thread Traductor1 = new Thread(new Traductor(laPlataforma), "traductor 1");
        Thread Traductor2 = new Thread(new Traductor(laPlataforma), "traductor 2");
        System.out.println("Ingrese la cantidad de socios que habrá");
        int cantSocios = TecladoIn.readInt();
        Thread socios[] = new Thread[cantSocios];
        for (int i = 0; i < cantSocios; i++) {
            socios[i] = new Thread(new Socio(laPlataforma, cantCapitulos), "socio " + i);
        }
        for (int i = 0; i < cantSocios; i++) {
            socios[i].start();
        }
        laFilmadora.start();
        Traductor1.start();
        Traductor2.start();
    }
}
