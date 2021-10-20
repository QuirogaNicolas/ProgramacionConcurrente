package TpObligatorio.Ejercicio2;

public class Main {
    public static void main(String[] args) {
        //vamos a tener siempre la misma cantidad de maquinas empaquetadoras y embotelladoras
        //por eso solamente creo dos hilos
        StockCajas stock = new StockCajas();
        Thread hiloEmpaquetador = new Thread(new Empaquetador(stock));
        Thread hiloEmbotellador = new Thread(new Embotellador(stock));
        hiloEmbotellador.start();
        hiloEmpaquetador.start();
    }
}
