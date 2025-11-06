package clases;

public class Cuenta {

    private static int contador =1;
    private int id;
    private Estadia estadia;
    private double total;
    private boolean pago;

    public Cuenta(Estadia estadia, double total) {
        this.id = contador++;
        this.estadia = estadia;
        this.total = total;
        this.pago = false;
    }

    public Cuenta() {
    }

    public int getId() {
        return id;
    }

    public Estadia getEstadia() {
        return estadia;
    }

    public void setEstadia(Estadia estadia) {
        this.estadia = estadia;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "=== Cuenta ===\n" +
                "• ID: " + id + "\n" +
                "• Estadia: " + estadia + "\n" +
                "• Monto total de la cuenta: " + total + "\n" +
                "• Pago: " + pago + "\n";
    }
}
