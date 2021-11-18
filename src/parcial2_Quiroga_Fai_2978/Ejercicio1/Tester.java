package parcial2_Quiroga_Fai_2978.Ejercicio1;

public class Tester {
    public static void main(String[] args) {
        // preguntamos cuantos embotelladores de cada tipo habra
        System.out.println("Cuantos embotelladores de Jugo habra?");
        int jugo = TecladoIn.readInt();
        System.out.println("Cuantos embotelladores de vino habra?");
        int vino = TecladoIn.readInt();
        Thread[] embotelladores = new Thread[jugo + vino];
        // creamos el almacen
        Almacen elAlmacen = new Almacen();
        // creamos los embotelladores
        for (int i = 0; i < jugo; i++) {
            embotelladores[i] = new Thread(new Embotellador(elAlmacen, 1), "embotellador de jugo " + i);
        }

        for (int i = jugo; i < jugo + vino; i++) {
            embotelladores[i] = new Thread(new Embotellador(elAlmacen, 2), "embotellador de vino " + i);
        }
        // iniciamos los embotelladores
        for (int i = 0; i < jugo + vino; i++) {
            embotelladores[i].start();
        }
        // creamos e iniciamos el empaquetador y el camion
        Thread empaquetador = new Thread(new Empaquetador(elAlmacen), "empaquetador");
        Thread camion = new Thread(new Camion(elAlmacen), "el camion");
        empaquetador.start();
        camion.start();
    }
}
