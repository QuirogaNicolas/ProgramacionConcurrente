package TpObligatorio.Ejercicio1;

public class HiloOperador implements Runnable {
    private String accion;
    private Accionar porHacer;

    public HiloOperador(String accion, Accionar porHacer) {
        this.accion = accion;
        this.porHacer = porHacer;
    }

    public void run() {
        // Segun la accion que le asignemos al HiloOperador es lo que vamos a hacer
        switch (accion) {
        case "s1":
            porHacer.s1();
            break;
        case "s2":
            porHacer.s2();
            break;
        case "s3":
            porHacer.s3();
            break;
        case "s4":
            porHacer.s4();
            break;
        }
    }
}
