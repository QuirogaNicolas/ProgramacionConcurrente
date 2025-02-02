package TrabajoFinalAeropuerto;

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
    
    //Nuevo pasajero
    public Pasajero(int id, String aerolinea, String vuelo){
        this.idPasajero = id;
        this.aerolineaPasajero = aerolinea;
        this.vuelo = vuelo;
    }

    public void asignarPA(PuestoAtencion pa){
        this.puestoAtencion = pa;
    }

    public void asignarTerminal(int numTerminal){
        this.terminalAsignada = numTerminal;
    }

    @Override
    public void run() {
        //Visita PI
        puestoInforme.solicitarGuia(vuelo); //El hilo puesto de informes le va a asignar un puesto de atención y aerolinea según el vuelo que tenga

        //Visita PA
        puestoAtencion.hacerCheckIn(vuelo, aerolineaPasajero);; //El hilo puesto de atención le va a asignar una terminal 

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
