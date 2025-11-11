package clases;

import interfaces.I_IdInicializable;

import java.util.Objects;

public class Cuenta implements I_IdInicializable {

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

    public void setId(int id) {
        this.id = id;
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

    public static void actualizarContador(int ultimoId) {
        if (ultimoId >= contador) {
            contador = ultimoId + 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cuenta cuenta)) return false;
        return id == cuenta.id && Double.compare(total, cuenta.total) == 0 && pago == cuenta.pago && Objects.equals(estadia, cuenta.estadia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estadia, total, pago);
    }
}
