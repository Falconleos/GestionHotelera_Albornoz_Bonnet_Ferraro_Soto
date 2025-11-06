package menu;

import claseHotel.Hotel;
import clases.Habitacion;
import clases.Reserva;
import excepcions.ListaVaciaException;
import gestor.GestorHabitacion;
import gestor.GestorReserva;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuReserva {

    public static void mostrarMenu(Scanner sc, GestorHabitacion gestorHabitacion, GestorReserva gestorReserva){

        boolean continuar = true;
        while (continuar) {
            Hotel.limpiarPantallaSubMenu();
            System.out.println("Gestion Reservas");
            System.out.println("1.Generar Reserva");
            System.out.println("2.Ver Checks in del día");
            System.out.println("3.Ver Checks in por fecha");
            System.out.println("4.Listar Reservas por Apellido");
            System.out.println("5.Listar Reservas por numero de habitación");
            System.out.println("6.Calcular promedio de noches de las reservas");
            System.out.println("7.Cancelar reserva");
            System.out.println("8.Listar todas las reservas");
            System.out.println("9.Agregar comentario a una reservas");
            System.out.println("0.Salir");
            System.out.println("Elija la opcion:");

            int opcion = -1;

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido. Ingrese un número.");
                continue;
            }

            switch (opcion) {

                case 1:
                    generarReserva(sc,gestorHabitacion,gestorReserva);
                    break;
                case 2:
                    verCheckInDia(gestorReserva);
                    break;
                case 3:
                    verCheckInXfecha(sc,gestorReserva);
                    break;
                case 4:
                    listarReservaPorApellido(sc,gestorReserva);
                    break;
                case 5:
                    listarReservaPorHabitacion(sc,gestorReserva);
                    break;
                case 6:
                    calcularPromedioDeNoches(sc,gestorReserva);
                    break;
                case 7:
                    cancelarReserva(sc,gestorReserva);
                    break;
                case 8:

                    try {
                        gestorReserva.mostrar();
                    }catch (ListaVaciaException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 0:
                    continuar = false;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion inválida...");
                    break;
            }
        }

    }

    public static void generarReserva(Scanner sc, GestorHabitacion gestorHabitacion, GestorReserva gestorReserva){
        try {
            System.out.println("=== GENERAR RESERVA ===");
            try {
                if(gestorHabitacion.getLista().isEmpty()){
                    throw new ListaVaciaException("Lista vacía...");
                }
            }catch (ListaVaciaException e){
                System.out.println("Aun no hay habitaciones generadas...");
                return;
            }

            //Pedir fechas
            System.out.print("Fecha de ingreso (yyyy-MM-dd): ");
            LocalDate ingreso = LocalDate.parse(sc.nextLine());

            if(ingreso.isBefore(LocalDate.now())){
                System.out.println("La fecha de ingreso no puede ser menor a la fecha actual...");
                return;
            }

            System.out.print("Fecha de egreso (yyyy-MM-dd): ");
            LocalDate egreso = LocalDate.parse(sc.nextLine());

            if (!egreso.isAfter(ingreso)) {
                System.out.println("La fecha de egreso debe ser anterior o igual a la de ingreso.");
                return;
            }

            //Pedir cantidad de personas
            System.out.print("Cantidad de personas: ");
            int pax = Integer.parseInt(sc.nextLine());

            //Intentar seleccionar habitación
            Habitacion habitacion = null;

            habitacion = gestorReserva.seleccionarHabitacion(gestorHabitacion, ingreso, egreso, pax);

            if(habitacion!=null){
                System.out.println("\n");
                System.out.println("Habitacion disponible: ");
                System.out.println("Numero: " + habitacion.getNumero());
                System.out.println("Tipo: " + habitacion.getTipo());
                System.out.println("Capacidad: " + habitacion.getCapacidadMaxima());
                System.out.println("Valor por noche: $" + habitacion.getPrecio());
                System.out.println("Cantidad de noches: " + ChronoUnit.DAYS.between(ingreso,egreso));
                System.out.println("Total por estadia: $" + habitacion.getPrecio() * ChronoUnit.DAYS.between(ingreso,egreso));
            }else{
                System.out.println("No hay habitaciones disponible...");
            }

            if(habitacion.getCapacidadMaxima()>pax){
                System.out.println("\nLa habitación tiene mas capacidad que la buscada");
            }

            System.out.println("\n");
            System.out.println("Desea Reservar? S/N");
            String respuesta = sc.nextLine();

            if(respuesta.equalsIgnoreCase("s")){
                //Pedir datos del huésped
                System.out.print("Nombre del pasajero: ");
                String nombre = sc.nextLine();
                System.out.print("Apellido del pasajero: ");
                String apellido = sc.nextLine();
                System.out.print("Celular del pasajero: ");
                String celular = sc.nextLine();

                long noches = ChronoUnit.DAYS.between(ingreso, egreso);
                double valorTotal = noches * habitacion.getPrecio();

                //Crear reserva
                Reserva reserva = new Reserva(ingreso, egreso, pax, noches, habitacion, valorTotal, nombre, apellido, celular, "sin detalles extra", "Recepción");

                gestorReserva.agregar(reserva);

                System.out.println("Desea agregar un comentario a la reserva: S/N");
                String respuesta1 = sc.nextLine();

                if(respuesta1.equalsIgnoreCase("s")){

                    System.out.println("Ingrese el comentario:");
                    String comentario = sc.nextLine();

                    gestorReserva.generarComentario(comentario, reserva);
                }

                System.out.println("Reserva creada con éxito!");
                System.out.println(reserva);



            }else{
                System.out.println("Reserva rechazada...");
            }

        }catch (DateTimeException e){
            System.out.println("Fecha invalida...");
        }catch (NumberFormatException e){
            System.out.println("formato invalido...");
        }
    }

    public static void verCheckInDia(GestorReserva gestorReserva){

        if(gestorReserva.getLista().isEmpty()){

            System.out.println("Aun no hay reservas Cargadas...");

        }else {
            List<Reserva>reservasEncontradas = gestorReserva.listarIngresoDelDia();

            if(reservasEncontradas.isEmpty()){
                System.out.println("Hoy no hay ingresos...");
            }else{
                System.out.println("Ingresos del dia:");
                for(Reserva r : reservasEncontradas){
                    System.out.println(r);
                }
            }

        }

    }

    public static void verCheckInXfecha(Scanner sc,GestorReserva gestorReserva){

        if(gestorReserva.getLista().isEmpty()){

            System.out.println("Aun no hay reservas Cargadas...");

        }else {
            System.out.println("Ingrese la fecha (yyyy-mm-dd):");
            LocalDate fecha = LocalDate.parse(sc.nextLine());

            List<Reserva>reservasEncontradas = gestorReserva.listarPorFecha(fecha);

            if(reservasEncontradas.isEmpty()){
                System.out.println("no hay ingresos para " + fecha);
            }else{
                System.out.println("Ingresos del dia " + fecha);
                for(Reserva r : reservasEncontradas){
                    System.out.println(r);
                }
            }
        }
    }

    public static void listarReservaPorApellido(Scanner sc ,GestorReserva gestorReserva){

        if(gestorReserva.getLista().isEmpty()){

            System.out.println("Aun no hay reservas Cargadas...");

        }else {
            System.out.println("Ingrese el apellido:");
            String apellido = sc.nextLine();

            List<Reserva>reservasEncontradas = gestorReserva.listarXapellidoReferencial(apellido);

            if(reservasEncontradas.isEmpty()){
                System.out.println("Hoy no hay reservas con apellido " + apellido);
            }else{
                System.out.println("Reservas de apellido " + apellido);
                for(Reserva r : reservasEncontradas){
                    System.out.println(r);
                }
            }

        }

    }

    public static void listarReservaPorHabitacion(Scanner sc ,GestorReserva gestorReserva){

        if(gestorReserva.getLista().isEmpty()){

            System.out.println("Aun no hay reservas Cargadas...");

        }else {
            System.out.println("Ingrese el numero de habitacion:");
            int numHabitacion = 0;

            try {
                numHabitacion = Integer.parseInt(sc.nextLine()) ;
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido. Ingrese un número.");
                return;
            }


            List<Reserva>reservasEncontradas = gestorReserva.listarPorNumHabitacion(numHabitacion);

            if(reservasEncontradas.isEmpty()){
                System.out.println("Hoy no hay reservas para la habitacion " + numHabitacion);
            }else{
                System.out.println("Reservas de la habitacion " + numHabitacion);
                for(Reserva r : reservasEncontradas){
                    System.out.println(r);
                }
            }

        }

    }

    public static void calcularPromedioDeNoches(Scanner sc ,GestorReserva gestorReserva){

        if(gestorReserva.getLista().isEmpty()){
            System.out.println("Aun no hay reservas Cargadas...");
        }else {
            Double promedioEstadias = gestorReserva.calcularPromedioPlazosReservas();
            System.out.println("Promedio de estadias: " +promedioEstadias);

        }
    }

    public static void cancelarReserva(Scanner sc,GestorReserva gestorReserva){
        if(gestorReserva.getLista().isEmpty()){
            System.out.println("Aun no hay reservas Cargadas...");
        }else {

            System.out.println("Ingrese el apellido:");
            String apellido = sc.nextLine();

            List<Reserva>reservasEncontradas = gestorReserva.buscarReservaPorApellido(apellido,gestorReserva);

            if(reservasEncontradas.isEmpty()){
                System.out.println("No existen reservas con el apellido " + apellido);
            }else{
                System.out.println("Reservas encontradas:");
                for(Reserva r : reservasEncontradas){
                    System.out.println(r);
                }

                System.out.println("Ingrese el id del a reserva a cancelar:");
                int idEliminar = 0;

                try {
                    idEliminar = Integer.parseInt(sc.nextLine()) ;
                    Reserva rEliminar = new Reserva();

                    for(Reserva r : reservasEncontradas){
                        if(r.getIdReserva() == idEliminar){
                            rEliminar = r;
                        }
                    }

                    gestorReserva.eliminar(rEliminar);
                    System.out.println("Reserva de " + apellido + " eliminada correctamente");

                } catch (NumberFormatException e) {
                    System.out.println("Formato inválido. Ingrese un número.");
                }
            }
        }
    }

}
