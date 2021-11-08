package TP6.Ejercicio3;

public class Grafica {
    public static void main(String[] args){
        Buffer bufferA= new Buffer(3);
        Buffer bufferB= new Buffer(3);
        Thread impresora1= new Thread(new Impresora(bufferA), "impresora 1");
        Thread impresora2= new Thread(new Impresora(bufferB), "impresora 2");
        Thread[] usuarios=new Thread[10];
        for(int i= 0; i<3; i++){
            usuarios[i]= new Thread(new Usuario(i, bufferA), "usuario "+i);    
        }
        for(int j= 3; j<7; j++){
            usuarios[j]= new Thread(new Usuario(j, bufferB), "usuario "+j);    
        }
        for(int n= 0; n<5; n++){
            usuarios[n].start();    
        }
        impresora1.start();
        impresora2.start();
    }
}
