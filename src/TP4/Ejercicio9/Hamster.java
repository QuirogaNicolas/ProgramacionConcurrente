package TP4.Ejercicio9;

public class Hamster implements Runnable{
    private String nombre;
    //satisfecho es la cantidad de acciones que debera realizar el hamster hasta estar satisfecho
    private int satisfecho;
    private Actividades lasActividades;
    //esto esta a prueba
    private int desicion; 
    public Hamster(String nombre){
        this.nombre= nombre;
    }
    public void run(){
        System.out.println(nombre+" intenta comer");
        lasActividades.comer();
        System.out.println(nombre+" ha comido");
        System.out.println(nombre+" intenta correr");
        lasActividades.correr();
        System.out.println(nombre+" ha usado la rueda");
        System.out.println(nombre+" intenta dormir");
        lasActividades.hamacarse();
        System.out.println(nombre+" ha dormido");
    }
    // desicion= (int)Math.random()*3+1;
            //switch(desicion){
              //  case 1: 
                    //intenta usar la hamaca
                //    break;
              // case 2: 
                    //intenta comer del plato 
              //      break; 
                //case 3: 
                    //intenta correr en la rueda
            //}
   
}
