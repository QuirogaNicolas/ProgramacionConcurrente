package QuirogaFai2978.Ejercicio1;

public class Buzo implements Runnable {
    private EncargadoAntiparras encargadoAntiparras;
    private EncargadoSnorkel encargadoSnorkel;

    public Buzo(EncargadoAntiparras encargadoAntiparras, EncargadoSnorkel encargadoSnorkel) {
        this.encargadoAntiparras = encargadoAntiparras;
        this.encargadoSnorkel = encargadoSnorkel;
    }

    public void run() {
        int queDesea = (int) Math.random() * (3 - 1) + 1;
        if (queDesea == 1) {
            // vamos a definir aleatoriamente que tipo de equipamiento desea
            encargadoAntiparras.solicitarEquipamiento();
            if (!utilizar()) {
                // si el buzo ha utilizado las antiparras y no las ha roto
                // entonces las devolveremos
                encargadoAntiparras.devolverEquipamiento();
            }
            // si, en cambio, se rompieron no las devolveremos
        } else {
            encargadoSnorkel.solicitarEquipamiento();
            if (utilizar()) {
                // si el buzo ha utilizado el snorkel y no lo ha roto
                // entonces lo devolvemos
                encargadoSnorkel.devolverEquipamiento();
            }
            // si, en cambio, se rompieron no lo devolveremos
        }
    }

    public boolean utilizar() {
        // Este metodo simula que el equipamiento fue utilizado
        // habiendo posibilidades de romper el objeto (antiparras o snorkel)
        boolean laHaRoto = false;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int posibilidades = (int) Math.random() * (4 - 1) + 1;
        // hay una entre 3 chances de que el objeto utilizado se rompa
        if (posibilidades == 1) {
            laHaRoto = true;
        }
        return laHaRoto;
    }
}
