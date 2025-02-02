package TrabajoFinalAeropuerto;

public class Pasajero implements Runnable{
    //Atributos de los pasajeros
    private int idPasajero;
    private String aerolineaPasajero;
    private String vuelo;
    private String estadoPasajero;
    private int terminalAsignada;
    private int puestoEmbarque;
    private int puestoAtencion;
    
    //Nuevo pasajero
    public Pasajero(int id, String aerolinea, String vuelo){
        this.idPasajero = id;
        this.aerolineaPasajero = aerolinea;
        this.vuelo = vuelo;
    }


    @Override
    public void run() {
        //Visita PI
        

        //Visita PA

        //Visita Tren

        //Visita FreeShop

        //Embarque
    }

}
