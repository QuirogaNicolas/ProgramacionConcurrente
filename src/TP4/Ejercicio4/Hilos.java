package TP4.Ejercicio4;

public class Hilos implements Runnable{
    private String nombre;
    private int cantVeces;
    private Procesos losProcesos;
    public Hilos(String nombre, int cantVeces, Procesos losProcesos){
        this.nombre= nombre;
        this.cantVeces= cantVeces;
        this.losProcesos= losProcesos; 
    }
    public void run(){
        for(int i=0; i<=3; i++){
            switch(nombre){
                case "a": 
                    losProcesos.escribirA(cantVeces);
                break;
                case "b": 
                    losProcesos.escribirB(cantVeces);
                break;
                case "c":
                    losProcesos.escribirC(cantVeces); 
                break;
            }
        }
    }
}
