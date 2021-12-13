package parcial2_Quiroga_Fai_2978.Reintento.Ejercicio1;

public class Embotellador implements Runnable{
    private Almacen elAlmacen;
    private int producto;
    public Embotellador(Almacen elAlmacen, int producto){
        this.elAlmacen= elAlmacen;
        this.producto= producto;
    }
    public void run(){
        if(this.producto== 1){
            //si produce jugo
            while(true){
                //se va a estar intentado embotellar todo el tiempo 
            } 
        } else{
            //si produce vino
            while(true){
                //se va a estar intentado embotellar todo el tiempo 

            } 
        }
        
    }
}
