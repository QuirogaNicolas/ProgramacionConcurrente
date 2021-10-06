package PracticaParciales.Aeropuerto;

public class TorreControl {
    public static void main(String[] args){
        PistaSemaforo pista= new PistaSemaforo();
        Avion avion1= new Avion(pista);
        Avion avion2= new Avion(pista);
        Avion avion3= new Avion(pista);
        Avion avion4= new Avion(pista);
        Avion avion5= new Avion(pista);
        Thread elAvion1= new Thread(avion1, "avion 1");
        Thread elAvion2= new Thread(avion2, "avion 2");
        Thread elAvion3= new Thread(avion3, "avion 3");
        Thread elAvion4= new Thread(avion4, "avion 4");
        Thread elAvion5= new Thread(avion5, "avion 5");

        elAvion1.start();
        elAvion2.start();
        elAvion3.start();
        elAvion4.start();
        elAvion5.start();
    }
}
