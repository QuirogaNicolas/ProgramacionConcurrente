package TP6.Ejercicio1;

public class DisparaSala {
    public static void main(String[] args) {
        SalaFumadores sala = new SalaFumadores();
        Fumador f1 = new Fumador(1, sala);
        Fumador f2 = new Fumador(2, sala);
        Fumador f3 = new Fumador(3, sala);
        Agente ag = new Agente(sala);
        Thread fumador1 = new Thread(f1);
        Thread fumador2 = new Thread(f2);
        Thread fumador3 = new Thread(f3);
        Thread agente = new Thread(ag);
        fumador1.start();
        fumador2.start();
        fumador3.start();
        agente.start();
    }// main
}
