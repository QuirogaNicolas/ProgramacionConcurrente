package TrabajoFinalAeropuerto;

public class PuestoAtencion {
    private String id;
    private String aerolinea;


    public PuestoAtencion(String idPuesto, String aerolinea){
        this.id = idPuesto;
        this.aerolinea = aerolinea;
    }

    public String getId(){
        return this.id;
    }

}
