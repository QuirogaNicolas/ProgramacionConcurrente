package parcial2_Quiroga_Fai_2978.Ejercicio2;

import java.util.concurrent.Semaphore;

public class Gimnasio {
    //variables que nos ayudaran a contabilizar la cantidad de personas dentro de la clase
    private int cantAcro;
    private int cantLyra;
    private int cantYoga;
    //semaforos que nos permitiran indicar cuando deben empezar las clases
    private Semaphore comenzarClaseAcro;
    private Semaphore comenzarClaseLyra;
    private Semaphore comenzarClaseYoga;
    //semaforos que nos permitiran organizar a las personas para preguntar si hay lugar en la clase
    private Semaphore hayLugarAcro;
    private Semaphore hayLugarLyra;
    private Semaphore hayLugarYoga;
    public Gimnasio(){
        this.cantAcro=0;
        this.cantLyra=0;
        this.cantYoga=0; 
        this.comenzarClaseAcro= new Semaphore(0);
        this.comenzarClaseLyra= new Semaphore(0);
        this.comenzarClaseYoga= new Semaphore(0);
        this.hayLugarAcro= new Semaphore(1);
        this.hayLugarLyra= new Semaphore(1);
        this.hayLugarLyra= new Semaphore(1);
    }
    //ME QUEDE SIN TIEMPO Y NO LO PUDE CONTINUAR, SIN EMBARGO ESTOY SEGURO QUE SI TENIA MEDIA HORA MAS LO HACIA FUNCIONAR :'D
    public boolean practicarAcro(){
        try {
            hayLugarAcro.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean hayCupos= false;
        if(cantAcro<4){

        }
        return hayCupos;
    }
    public boolean practicarLyra(){
        try {
            hayLugarAcro.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean hayCupos= false;
        return hayCupos;
    }
    public boolean practicarYoga(){
        boolean hayCupos= false;
        return hayCupos;
    }
}
