package TP4.Ejercicio4;
public class Main {
    public static void main(String[] args){
            Procesos losProcesos= new Procesos();
            Hilos runA= new Hilos("a", 1, losProcesos);
            Hilos runB= new Hilos("b", 2, losProcesos);
            Hilos runC= new Hilos("c", 3, losProcesos);
            
            Thread hiloA= new Thread(runA);
            Thread hiloB= new Thread(runB);
            Thread hiloC= new Thread(runC);
            
            hiloA.start();
            hiloB.start();
            hiloC.start();  
    }

}
