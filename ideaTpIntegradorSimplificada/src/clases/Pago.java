package clases;

import enums.TipoPago;

public class Pago {

    private static int contador = 1;
    private int idPago;
    private String nombre;
    private String apellido;
    private double monto;
    private Cuenta cuenta;
    private TipoPago tipoPago;
    private int descuento;
    private double total;


    public Pago(Cuenta cuenta, TipoPago tipoPago, int descuento,double total) {
        this.idPago = contador++;
        this.nombre = cuenta.getEstadia().getCliente().getNombre();
        this.apellido = cuenta.getEstadia().getCliente().getApellido();
        this.monto = cuenta.getTotal();
        this.cuenta = cuenta;
        this.tipoPago = tipoPago;
        this.descuento = descuento;
        this.total = total;
    }

    public Pago() {
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Pago.contador = contador;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return  "=== Pago ===\n" +
                "• ID de pago: " + idPago + "\n" +
                "• Cliente: " + nombre + " " + apellido + "\n" +
                "• Monto original: " + monto + "\n" +
                "• Descuento aplicado: " + descuento + "%\n" +
                "• Monto final: " + total + "\n" +
                "• Tipo de pago: " + tipoPago + "\n" +
                "• Cuenta asociada: " + cuenta.getId() + "\n";
    }

    public static void actualizarContador(int ultimoId) {
        if (ultimoId >= contador) {
            contador = ultimoId + 1;
        }
    }
}
