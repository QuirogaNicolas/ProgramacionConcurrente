package QuirogaFai2978.Ejercicio1;

import java.util.Scanner;

public class Puesto {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de antiparras que habra");
        int i = teclado.nextInt();
        System.out.println("Ingrese la cantidad de snorkel que habra");
        int j = teclado.nextInt();
        Antiparra[] equipamientoA = new Antiparra[i];
        for (int contA = 0; contA < i; contA++) {
            equipamientoA[contA] = new Antiparra();
        }
        Snorkel[] equipamientoS = new Snorkel[j];
        for (int contS = 0; contS < j; contS++) {
            equipamientoS[contS] = new Snorkel();
        }
        EncargadoAntiparras elEncargadoA = new EncargadoAntiparras(equipamientoA);
        EncargadoSnorkel elEncargadoS = new EncargadoSnorkel(equipamientoS);
        System.out.println("ingrese cuantos buzos habra");
        int b = teclado.nextInt();
        Thread[] losBuzos = new Thread[b];
        String nombre;
        for (int contB = 0; contB < b; contB++) {
            System.out.println("ingrese el nombre del buzo");
            nombre = teclado.nextLine();
            losBuzos[contB] = new Thread(new Buzo(elEncargadoA, elEncargadoS), nombre);
        }
        for (int iniciar = 0; iniciar < b; iniciar++) {
            losBuzos[iniciar].start();
        }
        teclado.close();
    }
}
