package clases;

import java.time.LocalDate;
import java.util.List;

public class Estadia {

    private static int contador = 1;
    private int idEstadia;
    private Reserva reserva;
    private Cliente cliente;
    private int pax;
    private LocalDate fechaCheckIn;
    private LocalDate fechaCheckOut;
    private List<Servicio>listaServicios;
    private int numHabitacion;
    private boolean cancelada;
    private LocalDate fechaCancelacion;

    public Estadia(Reserva reserva, Cliente cliente, int pax, LocalDate fechaCheckIn, LocalDate fechaCheckOut, List<Servicio> listaServicios, int numHabitacion) {
        this.idEstadia = contador++;
        this.reserva = reserva;
        this.cliente = cliente;
        this.pax = pax;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.listaServicios = listaServicios;
        this.numHabitacion = numHabitacion;
        this.cancelada = false;
        this.fechaCancelacion = null;
    }

    public int getIdEstadia() {
        return idEstadia;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public LocalDate getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(LocalDate fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }

    public LocalDate getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(LocalDate fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
    }

    public List<Servicio> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<Servicio> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public LocalDate getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(LocalDate fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    @Override
    public String toString() {
        return "=== Estadia ===\n" +
                "• ID: " + idEstadia + "\n" +
                "• Reserva: " + reserva + "\n" +
                "• Cliente: " + cliente + "\n" +
                "• Pax: " + pax + "\n" +
                "• Check-in: " + fechaCheckIn + "\n" +
                "• Check-out: " + fechaCheckOut + "\n" +
                "• Servicios: " + listaServicios + "\n" +
                "• Habitación Nº: " + numHabitacion + "\n" +
                "• Cancelada: " + cancelada + "\n" +
                "• Fecha de cancelación: " + fechaCancelacion + "\n";
    }
}
