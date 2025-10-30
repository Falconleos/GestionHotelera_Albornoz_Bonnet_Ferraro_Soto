package gestor;

import clases.Habitacion;
import clases.Reserva;
import excepcions.ListaVaciaException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorHabitacion extends Gestor<Habitacion>{

    private void validarListaVacia() throws ListaVaciaException {
        if (lista.isEmpty()) {
            throw new ListaVaciaException("Aún no hay habitaciones cargadas...");
        }
    }

    public List<Habitacion> listarHabitacionesXcapacidad(int pax)throws ListaVaciaException{
        validarListaVacia();
        List<Habitacion>filtradas = new ArrayList<>();
        for(Habitacion h : lista){
            if(h.getCapacidadMaxima()>=pax){
                filtradas.add(h);
            }
        }
        return filtradas;
    }

    public Habitacion buscarHabitacionXnum(int numero)throws ListaVaciaException{
        validarListaVacia();
        for(Habitacion h : lista){
            if(h.getNumero() == numero){
                return h;
            }
        }
        return null;
    }

    public void aumentarValorHabitacionesPorcentaje(int porcentaje)throws ListaVaciaException{
        validarListaVacia();
        for(Habitacion h : lista){
            double aumento = h.getPrecio() * (porcentaje/100.0);
            h.setPrecio( h.getPrecio() + aumento);
        }
    }

    public void rebajarValorHabitacionesPorcentaje(int porcentaje)throws ListaVaciaException{
        validarListaVacia();
        for(Habitacion h : lista){
            double rebaja = h.getPrecio() * (porcentaje/100.0);
            h.setPrecio( h.getPrecio() - rebaja);
        }
    }

    public List<Habitacion> listarHabitacionesSinReservas(GestorReserva gestorReserva){
        validarListaVacia(); // valida que haya habitaciones cargadas en este gestor

        List<Habitacion> habitacionesLibres = new ArrayList<>();

        for (Habitacion h : lista) { // recorremos las habitaciones de este gestor
            boolean reservada = false;

            // recorremos las reservas en GestorReserva
            for (Reserva r : gestorReserva.getLista()) {
                if (r.getHabitacion().equals(h)) {
                    reservada = true;
                    break;
                }
            }

            if (!reservada) {
                habitacionesLibres.add(h);
            }
        }
        return habitacionesLibres;
    }

    public void ordenarHabitacion(){
        Collections.sort(lista);
        System.out.println("Habitaciones orenadas por capacidad maxima...");
    }

}
