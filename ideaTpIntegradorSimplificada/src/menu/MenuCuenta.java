package menu;

import gestor.*;

import java.util.Scanner;

public class MenuCuenta {

    public static void mostrarMenu(GestorEstadia gestorEstadia, GestorReserva gestorReserva,
                                   GestorCliente gestorCliente, GestorServicio gestorServicio,
                                   GestorHabitacion gestorHabitacion, Scanner sc) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ CUENTA ---");
            System.out.println("1. ");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("5. ");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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

}
