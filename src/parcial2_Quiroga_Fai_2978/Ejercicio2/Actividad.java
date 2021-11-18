package parcial2_Quiroga_Fai_2978.Ejercicio2;

public class Actividad implements Runnable {
    private Gimnasio gym;
    private int actividad;

    public Actividad(Gimnasio gym, int actividad) {
        this.gym = gym;
        this.actividad = actividad;
    }

    public void run() {
        if (actividad == 1) {
            // Acro
            while (true) {
                gym.comenzarAcro();
                darClase();
            }
        } else {
            if (actividad == 2) {
                // Lyra
                while (true) {
                    gym.comenzarLyra();
                    darClase();
                }
            } else {
                // Yoga
                while (true) {
                    gym.comenzarYoga();
                    darClase();
                }
            }
        }
    }

    public void darClase() {
        // simulamos la duracion de la clase
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
