package menu;

import claseHotel.Hotel;
import clases.Servicio;
import excepcions.ElementoNuloException;
import excepcions.ListaVaciaException;
import excepcions.PrecioInvalidoException;
import gestor.*;

import java.util.Scanner;

public class MenuServicio {

    public static void mostrarMenu(GestorEstadia gestorEstadia, GestorReserva gestorReserva, GestorCliente gestorCliente, GestorServicio gestorServicio, GestorHabitacion gestorHabitacion, Scanner sc){

        boolean continuar = true;
        while (continuar) {
            Hotel.limpiarPantallaSubMenu();
            System.out.println("Gestion Servicios");
            System.out.println("1.Generar Servicio");
            System.out.println("2.Ver Servicios");
            System.out.println("3.Modificar Servicio");
            System.out.println("4.Modificar valor Servicio");
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
                    generarServicio(sc, gestorServicio);
                    break;
                case 2:

                    try {
                        gestorServicio.mostrarServicios();
                    }catch (ListaVaciaException e){
                        System.out.println("Aun no hay servicios cargados...");
                    }

                    break;
                case 3:
                    modificarServicio(sc,gestorServicio);
                    break;
                case 4:
                    modificarValorServicio(sc,gestorServicio);
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

    public static void generarServicio(Scanner sc,GestorServicio gestorServicio){

        System.out.println("Ingrese la descripcion:");
        String descripcion = sc.nextLine();

        System.out.println("Ingrese el precio");
        double precio = 0.0;

        try {
            precio = Double.parseDouble(sc.nextLine());
            if(precio < 0){
                throw new  PrecioInvalidoException("Precio invalido.");
            }
        }catch (NumberFormatException e){
            System.out.println("Formato de precio invalido");
        }catch (PrecioInvalidoException e){
            System.out.println(e.getMessage());
        }

        if(precio > 0){

            ;gestorServicio.agregar(new Servicio(descripcion,precio));
            System.out.println("Servicio generado correctamente.");

        }else{
            System.out.println("Servicio no generado por precio invalido...");
        }
    }

    public static void modificarServicio(Scanner sc,GestorServicio gestorServicio){
        try {
            System.out.println("Modificar Servicio");
            gestorServicio.mostrarServicios();
            System.out.println("Ingrese el id del servicio a MODIFICAR:");
            int id = -1;

            id = Integer.parseInt(sc.nextLine());

            Servicio servicioEncontrado = gestorServicio.obtenerPorId(id);

            if(servicioEncontrado == null){
                throw new ElementoNuloException("Servicio no encontrado");
            }

            System.out.println("Ingrese nueva descripcion:");
            String descripcion = sc.nextLine();

            System.out.println("Ingrese nuevo precio:");
            double precio = Double.parseDouble(sc.nextLine());

            if(precio < 0){
                throw new  PrecioInvalidoException("Precio invalido.");
            }

            gestorServicio.modificarServicio(servicioEncontrado,descripcion,precio);

        }catch (ListaVaciaException e){
            System.out.println(e.getMessage());
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }catch (ElementoNuloException e){
            System.out.println(e.getMessage());
        }catch (PrecioInvalidoException e){
            System.out.println(e.getMessage());
        }
    }

    public static void modificarValorServicio(Scanner sc,GestorServicio gestorServicio){
        try {
            System.out.println("Modificar valor de Servicio");
            gestorServicio.mostrarServicios();
            System.out.println("Ingrese el id del servicio a MODIFICAR:");
            int id = -1;

            id = Integer.parseInt(sc.nextLine());

            Servicio servicioEncontrado = gestorServicio.obtenerPorId(id);

            if(servicioEncontrado == null){
                throw new ElementoNuloException("Servicio no encontrado");
            }

            System.out.println("Ingrese nuevo precio:");
            double precio = Double.parseDouble(sc.nextLine());

            if(precio < 0){
                throw new  PrecioInvalidoException("Precio invalido.");
            }

            gestorServicio.modificarValorServicio(servicioEncontrado,precio);

        }catch (ListaVaciaException e){
            System.out.println(e.getMessage());
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }catch (ElementoNuloException e){
            System.out.println(e.getMessage());
        }catch (PrecioInvalidoException e){
            System.out.println(e.getMessage());
        }
    }
}
