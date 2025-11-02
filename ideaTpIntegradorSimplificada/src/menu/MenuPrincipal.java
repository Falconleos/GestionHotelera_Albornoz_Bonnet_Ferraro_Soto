package menu;

import claseHotel.Hotel;
import excepcions.PersonaNoEncontradaException;

import java.util.Scanner;

public class MenuPrincipal {

    private Hotel hotel;

    public MenuPrincipal(Hotel hotel) {
        this.hotel = hotel;
    }

    public void mostrarMenu() throws PersonaNoEncontradaException {

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar){

            System.out.println("Menu Hotel: " + hotel.getNombre() + " Ubicacion: " + hotel.getUbicacion());
            System.out.println("1.Gestion de Habitaciones");
            System.out.println("2.Gestion de Reservas");
            System.out.println("3. Gestion de Clientes");
            System.out.println("4. Gestion del Personal");
            System.out.println("0.Salir");
            System.out.println("Elija la opcion:");

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
