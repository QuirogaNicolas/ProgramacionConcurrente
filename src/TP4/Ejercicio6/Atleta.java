package TP4.Ejercicio6;

public class Atleta implements Runnable{
    private String nombre;
    private Testigo elTestigo;
    public Atleta(String nombre, Testigo elTestigo){
        this.nombre= nombre;
        this.elTestigo= elTestigo;
    }
    public void run(){
        elTestigo.tomarTestigo();
        System.out.println(this.nombre+" comienza a correr");
        try {
            Thread.sleep((int)(Math.random()*(11-8)+9)*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.nombre+ " termino la carrera");
        elTestigo.soltarTestigo();
    }

}