package TP4.Ejercicio9;

public class Jaula {
    public static void main(String[] args){
        Actividades lasActividades= new Actividades();
        Hamster Romeo= new Hamster("Romeo");
        Hamster Julieta= new Hamster("Julieta");
        Hamster Azafran= new Hamster("Azafran");
        Hamster Mayo= new Hamster("Mayo");
        
        Thread hamsterR= new Thread(Romeo);
        Thread hamsterJ= new Thread(Julieta);
        Thread hamsterA= new Thread(Azafran);
        Thread hamsterM= new Thread(Mayo);

        hamsterR.start();
        hamsterJ.start();
        hamsterA.start();
        hamsterM.start();
    }
}
