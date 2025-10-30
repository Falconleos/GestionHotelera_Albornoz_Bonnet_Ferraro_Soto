package menu;

import clases.Habitacion;
import clases.Reserva;
import excepcions.ListaVaciaException;
import gestor.GestorHabitacion;
import gestor.GestorReserva;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class MenuReserva {

    public static void mostrarMenu(Scanner sc, GestorHabitacion gestorHabitacion, GestorReserva gestorReserva){

        boolean continuar = true;
        while (continuar) {

            System.out.println("Gestion Reservas");
            System.out.println("1.Generar Reserva");
            System.out.println("2.Ver Checks in del día");
            System.out.println("3.Ver Checks in por fecha");
            System.out.println("4.Listar Reservas por Apellido");
            System.out.println("5.Listar Reservas por rango de fecha");
            System.out.println("6.Listar Reservas por numero de habitación");
            System.out.println("7.Calcular promedio de noches de las reservas");
            System.out.println("8.Eliminar reserva");
            System.out.println("9.Listar todas las reservas");
            System.out.println("0.Salir");
            System.out.println("Elija la opcion:");

            int opcion = -1;

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Formato inválido. Ingrese un número.");
                continue;
            }

            switch (opcion) {

                case 1:
                    generarReserva(sc,gestorHabitacion,gestorReserva);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    gestorReserva.mostrar();
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

                // 1️⃣ Pedir fechas
                System.out.print("Fecha de ingreso (yyyy-MM-dd): ");
                LocalDate ingreso = LocalDate.parse(sc.nextLine());

                if(ingreso.isBefore(LocalDate.now())){
                    System.out.println("La fecha de ingreso no puede ser menor a la fecha actual...");
                    return;
                }

                System.out.print("Fecha de egreso (yyyy-MM-dd): ");
                LocalDate egreso = LocalDate.parse(sc.nextLine());

                if (!egreso.isAfter(ingreso)) {
                    System.out.println("⚠ La fecha de egreso debe ser posterior a la de ingreso.");
                    return;
                }

                // 2️⃣ Pedir cantidad de personas
                System.out.print("Cantidad de personas: ");
                int pax = Integer.parseInt(sc.nextLine());

                // 3️⃣ Intentar seleccionar habitación
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
                    // 4️⃣ Pedir datos del huésped
                    System.out.print("Nombre del huésped: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellido del huésped: ");
                    String apellido = sc.nextLine();
                    System.out.print("Celular de contacto: ");
                    String celular = sc.nextLine();

                    long noches = ChronoUnit.DAYS.between(ingreso, egreso);
                    double valorTotal = noches * habitacion.getPrecio();

                    // 5️⃣ Crear reserva
                    Reserva reserva = new Reserva(ingreso, egreso, pax, noches, habitacion, valorTotal, nombre, apellido, celular, "sin detalles extra", "Recepción");

                    gestorReserva.agregar(reserva);

                    System.out.println("🎉 Reserva creada con éxito!");
                    System.out.println(reserva);
                }else{
                    System.out.println("Reserva rechazada...");
                }

            }catch (DateTimeException e){
                System.out.println("Fecha invalida...");
            }

    }


}
