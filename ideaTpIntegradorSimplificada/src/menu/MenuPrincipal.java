package menu;

import claseHotel.Hotel;
import manejoJSON.GestionJSONdeserializar;
import manejoJSON.GestionJSONserializar;

import java.util.Scanner;

public class MenuPrincipal {


    private Hotel hotel;

    public MenuPrincipal(Hotel hotel) {
        this.hotel = hotel;
    }

    public void mostrarMenu(){

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar){
            Hotel.limpiarPantallaMenu();

            System.out.println("==============================================");
            System.out.println("               MENU PRINCIPAL                 ");
            System.out.println("==============================================");
            System.out.println(" Hotel: " + hotel.getNombre());
            System.out.println(" Ubicación: " + hotel.getUbicacion());
            System.out.println("----------------------------------------------");
            System.out.println(" 🛏️  1. Gestión de Habitaciones");
            System.out.println(" 📅  2. Gestión de Reservas");
            System.out.println(" 👤  3. Gestión de Clientes");
            System.out.println(" 🧑‍💼  4. Gestión del Personal");
            System.out.println(" 🏨  5. Gestión de Estadías");
            System.out.println(" 💳  6. Gestión de Cuentas");
            System.out.println(" ✅  7. Gestión de Pagos");
            System.out.println(" 🛠️  8. Gestión de Servicios");
            System.out.println(" 🛠️  9. Guardar Cambios en el sistema");
            System.out.println("----------------------------------------------");
            System.out.println(" 🚪  0. Salir");
            System.out.println("==============================================");
            System.out.print  (" Seleccione una opción: ");

            int opcion = -1;

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido. Ingrese un número.");
                continue;
            }

            switch (opcion){

                case 1:
                    MenuHabitacion.mostrarMenu(sc,hotel.getGestorHabitacion(),hotel.getGestorReserva());
                    break;
                case 2:
                    MenuReserva.mostrarMenu(sc, hotel.getGestorHabitacion(), hotel.getGestorReserva());
                    break;
                case 3:
                    MenuCliente.mostrarMenu(sc,hotel.getGestorCliente());
                    break;
                case 4:
                    MenuPersonal.mostrarMenu(sc, hotel.getGestorPersonal());
                    break;
                case 5:
                    MenuEstadia.mostrarMenu(hotel.getGestorEstadia(), hotel.getGestorReserva(), hotel.getGestorCliente(), hotel.getGestorServicio(), hotel.getGestorHabitacion(), hotel.getGestorCuenta(), hotel.getGestorPago(), sc);
                    break;
                case 6:
                    MenuCuenta.mostrarMenu(hotel.getGestorEstadia(), hotel.getGestorReserva(), hotel.getGestorCliente(), hotel.getGestorServicio(), hotel.getGestorHabitacion(), hotel.getGestorCuenta(), sc);
                    break;
                case 7:
                    MenuPago.mostrarMenu(hotel.getGestorEstadia(), hotel.getGestorReserva(), hotel.getGestorCliente(), hotel.getGestorServicio(), hotel.getGestorHabitacion(), hotel.getGestorPago(), hotel.getGestorCuenta(),sc);
                    break;
                case 8:
                    MenuServicio.mostrarMenu(hotel.getGestorEstadia(), hotel.getGestorReserva(), hotel.getGestorCliente(), hotel.getGestorServicio(), hotel.getGestorHabitacion(), hotel.getGestorCuenta(), sc);
                    break;
                case 9:
                    GestionJSONserializar.modificarJson(hotel);
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
}
