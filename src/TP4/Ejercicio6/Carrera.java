package TP4.Ejercicio6;

public class Carrera {
    public static void main(String[] args) {
        Testigo testigoEquipo1= new Testigo();
        Testigo testigoEquipo2= new Testigo();

        Atleta atleta1= new Atleta("Francesca", testigoEquipo1);
        Atleta atleta2= new Atleta("Nicolas", testigoEquipo1);
        Atleta atleta3= new Atleta("Kimara", testigoEquipo2);
        Atleta atleta4= new Atleta("Alejo", testigoEquipo2);

        Thread Fran= new Thread(atleta1);
        Thread Nico= new Thread(atleta2);
        Thread Kimara= new Thread(atleta3);
        Thread Alejo= new Thread(atleta4);
        
        Fran.start();
        Nico.start();
        Kimara.start();
        Alejo.start();
    }
}
