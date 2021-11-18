package parcial2_Quiroga_Fai_2978.Ejercicio2;

import parcial2_Quiroga_Fai_2978.Ejercicio1.TecladoIn;

public class Tester {
    public static void main(String[] args) {
        System.out.println("Cuantos gimnastas habra?");
        int cant = TecladoIn.readInt();
        Thread[] gimnastas = new Thread[cant];
        // creamos el almacen
        Gimnasio elGimnasio = new Gimnasio();
        // creamos los embotelladores
        for (int i = 0; i < cant; i++) {
            gimnastas[i] = new Thread(new Gimnasta(), "gimnasta " + i);
        }

        // iniciamos los embotelladores
        for (int i = 0; i < cant; i++) {
            gimnastas[i].start();
        }
    }

}
