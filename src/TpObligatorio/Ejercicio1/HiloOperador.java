package TpObligatorio.Ejercicio1;

public class HiloOperador implements Runnable{
    private int x;
    private int y;
    private char operacion;
    private int resultado= 0;
    public HiloOperador(int x, int y, char op){
        this.x= x;
        this.y= y;
    }
    public void run(){
        if(operacion== '+'){
            sumar();
        }
        else{
            restar();
        }
    }
    public void sumar(){
        this.resultado= x + y;
    }
    public void restar(){
        this.resultado= x - y;
    }
    public int getResultado(){
        return resultado;
    }
}
