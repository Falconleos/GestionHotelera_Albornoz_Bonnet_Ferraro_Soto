package gestor;

import clases.Habitacion;
import clases.Reserva;
import excepcions.ListaVaciaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorReserva extends Gestor<Reserva>{

    private void validarListaVacia() throws ListaVaciaException {
        if (lista.isEmpty()) {
            throw new ListaVaciaException("Aún no hay reservas cargadas...");
        }
    }

    

    public List<Reserva> listarXapellidoReferencial(String apellido){
        validarListaVacia();
        List<Reserva>filtradas = new ArrayList<>();
        for(Reserva r : lista){
            if(r.getApellidoReferencia().equalsIgnoreCase(apellido)){
                filtradas.add(r);
            }
        }
        return filtradas;
    }

    public List<Reserva>listarIngresoDelDia(){
        validarListaVacia();
        List<Reserva>filtradas = new ArrayList<>();

        for(Reserva r : lista){
            if(r.getFechaIngreso().equals(LocalDate.now())){
                filtradas.add(r);
            }
        }
        return filtradas;
    }

    public List<Reserva>listarPorFecha(LocalDate fecha){
        validarListaVacia();
        List<Reserva>filtradas = new ArrayList<>();

        for(Reserva r : lista){
            if(r.getFechaIngreso().equals(fecha)){
                filtradas.add(r);
            }
        }
        return filtradas;
    }

    public List<Reserva>listarPorNumHabitacion(int numero){
        validarListaVacia();
        List<Reserva>filtradas = new ArrayList<>();

        for(Reserva r : lista){
            if(r.getHabitacion().getNumero() == numero){
                filtradas.add(r);
            }
        }
        return filtradas;
    }

    public double calcularPromedioPlazosReservas(){

        validarListaVacia();

        long cantNoches = 0;
        int contadorReservas = 0;
        double promedio = 0.0;

        for(Reserva r : lista){
            contadorReservas++;
            cantNoches += r.getCantidadNoches();
        }


        return (double) cantNoches / (double) contadorReservas;
    }

    public List<Reserva> buscarReservaPorApellido(String apellido,GestorReserva gestorReserva){
        validarListaVacia();
        List<Reserva>filtradas = new ArrayList<>();

        for(Reserva r : gestorReserva.getLista()){
            if(r.getApellidoReferencia().equalsIgnoreCase(apellido)){
                filtradas.add(r);
            }
        }
        return filtradas;
    }

    public Reserva buscarReservaPorId(int id){
        validarListaVacia();
        for(Reserva r : lista){
            if(r.getIdReserva() == id){
                return r;
            }
        }
        return null;
    }

    public void generarComentario(String comentario,Reserva reserva){
        reserva.setDetallesExtra(comentario);
        System.out.println("Comentario agregado exitosamente.");
    }

    //Implementacion metodo abstracto
    @Override
    public int buscarIndicePorTexto(String textoABuscar) {
        return 0;
    }
}
