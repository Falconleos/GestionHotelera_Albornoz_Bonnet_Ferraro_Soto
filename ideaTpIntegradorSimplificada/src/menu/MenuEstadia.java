package menu;

import claseHotel.Hotel;
import clases.*;
import excepcions.ListaVaciaException;
import gestor.*;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuEstadia {

    public static void mostrarMenu(GestorEstadia gestorEstadia, GestorReserva gestorReserva,
                                   GestorCliente gestorCliente, GestorServicio gestorServicio,
                                   GestorHabitacion gestorHabitacion,GestorCuenta gestorCuenta,Scanner sc) {

        boolean salir = false;
        while (!salir) {
            Hotel.limpiarPantallaSubMenu();
            System.out.println("\n--- MENÚ ESTADÍAS ---");
            System.out.println("1. Crear estadía");
            System.out.println("2. Listar estadías");
            System.out.println("3. Cancelar estadía");
            System.out.println("4. Buscar estadia por habitacion");
            System.out.println("5. Buscar estadia por apellido");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = -1;

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Formato inválido. Ingrese un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    crearEstadia(sc,gestorReserva,gestorHabitacion,gestorCliente,gestorServicio,gestorEstadia,gestorCuenta);
                    break;
                case 2:

                    try {
                        gestorEstadia.mostrar();
                    }catch (ListaVaciaException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 3:
                    //cancelar si la estadia esta paga...
                    break;
                case 4:

                    try {
                        buscarEstadiaXhabitacion(sc,gestorHabitacion,gestorEstadia);
                    }catch (ListaVaciaException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 5:

                    try {
                        buscarEstadiaXapellido(sc,gestorCliente,gestorEstadia);
                    }catch (ListaVaciaException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 0:
                    salir = true;
                    System.out.println("🔹 Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("⚠️ Opción incorrecta.");
            }
        }
    }

    //---------------------- CREAR ESTADÍA ----------------------//
    private static void crearEstadia(Scanner sc,GestorReserva gestorReserva,GestorHabitacion gestorHabitacion,GestorCliente gestorCliente,GestorServicio gestorServicio,GestorEstadia gestorEstadia,GestorCuenta gestorCuenta) {
        Reserva reservaSeleccionada = null;

        if(!gestorReserva.getLista().isEmpty()){

            // 1️⃣ Preguntar si tiene reserva
            System.out.print("¿El cliente ya tiene reserva? (S/N): ");
            String tieneReserva = sc.nextLine().trim().toUpperCase();

            if (tieneReserva.equals("S")) {
                System.out.print("Ingrese el apellido de referencia de la reserva: ");
                String apellido = sc.nextLine();
                List<Reserva> reservas = gestorReserva.buscarReservaPorApellido(apellido,gestorReserva);

                if (reservas.isEmpty()) {
                    System.out.println("⚠️ No se encontraron reservas con ese apellido.");
                    return;
                }

                System.out.println("\n--- Reservas encontradas ---");
                for (Reserva r : reservas) {
                    System.out.println(r);
                }

                System.out.print("Ingrese el ID de la reserva a usar: ");
                int idReserva = Integer.parseInt(sc.nextLine());

                for (Reserva r : reservas) {
                    if (r.getIdReserva() == idReserva) {
                        reservaSeleccionada = r;
                        break;
                    }
                }

                if (reservaSeleccionada == null) {
                    System.out.println("⚠️ No se seleccionó ninguna reserva válida.");
                    return;
                }

            } else {
                // Si no tiene reserva → generar reserva
                System.out.println("🔹 Vamos a crear una nueva reserva primero.");
                int reservasAntes = gestorReserva.getLista().size();

                MenuReserva.generarReserva(sc,gestorHabitacion,gestorReserva);

                List<Reserva> reservas = gestorReserva.getLista();
                if (reservas.size() > reservasAntes) {
                    reservaSeleccionada = reservas.get(reservas.size() - 1);
                    System.out.println("✅ Reserva generada correctamente y asignada a la estadía.");
                } else {
                    System.out.println("⚠️ No se pudo generar la reserva.");
                    return;
                }
            }

        }else {

            System.out.println("Aun no hay reservas cargadas...");
            System.out.println("🔹 Vamos a crear una nueva reserva primero.");
            int reservasAntes = gestorReserva.getLista().size();

            MenuReserva.generarReserva(sc,gestorHabitacion,gestorReserva);

            List<Reserva> reservas = gestorReserva.getLista();
            if (reservas.size() > reservasAntes) {
                reservaSeleccionada = reservas.get(reservas.size() - 1);
                System.out.println("✅ Reserva generada correctamente y asignada a la estadía.");
            } else {
                System.out.println("⚠️ No se pudo generar la reserva.");
                return;
            }

        }



        // 2️⃣ Manejo del cliente (igual que antes)
        Cliente clienteSeleccionado = null;
        System.out.print("¿El cliente ya existe? (S/N): ");
        String existeCliente = sc.nextLine().trim().toUpperCase();

        if (existeCliente.equals("S")) {
            System.out.print("Ingrese el apellido del cliente: ");
            String apellidoCliente = sc.nextLine();
            List<Cliente> clientes = gestorCliente.getLista();
            List<Cliente> encontrados = new ArrayList<>();

            for (Cliente c : clientes) {
                if (c.getApellido().equalsIgnoreCase(apellidoCliente)) {
                    encontrados.add(c);
                }
            }

            if (encontrados.isEmpty()) {
                System.out.println("⚠️ No se encontraron clientes con ese apellido.");
                return;
            }

            System.out.println("\n--- Clientes encontrados ---");
            for (Cliente c : encontrados) {
                System.out.println(c);
            }

            System.out.print("Ingrese el DNI del cliente a usar: ");
            String dniCliente = sc.nextLine();

            for (Cliente c : encontrados) {
                if (c.getDni().equalsIgnoreCase(dniCliente)) {
                    clienteSeleccionado = c;
                    break;
                }
            }

            if (clienteSeleccionado == null) {
                System.out.println("⚠️ No se seleccionó ningún cliente válido.");
                return;
            }

        } else {
            // Crear cliente nuevo
            System.out.println("\n--- Crear nuevo cliente ---");
            System.out.print("DNI: ");
            String dni = sc.nextLine();
            System.out.print("Ciudad de origen: ");
            String ciudad = sc.nextLine();

            clienteSeleccionado = new Cliente(dni, reservaSeleccionada.getNombreReferencia(), reservaSeleccionada.getApellidoReferencia(), reservaSeleccionada.getCelularReferencia(),ciudad);
            gestorCliente.agregar(clienteSeleccionado);
        }

        // 3️⃣ Selección de servicios
        List<Servicio> serviciosSeleccionados = new ArrayList<>();

        if(gestorServicio.getLista().isEmpty()){
            System.out.println("aun no hay servicios que agregar...");
            serviciosSeleccionados = null;
        }else{
                boolean agregarMas = true;

                while (agregarMas) {


                    try{
                        gestorServicio.mostrar();
                        System.out.print("Ingrese el ID del servicio a agregar (0 para terminar): ");
                        int idServicio = Integer.parseInt(sc.nextLine());

                        if (idServicio == 0) {
                            agregarMas = false;
                        } else {
                            Servicio s = gestorServicio.buscarServicioPorId(idServicio);
                            if (s != null) {
                                serviciosSeleccionados.add(s);
                                System.out.println("✅ Servicio agregado: " + s.getDetalle());
                            } else {
                                System.out.println("⚠️ No se encontró servicio con ese ID.");
                            }
                        }
                    }catch (NumberFormatException e){
                        System.out.println("Formato de respuesta invalido");
                    }

                }


        }

        // 4️⃣ Buscar mumero de habitación de la reserva
        int numHabitacion = reservaSeleccionada.getHabitacion().getNumero();


        if (numHabitacion == 0) {
            System.out.println("⚠️ No se encontró la habitación correspondiente a la reserva.");
            return;
        }

        // 5️⃣ Crear la estadía
        Estadia estadia = new Estadia(
                reservaSeleccionada,
                clienteSeleccionado,
                reservaSeleccionada.getPax(),
                reservaSeleccionada.getFechaIngreso(),
                reservaSeleccionada.getFechaEgreso(),
                serviciosSeleccionados,
                numHabitacion
        );

        //calculo total de estadia para crear cuenta
        Habitacion habitacionDeEstadia = gestorHabitacion.buscarHabitacionXnum(estadia.getNumHabitacion());
        long dias = ChronoUnit.DAYS.between(estadia.getFechaCheckIn(), estadia.getFechaCheckOut());
        double totalHabitacionEstadia = habitacionDeEstadia.getPrecio() * dias;
        double totalServicios = 0;

        if(!gestorServicio.getLista().isEmpty()){
            totalServicios = gestorServicio.calcularTotal(serviciosSeleccionados);;
        }

        double totalCuenta = totalHabitacionEstadia + totalServicios;

        // creacion de la cuenta automaticamente...
        Cuenta cuenta = new Cuenta(estadia,totalCuenta);
        gestorCuenta.agregar(cuenta);
        gestorEstadia.agregar(estadia);
        System.out.println("🎉 Estadía y cuenta creada exitosamente! Habitación: " + numHabitacion);
    }

    public static void buscarEstadiaXhabitacion(Scanner sc,GestorHabitacion gestorHabitacion,GestorEstadia gestorEstadia){

        if (gestorEstadia.getLista().isEmpty()){
            throw new ListaVaciaException("Aun no hay estadias cargadas...");
        }

        System.out.println("Ingrese el numero de habitacion:");
        int numeroHabitacion = 0;

        try {
            numeroHabitacion = Integer.parseInt(sc.nextLine()) ;
        } catch (NumberFormatException e) {
            System.out.println("Formato inválido. Ingrese un número.");
            return;
        }

        boolean existeHabitacion = gestorHabitacion.existeHabitacion(numeroHabitacion);

        if(existeHabitacion){

            Estadia estadiaEncontrada = gestorEstadia.buscarEstadiaXHabitacion(numeroHabitacion);

            if(estadiaEncontrada!=null){
                System.out.println(estadiaEncontrada);
            }else{
                System.out.println("no hay estadia actual en la habitacion: " + numeroHabitacion);
            }

        }else{
            System.out.println("numero de habitacion inexistente...");
        }

    }

    public static void buscarEstadiaXapellido(Scanner sc,GestorCliente gestorCliente,GestorEstadia gestorEstadia){

        if (gestorEstadia.getLista().isEmpty()){
            throw new ListaVaciaException("Aun no hay estadias cargadas...");
        }

        System.out.println("Ingrese el apellido:");
        String apellidoCliente = sc.nextLine();

        boolean existeCliente = gestorCliente.existeCliente(apellidoCliente);

        if(existeCliente){

            List<Estadia>estadiasEncontradas = gestorEstadia.buscarEstadiaPorApellido(apellidoCliente);

            if(estadiasEncontradas.isEmpty()){
                System.out.println("No hay estadias con ese apellido");
            }else{
                System.out.println("estadias con apellido " + apellidoCliente);
                for(Estadia e : estadiasEncontradas){
                    System.out.println(e);
                }
            }

        }else{
            System.out.println("Apellido inexistente...");
        }
    }



}
