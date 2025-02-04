package TrabajoFinalAeropuerto;
//import java.util.concurrent.Exchanger;

public class Pasajero implements Runnable{
    //Atributos de los pasajeros
    private int idPasajero;
    private String aerolineaPasajero; //capaz aerolinea está de más
    private String vuelo;
    private String estadoPasajero;
    private int terminalAsignada;
    private PuestoInformes puestoInforme;
    private PuestoAtencion puestoAtencion;
    private Tren tren;
    private Exchanger<Object> exchanger;
    
    //Nuevo pasajero
    public Pasajero(int id, String aerolinea, String vuelo, Exchanger ex){
        this.idPasajero = id;
        this.aerolineaPasajero = aerolinea;
        this.vuelo = vuelo;
        this.exchanger = ex;
    }

    public void asignarTerminal(int numTerminal){
        this.terminalAsignada = numTerminal;
    }

    @Override
    public void run() {
        //Visita PI
        this.puestoAtencion = (puestoAtencion) exchanger.exchange(aerolineaPasajero); //El hilo puesto de informes le va a asignar un puesto de atención y aerolinea según el vuelo que tenga
        System.out.println("El pasajero "+this.idPasajero+" se le asignó el puesto de atención "+ this.puestoAtencion);
        
        //Visita PA
        puestoAtencion.hacerCheckIn(vuelo);; //El hilo puesto de atención le va a asignar una terminal 

        //Visita Tren
        tren.abordar();
        //se sube
        tren.bajar(); //El tren le debe avisar en qué terminal está y el pasajero comparar con la terminal que se le asigno

        //Visita FreeShop
        if((int)(Math.random() * 2) == 1){
            //Tiene ganas de entrar al freeshop

            
        } else{
            //No tiene ganas de entrar al freeshop y solamente espera
        }

        //Embarque
    }

}
