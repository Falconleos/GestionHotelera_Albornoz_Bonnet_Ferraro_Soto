package clases;

import interfaces.I_IdInicializable;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Reserva implements I_IdInicializable {
    private static int contador = 1;
    private int idReserva;
    private LocalDate fechaIngreso;
    private LocalDate fechaEgreso;
    private int pax;
    private long cantidadNoches;
    private Habitacion habitacion;
    private double valor;
    private String nombreReferencia;
    private String apellidoReferencia;
    private String celularReferencia;
    private String detallesExtra;
    private String personalResponsable;

    public Reserva(LocalDate fechaIngreso, LocalDate fechaEgreso, int pax, long cantidadNoches, Habitacion habitacion, double valor, String nombreReferencia, String apellidoReferencia, String celularReferencia, String detallesExtra, String personalResponsable) {
        this.idReserva = contador++;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.pax = pax;
        this.cantidadNoches = ChronoUnit.DAYS.between(fechaIngreso,fechaEgreso);
        this.habitacion = habitacion;
        this.valor = habitacion.getPrecio()*cantidadNoches;
        this.nombreReferencia = nombreReferencia;
        this.apellidoReferencia = apellidoReferencia;
        this.celularReferencia = celularReferencia;
        this.detallesExtra = detallesExtra != null && !detallesExtra.isBlank() ? detallesExtra : "sin detalles extra";;
        this.personalResponsable = personalResponsable;
    }

    public Reserva() {
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(LocalDate fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public long getCantidadNoches() {
        return cantidadNoches;
    }

    public void setCantidadNoches(long cantidadNoches) {
        this.cantidadNoches = cantidadNoches;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNombreReferencia() {
        return nombreReferencia;
    }

    public void setNombreReferencia(String nombreReferencia) {
        this.nombreReferencia = nombreReferencia;
    }

    public String getApellidoReferencia() {
        return apellidoReferencia;
    }

    public void setApellidoReferencia(String apellidoReferencia) {
        this.apellidoReferencia = apellidoReferencia;
    }

    public String getCelularReferencia() {
        return celularReferencia;
    }

    public void setCelularReferencia(String celularReferencia) {
        this.celularReferencia = celularReferencia;
    }

    public String getDetallesExtra() {
        return detallesExtra;
    }

    public void setDetallesExtra(String detallesExtra) {
        this.detallesExtra = detallesExtra;
    }

    public String getPersonalResponsable() {
        return personalResponsable;
    }

    public void setPersonalResponsable(String personalResponsable) {
        this.personalResponsable = personalResponsable;
    }

    @Override
    public String toString() {
        return  "=== Reserva ===\n" +
                "• ID: " + idReserva + "\n" +
                "• Fecha ingreso: " + fechaIngreso + "\n" +
                "• Fecha egreso: " + fechaEgreso + "\n" +
                "• Pax: " + pax + "\n" +
                "• Noches: " + cantidadNoches + "\n" +
                "• Habitación: " + habitacion + "\n" +
                "• Valor: $" + valor + "\n" +
                "• Nombre referencia: " + nombreReferencia + "\n" +
                "• Apellido referencia: " + apellidoReferencia + "\n" +
                "• Celular referencia: " + celularReferencia + "\n" +
                "• Detalles extra: " + detallesExtra + "\n" +
                "• Responsable: " + personalResponsable + "\n";
    }

    public static void actualizarContador(int ultimoId) {
        if (ultimoId >= contador) {
            contador = ultimoId + 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Reserva reserva)) return false;
        return idReserva == reserva.idReserva && pax == reserva.pax && cantidadNoches == reserva.cantidadNoches && Double.compare(valor, reserva.valor) == 0 && Objects.equals(fechaIngreso, reserva.fechaIngreso) && Objects.equals(fechaEgreso, reserva.fechaEgreso) && Objects.equals(habitacion, reserva.habitacion) && Objects.equals(nombreReferencia, reserva.nombreReferencia) && Objects.equals(apellidoReferencia, reserva.apellidoReferencia) && Objects.equals(celularReferencia, reserva.celularReferencia) && Objects.equals(detallesExtra, reserva.detallesExtra) && Objects.equals(personalResponsable, reserva.personalResponsable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReserva, fechaIngreso, fechaEgreso, pax, cantidadNoches, habitacion, valor, nombreReferencia, apellidoReferencia, celularReferencia, detallesExtra, personalResponsable);
    }
}
