package TrabajoFinalAeropuerto;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Avion {
    private ReentrantLock subir; 
    private Condition podemos;
    private boolean abordajePermitido;
    private boolean seFue;

    public Avion(){
        subir = new ReentrantLock();
        podemos = subir.newCondition();
        abordajePermitido = false;
        seFue = false;
    }

    public void despegue(){
        //El avión se va y ya no se puede abordar
        subir.lock();
        abordajePermitido = false;
        seFue = true;
        podemos.signalAll();
        subir.unlock();
    }

    public boolean esperarEmbarque(){
        //Los pasajeros van a estar esperando la señal para embarcar siempre y cuando esté permitido el abordaje
        subir.lock();
        if(!abordajePermitido && !seFue){
            try {
                podemos.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        subir.unlock();;
        return !seFue;
    }

    public void iniciarEmbarque(){
        //El empleado de embarque va a dar la señal para iniciar el embarque
        subir.lock();
        abordajePermitido = true;
        podemos.signalAll();
        subir.unlock();
    }
}
