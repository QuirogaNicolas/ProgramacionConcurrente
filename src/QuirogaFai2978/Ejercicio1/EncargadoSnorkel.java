package QuirogaFai2978.Ejercicio1;

import java.util.concurrent.Semaphore;

public class EncargadoSnorkel {
    private Snorkel[] equipamiento;
    private Semaphore semConsulta;
    private Semaphore semGuardar;

    public EncargadoSnorkel(Snorkel[] equipamiento) {
        this.equipamiento = equipamiento;
        this.semConsulta = new Semaphore(1, true);
        this.semGuardar = new Semaphore(1, true);
    }

    public void solicitarEquipamiento() {
        boolean puedeEntregar = false;
        int i = 0;
        try {
            semConsulta.acquire();
            System.out.println(Thread.currentThread().getName()+" esta solicitando un snorkel");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!puedeEntregar) {
            // el encargado va a buscar un snorkel libre hasta encontrar uno
            if (i < equipamiento.length) {
                puedeEntregar = equipamiento[i].tomarSnorkel();
            } else {
                i = -1;
            }
            i++;
        }
        System.out.println(Thread.currentThread().getName()+" recibe un snorkel");
        semConsulta.release();
    }

    public void devolverEquipamiento() {
        try {
            semGuardar.acquire();
            System.out.println(Thread.currentThread().getName()+" esta devolviendo un snorkel");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i= 0;
        boolean sePudoGuardar= false;
        while(!sePudoGuardar){
            if(!equipamiento[i].tomarSnorkel()){
                equipamiento[i].devolverSnorkel();
                sePudoGuardar= true;
            }
            else{
                equipamiento[i].devolverSnorkel();
            }
        }
        System.out.println("el snorkel ha sido guardadas");
        semGuardar.release();
    }
}
