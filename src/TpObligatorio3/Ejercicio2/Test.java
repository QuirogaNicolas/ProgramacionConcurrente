package TpObligatorio3.Ejercicio2;

import TpObligatorio.TecladoIn;

public class Test {
    public static void main(String[] args){
        System.out.println("ingrese la cantidad de escritores");
        int escritores= TecladoIn.readInt();
        System.out.println("ingrese la cantidad de lectores");
        int lectores= TecladoIn.readInt();
        System.out.println("ingrese la cantidad de hojas que tendrá el libro");
        int cantHojasTotales= TecladoIn.readInt();
        int suma= lectores+escritores;
        Libro elLibro= new Libro(cantHojasTotales);
        Thread[] hilos= new Thread[suma];
        for(int i=0; i< escritores; i++){
            hilos[i]= new Thread(new Escritor(elLibro), "escritor "+i);
        }
        for(int i= escritores; i< suma; i++){
            hilos[i]= new Thread(new Lector(elLibro), "lector "+i);
        }
        for(int i= 0; i< suma; i++){
            hilos[i].start();
        }
    }
}
