package gestor;

import clases.Habitacion;
import clases.Reserva;
import excepcions.ListaVaciaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorReserva extends Gestor<Reserva>{

    private void validarListaVacia() throws ListaVaciaException {
        if (lista.isEmpty()) {
            throw new ListaVaciaException("Aún no hay habitaciones cargadas...");
        }
    }

    public Habitacion seleccionarHabitacion(GestorHabitacion gestorHabitacion,
                                            LocalDate ingreso,
                                            LocalDate egreso,
                                            int pax) throws ListaVaciaException {


        // 1️⃣ Filtrar habitaciones por capacidad
        List<Habitacion> habitacionesPosibles = gestorHabitacion.listarHabitacionesXcapacidad(pax);

        // 2️⃣ Si no hay reservas aún, devolver directamente la primera habitación posible
        if (lista.isEmpty()) {
            return habitacionesPosibles.get(0);
        }

        // 3️⃣ Si hay reservas, verificar disponibilidad por fechas
        List<Habitacion> habitacionesDisponibles = new ArrayList<>();

        for (Habitacion h : habitacionesPosibles) {
            boolean disponible = true;

            for (Reserva r : lista) {
                if (r.getHabitacion().equals(h)) {
                    // ✅ Permitir ocupar el mismo día que otra reserva se libera
                    boolean noSolapa = egreso.isBefore(r.getFechaIngreso()) || egreso.isEqual(r.getFechaIngreso())
                            || ingreso.isAfter(r.getFechaEgreso()) || ingreso.isEqual(r.getFechaEgreso());

                    if (!noSolapa) {
                        disponible = false;
                        break;
                    }
                }
            }

            if (disponible) {
                habitacionesDisponibles.add(h);
            }
        }

        // 4️⃣ Si no se encontró ninguna disponible
        if (habitacionesDisponibles.isEmpty()) {
            throw new ListaVaciaException("No hay habitaciones disponibles para esas fechas y cantidad de personas.");
        }
        // 5️⃣ Retorna la primera disponible
        return habitacionesDisponibles.get(0);
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

    public List<Reserva>listarPorRangoFechas(LocalDate inferior,LocalDate superior){
        validarListaVacia();
        List<Reserva>filtradas = new ArrayList<>();
        for(Reserva r : lista){
            if(!r.getFechaIngreso().isAfter(superior) && !r.getFechaEgreso().isBefore(inferior)){
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




}
