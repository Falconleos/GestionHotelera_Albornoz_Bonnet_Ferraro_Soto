package menu;

import claseHotel.Hotel;
import clases.Personal;
import enums.Rol;
import enums.Turno;
import excepcions.PersonaNoEncontradaException;
import gestor.GestorPersonal;

import java.util.Scanner;

public class MenuPersonal {

    //Se podrian reemplzar la seleccion del turno y rol y sus filtrados con sout en vez de .values, que no vimos en la materia
    public static void mostrarMenu(Scanner sc, GestorPersonal gestorPersonal) {
        boolean salir = false;

        while (!salir) {
            Hotel.limpiarPantallaSubMenu();
            System.out.println("\n===== MENÚ PERSONAL =====");
            System.out.println("1. Agregar Personal");
            System.out.println("2. Eliminar Personal");
            System.out.println("3. Mostrar Todo el Personal");
            System.out.println("4. Filtrar por Rol");
            System.out.println("5. Filtrar por Turno");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    agregarPersonal(sc, gestorPersonal);
                    break;
                case 2:
                    eliminarPersonal(sc, gestorPersonal);
                    break;
                case 3:
                    gestorPersonal.mostrar();
                    break;
                case 4:
                    filtrarPorRol(sc, gestorPersonal);
                    break;
                case 5:
                    filtrarPorTurno(sc, gestorPersonal);
                    break;
                case 0:
                    salir = true;
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción incorrecta, intente nuevamente.");
            }
        }
    }

    private static void agregarPersonal(Scanner sc, GestorPersonal gestorPersonal) {
        System.out.print("DNI: ");
        String dni = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Celular: ");
        String celular = sc.nextLine();

        System.out.println("Seleccione Rol: ");
        for (Rol r : Rol.values()) {
            System.out.println(r.ordinal() + 1 + ". " + r);
        }
        int rolIndex = Integer.parseInt(sc.nextLine()) - 1;
        Rol rol = Rol.values()[rolIndex];

        System.out.println("Seleccione Turno: ");
        for (Turno t : Turno.values()) {
            System.out.println(t.ordinal() + 1 + ". " + t);
        }
        int turnoIndex = Integer.parseInt(sc.nextLine()) - 1;
        Turno turno = Turno.values()[turnoIndex];

        Personal nuevo = new Personal(dni, nombre, apellido, celular, rol, turno);
        gestorPersonal.agregar(nuevo);
    }

    private static void eliminarPersonal(Scanner sc, GestorPersonal gestorPersonal) {
        try{
            System.out.print("Ingrese DNI del personal a eliminar: ");
            String dni = sc.nextLine();

            int indiceElementoAEliminar = gestorPersonal.buscarIndicePorTexto(dni);
            if (indiceElementoAEliminar < 0) throw new PersonaNoEncontradaException("Personal no encontrado");
            gestorPersonal.eliminar(gestorPersonal.getLista().get(indiceElementoAEliminar));
        }catch (PersonaNoEncontradaException e){
            System.out.println(e.getMessage());
        }
    }

    private static void filtrarPorRol(Scanner sc, GestorPersonal gestorPersonal) {
        System.out.println("Seleccione Rol para filtrar: ");
        for (Rol r : Rol.values()) {
            System.out.println(r.ordinal() + 1 + ". " + r);
        }
        int rolIndex = Integer.parseInt(sc.nextLine()) - 1;
        Rol rol = Rol.values()[rolIndex];
        gestorPersonal.filtrarXRol(rol);
    }

    private static void filtrarPorTurno(Scanner sc, GestorPersonal gestorPersonal) {
        System.out.println("Seleccione Turno para filtrar: ");
        for (Turno t : Turno.values()) {
            System.out.println(t.ordinal() + 1 + ". " + t);
        }
        int turnoIndex = Integer.parseInt(sc.nextLine()) - 1;
        Turno turno = Turno.values()[turnoIndex];
        gestorPersonal.filtrarXTurno(turno);
    }
}
