package claseHotel;

import clases.Habitacion;
import gestor.*;

public class Hotel {

    private String nombre;
    private String ubicacion;

    //declarar todos los gestores
    private GestorHabitacion gestorHabitacion;
    private GestorReserva gestorReserva;
    private GestorCliente gestorCliente;
    private GestorPersonal gestorPersonal;
    private GestorEstadia gestorEstadia;
    private GestorServicio gestorServicio;
    private GestorCuenta gestorCuenta;
    private GestorPago gestorPago;

    public Hotel(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        //inicializar todos los gestores
        this.gestorHabitacion = new GestorHabitacion();//los inicializamos vacíos
        this.gestorReserva = new GestorReserva();
        this.gestorCliente = new GestorCliente();
        this.gestorPersonal = new GestorPersonal();
        this.gestorEstadia = new GestorEstadia();
        this.gestorServicio = new GestorServicio();
        this.gestorCuenta = new GestorCuenta();
        this.gestorPago = new GestorPago();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    //getter de los gestores inicializados
    public GestorHabitacion getGestorHabitacion() {
        return gestorHabitacion;
    }

    public GestorReserva getGestorReserva() {
        return gestorReserva;
    }

    public GestorCliente getGestorCliente() { return gestorCliente; }

    public GestorPersonal getGestorPersonal() { return gestorPersonal; }

    public GestorEstadia getGestorEstadia() {
        return gestorEstadia;
    }

    public GestorServicio getGestorServicio() {
        return gestorServicio;
    }

    public GestorCuenta getGestorCuenta() {
        return gestorCuenta;
    }

    public GestorPago getGestorPago() {
        return gestorPago;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                '}';
    }

    public static void limpiarPantallaMenu(){
        for(int i = 0;i<50;i++){
            System.out.println("");
        }
    }

    public static void limpiarPantallaSubMenu(){
        for(int i = 0;i<2;i++){
            System.out.println("");
        }
    }

}
