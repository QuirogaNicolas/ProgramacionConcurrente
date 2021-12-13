package TrabajoConAdicionales.TrenConLatch;

import parcial2_Quiroga_Fai_2978.Ejercicio1.TecladoIn;

public class Main {
    public static void main(String[] args) {
        System.out.println("ingrese la capacidad de pasajeros que tendra el tren");
        int capacidad = TecladoIn.readInt();
        System.out.println("ingrese la cantidad de pasajeros que habra en total");
        int cantidad = TecladoIn.readInt();
        
        Thread vendedor = new Thread(new VendedorTickets(laEstacion, capacidad), "el vendedor");
        Thread control = new Thread(new ControlTren(laEstacion), "el control");
        Thread elTren = new Thread(new Tren(laEstacion, capacidad), "el tren");
        Estacion laEstacion = new Estacion(cantidad, elTren, control, vendedor);
        Thread pasajeros[] = new Thread[cantidad];
        for (int i = 0; i < cantidad; i++) {
            pasajeros[i] = new Thread(new Pasajero(laEstacion), "pasajero " + i);
        }
        for (int i = 0; i < cantidad; i++) {
            pasajeros[i].start();
        }
        vendedor.start();
        control.start();
        elTren.start();
    }
}
