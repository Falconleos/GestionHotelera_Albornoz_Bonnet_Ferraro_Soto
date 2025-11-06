package menu;

import claseHotel.Hotel;
import clases.Habitacion;
import clases.Reserva;
import excepcions.ListaVaciaException;
import excepcions.PrecioInvalidoException;
import gestor.GestorHabitacion;
import gestor.GestorReserva;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuHabitacion {

    public static void mostrarMenu(Scanner sc, GestorHabitacion gestorHabitacion, GestorReserva gestorReserva){

        boolean continuar = true;
        while (continuar) {
            Hotel.limpiarPantallaSubMenu();

            System.out.println("==============================================");
            System.out.println("            GESTIÓN DE HABITACIONES 🛏️        ");
            System.out.println("==============================================");
            System.out.println("  1. Agregar habitaciones por defecto");
            System.out.println("  2. Agregar habitación");
            System.out.println("  3. Eliminar habitación");
            System.out.println("  4. Listar habitaciones");
            System.out.println("  5. Listar habitaciones por capacidad");
            System.out.println("  6. Buscar habitación por número");
            System.out.println("  7. Aumentar valor de las habitaciones");
            System.out.println("  8. Rebajar valor de las habitaciones");
            System.out.println("  9. Listar habitaciones sin reservas");
            System.out.println(" 10. Ordenar habitaciones por capacidad");
            System.out.println("----------------------------------------------");
            System.out.println("  0. Volver al menú principal");
            System.out.println("==============================================");
            System.out.print  (" Seleccione una opción: ");
            int opcion = -1;

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Formato inválido. Ingrese un número.");
                continue;
            }

            switch (opcion) {

                case 1:
                    agregar10HabitacionesPorDefecto(sc,gestorHabitacion);
                    break;
                case 2:
                    agregarUnaHabitacion(sc,gestorHabitacion);
                    break;
                case 3:
                    eliminarHabitacion(sc,gestorHabitacion);
                    break;
                case 4:
                    listarTodasHabitacions(gestorHabitacion);
                    break;
                case 5:
                    listarHabitacionesXcapacidad(sc,gestorHabitacion);
                    break;
                case 6:
                    buscarHabitacionPorNumero(sc,gestorHabitacion);
                    break;
                case 7:
                    aumentarValorHabitaciones(sc,gestorHabitacion);
                    break;
                case 8:
                    rebajarValorHabitaciones(sc,gestorHabitacion);
                    break;
                case 9:
                    listarHabitacionesSinReserva(gestorHabitacion,gestorReserva);
                    break;
                case 10:
                    ordenarPorCapacidad(gestorHabitacion);
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

    public static void agregar10HabitacionesPorDefecto(Scanner sc, GestorHabitacion gestorHabitacion) {
        //SIMPLES
        for(int i = 0;i < 3;i++){
            // 1. Definir la habitación que quieres agregar
            int numeroNuevaHabitacion = 100 + i; // Ejemplo de número

            // 2. Verificar si ya existe una habitación con ese número
            boolean habitacionExiste = false;

            // Itera sobre la lista actual de habitaciones
            for (Habitacion h : gestorHabitacion.getLista()) {
                if (h.getNumero() == numeroNuevaHabitacion) {
                    habitacionExiste = true;
                    break; // Si la encontramos, podemos salir del bucle inmediatamente
                }
            }

            // 3. Agregar solo si no existe
            if (!habitacionExiste) {
                // Habitacion(int numero, String tipo, int capacidadMaxima, double precio, String descipcion)
                Habitacion nuevaHab = new Habitacion(numeroNuevaHabitacion, "Simple", 1, 38000, "Habitación Simple defecto.");
                gestorHabitacion.agregar(nuevaHab);
                System.out.println("Habitación " + numeroNuevaHabitacion + " agregada.");
            } else {
                System.out.println("La habitación " + numeroNuevaHabitacion + " ya existe y no fue agregada.");
            }
        }
        //DOBLES
        for(int i = 0;i < 3;i++){
            // 1. Definir la habitación que quieres agregar
            int numeroNuevaHabitacion = 200 + i; // Ejemplo de número

            // 2. Verificar si ya existe una habitación con ese número
            boolean habitacionExiste = false;

            // Itera sobre la lista actual de habitaciones
            for (Habitacion h : gestorHabitacion.getLista()) {
                if (h.getNumero() == numeroNuevaHabitacion) {
                    habitacionExiste = true;
                    break; // Si la encontramos, podemos salir del bucle inmediatamente
                }
            }

            // 3. Agregar solo si no existe
            if (!habitacionExiste) {
                // Habitacion(int numero, String tipo, int capacidadMaxima, double precio, String descipcion)
                Habitacion nuevaHab = new Habitacion(numeroNuevaHabitacion, "Doble", 2, 45000, "Habitación Doble defecto.");
                gestorHabitacion.agregar(nuevaHab);
                System.out.println("Habitación " + numeroNuevaHabitacion + " agregada.");
            } else {
                System.out.println("La habitación " + numeroNuevaHabitacion + " ya existe y no fue agregada.");
            }
        }

        //CUADRUPLE
        for(int i = 0;i < 3;i++){
            // 1. Definir la habitación que quieres agregar
            int numeroNuevaHabitacion = 400 + i; // Ejemplo de número

            // 2. Verificar si ya existe una habitación con ese número
            boolean habitacionExiste = false;

            // Itera sobre la lista actual de habitaciones
            for (Habitacion h : gestorHabitacion.getLista()) {
                if (h.getNumero() == numeroNuevaHabitacion) {
                    habitacionExiste = true;
                    break; // Si la encontramos, podemos salir del bucle inmediatamente
                }
            }

            // 3. Agregar solo si no existe
            if (!habitacionExiste) {
                // Habitacion(int numero, String tipo, int capacidadMaxima, double precio, String descipcion)
                Habitacion nuevaHab = new Habitacion(numeroNuevaHabitacion, "cuadruple", 4, 62000, "Habitación Cuadruple defecto.");
                gestorHabitacion.agregar(nuevaHab);
                System.out.println("Habitación " + numeroNuevaHabitacion + " agregada.");
            } else {
                System.out.println("La habitación " + numeroNuevaHabitacion + " ya existe y no fue agregada.");
            }
        }

    }

    public static void listarTodasHabitacions(GestorHabitacion gestorHabitacion){
        try {
            gestorHabitacion.mostrar();
        } catch (ListaVaciaException e) {
            System.out.println("⚠ " + e.getMessage());
        }
    }

    public static void agregarUnaHabitacion(Scanner sc,GestorHabitacion gestorHabitacion){

        try {
            System.out.println("Ingrese numero:");
            int numeroHabitacion = Integer.parseInt(sc.nextLine());

            if(!gestorHabitacion.getLista().isEmpty()){
                for(Habitacion h : gestorHabitacion.getLista()){
                    if(numeroHabitacion == h.getNumero()){
                        System.out.println("Numero de habitacion existente...");
                        return;
                    }
                }
            }

            System.out.println("Ingrese el tipo de Habitacion:");
            String tipo = sc.nextLine();

            System.out.println("Ingrese capacidad Maxima");
            int capacidadMaxima = Integer.parseInt(sc.nextLine());

            System.out.println("Ingrese precio por noche:");
            double precio = Double.parseDouble(sc.nextLine());

            if(precio < 0){
                throw new PrecioInvalidoException("Precio invalido...");
            }

            System.out.println("Ingrese descripcion:");
            String descripcion = sc.nextLine();


            gestorHabitacion.agregar(new Habitacion(numeroHabitacion,tipo,capacidadMaxima,precio,descripcion));
            System.out.println("Habitacion " + tipo + " agregada con exito");

        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }catch (PrecioInvalidoException e){
            System.out.println(e.getMessage());
        }
    }

    public static void eliminarHabitacion(Scanner sc,GestorHabitacion gestorHabitacion){
        try {
            if(gestorHabitacion.getLista().isEmpty()){
                throw new ListaVaciaException("Aun no hay habitaciones cargadas...");
            }

            System.out.println("Ingrese el numero de habitacion a borrar:");
            int numeroBorrar = Integer.parseInt(sc.nextLine());

            Habitacion habitacionEncontrada = gestorHabitacion.buscarHabitacionXnum(numeroBorrar);

            if(habitacionEncontrada != null){

                gestorHabitacion.eliminar(habitacionEncontrada);
                System.out.println("Habitacion " + habitacionEncontrada.getNumero() + " ha sido eliminada...");

            }else{
                System.out.println("Numero inexistente...");
            }

        }catch (ListaVaciaException e){
            System.out.println(e.getMessage());
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }

    public static void listarHabitacionesXcapacidad(Scanner sc,GestorHabitacion gestorHabitacion){

        try {
            if(gestorHabitacion.getLista().isEmpty()){
                throw new ListaVaciaException("Aun no hay habitaciones cargadas...");
            }

            System.out.println("Ingrese cantidad de PAX:");
            int pax = Integer.parseInt(sc.nextLine());

            if(pax<0){
                System.out.println("Cantidad invalida...");
                return;
            }

            List<Habitacion>filtradas = gestorHabitacion.listarHabitacionesXcapacidad(pax);

            if(filtradas.isEmpty()){
                System.out.println("No hay habitaciones para esa cantidad de PAX");
            }else{

                for(Habitacion h : filtradas){
                    System.out.println(h);
                }

            }

        }catch (ListaVaciaException e){
            System.out.println(e.getMessage());
        }
        catch (NumberFormatException e){
            System.out.println("cantidad Invalida...");
        }
    }

    public static void buscarHabitacionPorNumero(Scanner sc,GestorHabitacion gestorHabitacion){

        try{

            if(gestorHabitacion.getLista().isEmpty()){
                throw new ListaVaciaException("Aun no hay habitaciones cargadas...");
            }

            System.out.println("Ingrese el numero de la habitacion a buscar:");
            int numBuscar = Integer.parseInt(sc.nextLine());

            Habitacion habitacionEncontrada = gestorHabitacion.buscarHabitacionXnum(numBuscar);

            if(habitacionEncontrada != null){
                System.out.println(habitacionEncontrada);
            }else{
                System.out.println("Habitacion inexistente...");
            }

        }catch (ListaVaciaException e){
            System.out.println(e.getMessage());
        }
        catch (NumberFormatException e){
            System.out.println("Numero invalido...");
        }

    }

    public static void aumentarValorHabitaciones(Scanner sc,GestorHabitacion gestorHabitacion){

        try{

            if(gestorHabitacion.getLista().isEmpty()){
                throw new ListaVaciaException("Aun no hay habitaciones cargadas...");
            }

            System.out.println("Ingrese el porcentaje a aumentar:");
            int porcentaje = Integer.parseInt(sc.nextLine());

            gestorHabitacion.aumentarValorHabitacionesPorcentaje(porcentaje);

            System.out.println("Todas las habitaciones aumentadas un " + porcentaje + "%");


        }catch (ListaVaciaException e){
            System.out.println(e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("Porcentaje invalido...");
        }
    }


    public static void rebajarValorHabitaciones(Scanner sc,GestorHabitacion gestorHabitacion){

        try{
            if(gestorHabitacion.getLista().isEmpty()){
                throw new ListaVaciaException("Aun no hay habitaciones cargadas...");
            }

            System.out.println("Ingrese el porcentaje a rebajar:");
            int porcentaje = Integer.parseInt(sc.nextLine());

            gestorHabitacion.rebajarValorHabitacionesPorcentaje(porcentaje);

            System.out.println("Todas las habitaciones rebajadas un " + porcentaje + "%");


        }catch (ListaVaciaException e){
            System.out.println(e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("Porcentaje invalido...");
        }
    }

    public static void listarHabitacionesSinReserva(GestorHabitacion gestorHabitacion,GestorReserva gestorReserva){

        try{

            System.out.println("Lista de habitaciones sin reserva:");

            if (gestorHabitacion.getLista().isEmpty()) {
                throw new ListaVaciaException("No hay habitaciones cargadas.");
            }

            if(gestorReserva.getLista().isEmpty()){
                System.out.println(gestorHabitacion.getLista());
                return;
            }


            List<Habitacion>habitacionesSinReserva = new ArrayList<>();

            for(Habitacion habitacion : gestorHabitacion.getLista()){
                boolean estaReservada = false;

                for(Reserva reserva : gestorReserva.getLista()){

                    if(reserva.getHabitacion().getIdHabitacion() == habitacion.getIdHabitacion()){
                        estaReservada = true;
                        break;
                    }
                }

                if(!estaReservada){
                    habitacionesSinReserva.add(habitacion);
                }
            }

            if (habitacionesSinReserva.isEmpty()) {
                System.out.println("Todas las habitaciones están reservadas actualmente.");
            } else {
                for (Habitacion h : habitacionesSinReserva) {
                    System.out.println(h);
                }
            }


        }catch (ListaVaciaException e){
            System.out.println(e.getMessage());
        }

    }

    public static void ordenarPorCapacidad(GestorHabitacion gestorHabitacion){
        try{
            if(gestorHabitacion.getLista().isEmpty()){
                throw new ListaVaciaException("No hay habitaciones cargadas...");
            }

            gestorHabitacion.ordenarHabitacion();

        } catch (ListaVaciaException e) {
            System.out.println(e.getMessage());;
        }
    }



}
