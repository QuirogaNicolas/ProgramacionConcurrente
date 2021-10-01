package TP4.Ejercicio9;

public class Jaula {
    public static void main(String[] args){
        Actividades lasActividades= new Actividades();
        Hamster Romeo= new Hamster(lasActividades);
        Hamster Julieta= new Hamster(lasActividades);
        Hamster Azafran= new Hamster(lasActividades);
        Hamster Mayo= new Hamster(lasActividades);
        
        Thread hamsterR= new Thread(Romeo, "Romeo");
        Thread hamsterJ= new Thread(Julieta, "Julieta");
        Thread hamsterA= new Thread(Azafran, "Azafran");
        Thread hamsterM= new Thread(Mayo, "Mayo");

        hamsterR.start();
        hamsterJ.start();
        hamsterA.start();
        hamsterM.start();
    }
}
