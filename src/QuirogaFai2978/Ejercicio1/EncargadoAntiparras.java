package QuirogaFai2978.Ejercicio1;

import java.util.concurrent.Semaphore;

public class EncargadoAntiparras{
    private Antiparra[] equipamiento;
    private Semaphore semConsulta;
    private Semaphore semGuardar;

    public EncargadoAntiparras(Antiparra[] equipamiento) {
        this.equipamiento = equipamiento;
        this.semConsulta = new Semaphore(1, true);
        this.semGuardar = new Semaphore(1, true);
    }

    public void solicitarEquipamiento() {
        boolean puedeEntregar = false;
        int i = 0;
        try {
            semConsulta.acquire();
            System.out.println(Thread.currentThread().getName()+" esta solicitando unas antiparras");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!puedeEntregar) {
            // el encargado va a buscar una antiparra libre hasta encontrar una
            if (i < equipamiento.length) {
                puedeEntregar = equipamiento[i].tomarAntiparra();
            } else {
                i = -1;
            }
            i++;
        }
        System.out.println(Thread.currentThread().getName()+" recibe las antiparras");
        semConsulta.release();
    }

    public void devolverEquipamiento() {
        try {
            semGuardar.acquire();
            System.out.println(Thread.currentThread().getName()+" esta devolviendo las antiparras");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i= 0;
        boolean sePudoGuardar= false;
        while(!sePudoGuardar){
            if(!equipamiento[i].tomarAntiparra()){
                equipamiento[i].devolverAntiparra();
                sePudoGuardar= true;
            }
            else{
                equipamiento[i].devolverAntiparra();
            }
        }
        System.out.println("las antiparras han sido guardadas");
        semGuardar.release();
    }
}
