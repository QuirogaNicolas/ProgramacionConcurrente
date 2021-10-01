package TP4.Ejercicio9;

public class Hamster implements Runnable{
    private Actividades lasActividades;
    public Hamster(Actividades lasActividades){
        this.lasActividades= lasActividades;
    }
    public void run(){
        lasActividades.comer();
        lasActividades.correr();
        lasActividades.hamacarse();
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
