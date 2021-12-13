package parcial2_Quiroga_Fai_2978.Reintento.Ejercicio1;

public class Caja {
    private int capacidad;
    private int cantidad;
    private int maduracion;

    public Caja(int capacidad) {
        this.capacidad = capacidad;
        this.cantidad = cantidad;
    }

    public void ponerBotella() {
        this.cantidad++;
    }

    public boolean hayEspacio() {
        return this.cantidad < this.capacidad;
    }
}
