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
    private Object[] informacion;
    private Tren tren;

    
    //Nuevo pasajero
    public Pasajero(int id, String aerolinea, String vuelo, PuestoInformes pi){
        this.idPasajero = id;
        this.aerolineaPasajero = aerolinea;
        this.vuelo = vuelo;
        this.puestoInforme = pi;
        this.informacion = new Object[2];
    }

    public void asignarTerminal(int numTerminal){
        this.terminalAsignada = numTerminal;
    }


    @Override
    public void run() {
        //Visita PI
        this.informacion = puestoInforme.asignarPuestoAtencion(vuelo);
        //El hilo puesto de informes le va a asignar un puesto de atención y aerolinea según el vuelo que tenga
        System.out.println("El pasajero "+ this.idPasajero+" se le asignó el puesto de atención");
        
        
        //Visita PA
        ((ColaPuestoAtencion) informacion[0]).consultarLugar();

        ((PuestoAtencion) informacion[1]).hacerCheckIn(vuelo);; //El hilo puesto de atención le va a asignar una terminal 
        
/*
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
        */
    }

}
