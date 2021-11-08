package TP6.Ejercicio3;

import java.util.Random;

public class Buffer {
    private Object[] buffer;
    private int ocupados;
    private int espacio;

    public Buffer(int tamanio) {
        this.espacio = tamanio;
        this.buffer = new Object[tamanio];
        this.ocupados = 0;
    }

    public synchronized void ingresar(Object aImprimir) {
        boolean pudoIngresar = false;
        while (!pudoIngresar) {
            // nos fijamos si hay lugar en el buffer
            if (this.ocupados < this.espacio) {
                // si hay lugar
                int cont = 0;
                while (this.buffer[cont] != null) {
                    // buscamos el espacio
                    cont++;
                }
                // una vez encontrado agregamos el elemento a imprimir en el
                this.buffer[cont] = aImprimir;
                // indicamos que pudo ingresar el elemento
                pudoIngresar = true;
                this.ocupados++;
                System.out.println("usuario " + Thread.currentThread().getName() + " accede al buffer");
                // notificamos el cambio
                this.notifyAll();
            } else {
                // si no hay lugar
                try {
                    // le decimos al hilo del usuario que espere
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized Object obtener() {
        Object aImprimir = null;
        Random r = new Random();
        int posicion;
        boolean encontro = false;
        while (!encontro) {
            if (this.ocupados >= 1) {
                // si hay por lo menos un elemento
                // vamos a elegir uno de ellos al azar
                posicion = r.nextInt(ocupados);
                aImprimir = buffer[posicion];
                // lo eliminamos del buffer para dejar un nuevo lugar
                this.buffer[posicion] = null;
                this.ocupados--;
                // notificamos que se ha agregado un nuevo lugar
                this.notifyAll();
            } else {
                // si no hay elementos esperamos
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return aImprimir;
    }
}
