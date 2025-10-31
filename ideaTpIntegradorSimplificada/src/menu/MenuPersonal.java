package menu;

import clases.Personal;
import enums.Rol;
import enums.Turno;
import gestores.GestorPersonal;

import java.util.Scanner;

public class MenuPersonal {

    //Se podrian reemplzar la seleccion del turno y rol y sus filtrados con sout en vez de .values, que no vimos en la materia

    private GestorPersonal gestorPersonal;
    private Scanner sc;

    public MenuPersonal(GestorPersonal gestorPersonal) {
        this.gestorPersonal = gestorPersonal;
        this.sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
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
                    agregarPersonal();
                    break;
                case 2:
                    eliminarPersonal();
                    break;
                case 3:
                    gestorPersonal.mostrarPersonas();
                    break;
                case 4:
                    filtrarPorRol();
                    break;
                case 5:
                    filtrarPorTurno();
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

    private void agregarPersonal() {
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
        gestorPersonal.agregarPersona(nuevo);
    }

    private void eliminarPersonal() {
        System.out.print("Ingrese DNI del personal a eliminar: ");
        String dni = sc.nextLine();
        gestorPersonal.eliminarPersonaPorDni(dni);
    }

    private void filtrarPorRol() {
        System.out.println("Seleccione Rol para filtrar: ");
        for (Rol r : Rol.values()) {
            System.out.println(r.ordinal() + 1 + ". " + r);
        }
        int rolIndex = Integer.parseInt(sc.nextLine()) - 1;
        Rol rol = Rol.values()[rolIndex];
        gestorPersonal.filtrarXRol(rol);
    }

    private void filtrarPorTurno() {
        System.out.println("Seleccione Turno para filtrar: ");
        for (Turno t : Turno.values()) {
            System.out.println(t.ordinal() + 1 + ". " + t);
        }
        int turnoIndex = Integer.parseInt(sc.nextLine()) - 1;
        Turno turno = Turno.values()[turnoIndex];
        gestorPersonal.filtrarXTurno(turno);
    }
}
