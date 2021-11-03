package TpObligatorio.Ejercicio2;

import java.util.concurrent.Semaphore;

public class StockCajas {
    //El objeto compartido va a ser un deposito de cajas 
    //Tenemos dos semaforos uno para llenar y otro para empaquetar
    //Tambien vamos a llevar cuenta de cuantas botellas vamos llenando en un lote 
    public Semaphore semaforo_llenar;
    public Semaphore semaforo_empaquetar;
    public int botellasActuales;

    public StockCajas() {
        //inicializamos el semaforo de llenar en 1 para que la maquina pueda empezar 
        this.semaforo_llenar = new Semaphore(1);
        this.semaforo_empaquetar = new Semaphore(0);
        this.botellasActuales = 0;
    }

    public void llenar() {
        //Se agrega de a una botella 
        try {
            semaforo_llenar.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Se ha agregado otra botella a la caja, van: " + this.botellasActuales);
        //Le damos un permiso al semaforo de empaquetar. Dicho permiso es correspondiente a la botella agregada
        semaforo_empaquetar.release();
        //Incrementamos la cantidad de botellas en la caja
        this.botellasActuales++;
        if (botellasActuales < 10) {
            //Si la caja no se lleno aun le vamos a dar permiso a la maquina para seguir llenando
            semaforo_llenar.release();
        }
    }

    public void empaquetar() {
        //La maquina siempre va a querer empaquetar pero hasta que no esten los dies permisos (botellas)
        //No podra actuar
        System.out.println("La maquina esta lista para empaquetar");
        try {
            //Intenta empaquetar y se queda esperando hasta tener los permisos necesarios
            semaforo_empaquetar.acquire(10);
            System.out.println("La maquina está empaquetando");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void reponerCaja(){
        //Volvemos a 0 la cantidad de botellas para empezar la proxima tanda
        this.botellasActuales = 0;
        //Imaginariamente se ha agregado una caja y, por lo tanto, devolvemos el permiso para proceder con el llenado
        semaforo_llenar.release();
        System.out.println("Se ha empaquetado correctamente");
    }
}
