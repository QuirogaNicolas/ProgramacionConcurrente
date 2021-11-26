package TrabajoConAdicionales.TrenConLatch;

public class VendedorTickets implements Runnable {
    private Estacion laEstacion;
    private int cantTickets;

    public VendedorTickets(Estacion laEstacion, int cantTickets) {
        this.cantTickets = cantTickets;
        this.laEstacion = laEstacion;
    }

    public int ticketsRestantes() {
        return this.cantTickets;
    }

    public void venderTicket() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.cantTickets--;
    }

    public void run() {
        while (true) {
            laEstacion.vender();
        }
    }
}
