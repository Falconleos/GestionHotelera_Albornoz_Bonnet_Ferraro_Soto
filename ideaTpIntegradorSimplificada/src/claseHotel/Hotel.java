package claseHotel;

import clases.Habitacion;
import gestor.Gestor;
import gestor.GestorHabitacion;
import gestor.GestorReserva;

public class Hotel {

    private String nombre;
    private String ubicacion;

    //declarar todos los gestores
    private GestorHabitacion gestorHabitacion;
    private GestorReserva gestorReserva;

    public Hotel(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        //inicializar todos los gestores
        this.gestorHabitacion = new GestorHabitacion();//los inicializamos vacíos
        this.gestorReserva = new GestorReserva();//los inicializamos vacíos
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

    @Override
    public String toString() {
        return "Hotel{" +
                "nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                '}';
    }
}
