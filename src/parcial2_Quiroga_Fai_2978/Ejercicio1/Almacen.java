package parcial2_Quiroga_Fai_2978.Ejercicio1;

import java.util.concurrent.Semaphore;

public class Almacen {
    // Estos semaforos son para coordinar el llenado de las cajas
    private Semaphore semVino;
    private Semaphore semJugo;
    // Estos semaforos son para coordinar el empaquetado de las cajas
    private Semaphore semEmpaquetadoVino;
    private Semaphore semEmpaquetadoJugo;
    // Estos semaforos son para coordinar el reparto
    private Semaphore semRepartoVino;
    private Semaphore semRepartoJugo;
    // variables para controlar la cantidad de botellas
    private int capacidad;
    private int cantVino;
    private int cantJugo;

    public Almacen() {
        this.semVino = new Semaphore(1);
        this.semJugo = new Semaphore(1);
        this.semEmpaquetadoJugo = new Semaphore(1);
        this.semEmpaquetadoVino = new Semaphore(1);
        this.semRepartoJugo = new Semaphore(0);
        this.semRepartoVino = new Semaphore(0);
        this.capacidad = 0;
        this.cantJugo = 0;
        this.cantVino = 0;
    }

    public void llenarCajaVino() {
        // los embotelladores encargados de llenar botellas de vino llamaran a este
        // metodo
        try {
            // solicitaran permiso al semaforo que coordina el llenado de botellas para
            // trabajar de a un embotellador y de manera coordinada
            semVino.acquire();
            if (semEmpaquetadoVino.availablePermits() == 1) {
                // si es el primero en llegar entonces tomara el permiso del semaforo del
                // empaquetador
                semEmpaquetadoVino.acquire();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.cantVino++;
        System.out.println(Thread.currentThread().getName() + " embotella un vino. Vinos: " + cantVino);
        if (this.cantVino == 10) {
            // si es el ultimo en irse entonces va a hacer un release de 10 permisos para
            // que el empaquetador pueda iniciar
            // ademas no liberara permisos para que se sigan llenando botellas
            semEmpaquetadoVino.release(10);
            // se vuelve a 0 la cantidad de botellas que se llevan en esta tanda
            this.cantVino = 0;
        } else {
            // si no es el ultimo, entonces va a hacer un release para que otro embotellador
            // pueda trabajar
            semVino.release();
        }
    }

    public void llenarCajaJugo() {
        // los embotelladores encargados de llenar botellas de jugo llamaran a este
        // metodo
        try {
            // solicitaran permiso al semaforo que coordina el llenado de botellas para
            // trabajar de a un embotellador y de manera coordinada
            semJugo.acquire();
            if (semEmpaquetadoJugo.availablePermits() == 1) {
                // si es el primero en llegar entonces tomara el permiso del semaforo del
                // empaquetador
                semEmpaquetadoJugo.acquire();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.cantJugo++;
        System.out.println(Thread.currentThread().getName() + " embotella un jugo. Jugos: " + cantJugo);
        if (this.cantJugo == 10) {
            // si es el ultimo en irse entonces va a hacer un release de 10 permisos para
            // que el empaquetador pueda iniciar
            // ademas no liberara permisos para que se sigan llenando botellas
            semEmpaquetadoJugo.release(10);
            // se vuelve a 0 la cantidad de botellas que se llevan en esta tanda
            this.cantJugo = 0;
        } else {
            // si no es el ultimo, entonces va a hacer un release para que otro embotellador
            // pueda trabajar
            semJugo.release();
        }
    }

    public void empaquetarVino() {
        // el empaquetador, cuando quiera empaquetar una caja de vino llamara a este
        // metodo
        try {
            // tomara 10 permisos
            semEmpaquetadoVino.acquire(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " esta empaquetando una caja de vino");
        // simulamos como que guardamos la caja en el almacen y actualizamos la cantidad
        // de litros que hay
        guardarCaja();
        if (this.capacidad == 100) {
            // si el almacen se lleno entonces vamos a avisarle al camion que puede salir a
            // repartir
            System.out.println(Thread.currentThread().getName() + " avisa al camion que puede iniciar el reparto");
            // liberando los permisos para que pueda hacer el reparto
            semRepartoJugo.release();
            semRepartoVino.release();
            // tambien volveremos a 0 la cantidad de litros que hay en el almacen
            this.capacidad = 0;
        }
        // finalmente dejaremos que los embotelladores sigan produciendo liberando los
        // permisos
        semVino.release();
        semEmpaquetadoVino.release();
    }

    public void empaquetarJugo() {
        // el empaquetador, cuando quiera empaquetar una caja de jugo llamara a este
        // metodo
        try {
            // tomara 10 permisos
            semEmpaquetadoJugo.acquire(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " esta empaquetando una caja de jugo");
        // simulamos como que guardamos la caja en el almacen y actualizamos la cantidad
        // de litros que hay
        guardarCaja();
        if (this.capacidad == 100) {
            // si el almacen se lleno entonces vamos a avisarle al camion que puede salir a
            // repartir
            System.out.println(Thread.currentThread().getName() + " avisa al camion que puede iniciar el reparto");
            // liberando los permisos para que pueda hacer el reparto
            semRepartoJugo.release();
            semRepartoVino.release();
            // tambien volveremos a 0 la cantidad de litros que hay en el almacen
            this.capacidad = 0;
        }
        // finalmente dejaremos que los embotelladores sigan produciendo liberando los
        // permisos
        semJugo.release();
        semEmpaquetadoJugo.release();
    }

    public void guardarCaja() {
        this.capacidad += 10;
        System.out.println(Thread.currentThread().getName() + " guardo la caja en el almacen. Hay " + this.capacidad
                + " ltrs en total");
    }

    public void repartirJugo() {
        // el camion cuando quiera salir a repartir jugo entrara aca
        try {
            // solicitara el permiso
            semRepartoJugo.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // procedera
        System.out.println(Thread.currentThread().getName() + " sale a hacer el reparto de jugo");
    }

    public void repartirVino() {
        // el camion cuando quiera salir a repartir vino entrara aca
        try {
            // solicitara el permiso
            semRepartoVino.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // procedera
        System.out.println(Thread.currentThread().getName() + " sale a hacer el reparto de vino");
    }

    public void volverABase() {
        // cuando el camion termine y vuelva a la base ingresara a este metodo
        System.out.println(Thread.currentThread().getName() + " ha vuelto a la base y esta listo para salir");
    }
}
