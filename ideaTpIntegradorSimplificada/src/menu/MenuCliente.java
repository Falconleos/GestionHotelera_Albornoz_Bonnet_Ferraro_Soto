package menu;

import claseHotel.Hotel;
import clases.Cliente;
import excepcions.ListaVaciaException;
import excepcions.PersonaNoEncontradaException;
import gestor.GestorCliente;

import java.util.Scanner;

public class MenuCliente {

    //Metodos del menu
    //1. Visualizacion de las opciones del menu de clientes
    public static void mostrarMenu(Scanner sc, GestorCliente gestorCliente) {
        boolean salir = false;

        while (!salir) {
            Hotel.limpiarPantallaSubMenu();

            System.out.println("==============================================");
            System.out.println("                MENÚ CLIENTE  👤              ");
            System.out.println("==============================================");
            System.out.println(" 1. Agregar Cliente");
            System.out.println(" 2. Eliminar Cliente");
            System.out.println(" 3. Mostrar Todos los Clientes");
            System.out.println(" 4. Agregar Comentario a Cliente");
            System.out.println(" 5. Modificar Cliente");
            System.out.println("----------------------------------------------");
            System.out.println(" 0. Volver al Menú Principal");
            System.out.println("==============================================");
            System.out.print  (" Seleccione una opción: ");

            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    agregarCliente(sc, gestorCliente);
                    break;
                case 2:
                    eliminarCliente(sc, gestorCliente);
                    break;
                case 3:

                    try {
                        gestorCliente.mostrar();
                    }catch (ListaVaciaException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 4:
                    agregarComentario(sc, gestorCliente);
                    break;
                case 5:
                    modificarCliente(sc, gestorCliente);
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

    private static void agregarCliente(Scanner sc, GestorCliente gestorCliente) {
        System.out.print("DNI: ");
        String dni = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Celular: ");
        String celular = sc.nextLine();
        System.out.print("Ciudad de origen: ");
        String ciudadOrigen = sc.nextLine();

        Cliente cliente = new Cliente(dni, nombre, apellido, celular, ciudadOrigen);
        gestorCliente.agregar(cliente);
    }

    private static void eliminarCliente(Scanner sc, GestorCliente gestorCliente){
        try {
            System.out.print("Ingrese DNI del cliente a eliminar: ");
            String dni = sc.nextLine();

            int indiceElementoAEliminar = gestorCliente.buscarIndicePorTexto(dni);
            if (indiceElementoAEliminar < 0) throw new PersonaNoEncontradaException("Cliente no encontrado");
            if(gestorCliente.eliminar(gestorCliente.getLista().get(indiceElementoAEliminar))){
                System.out.println("Cliente eliminado con exito...");
            }


        }catch (PersonaNoEncontradaException e){
            System.out.println(e.getMessage());
        }
    }

    private static void agregarComentario(Scanner sc, GestorCliente gestorCliente) {
        try{
            System.out.print("Ingrese DNI del cliente: ");
            String dni = sc.nextLine();
            int indiceClienteAModificar = gestorCliente.buscarIndicePorTexto(dni);
            if (indiceClienteAModificar < 0) throw new PersonaNoEncontradaException("No se encontro cliente en la lista con el DNI ingresado");
            System.out.print("Ingrese comentario: ");
            String comentarioAAgregar = sc.nextLine();
            gestorCliente.getLista().get(indiceClienteAModificar).agregarComentario(comentarioAAgregar);
        }catch (PersonaNoEncontradaException e){
            System.out.println(e.getMessage());
        }
    }

    private static void modificarCliente(Scanner sc, GestorCliente gestorCliente)  {

        try {
            System.out.print("Ingrese DNI del cliente a modificar: ");
            String dni = sc.nextLine();
            int indiceClienteAModificar = gestorCliente.buscarIndicePorTexto(dni);

            if (indiceClienteAModificar < 0) throw new PersonaNoEncontradaException("No se encontro cliente en la lista con el DNI ingresado");

            System.out.println("Cliente encontrado: " + gestorCliente.getLista().get(indiceClienteAModificar));

            System.out.print("Nuevo nombre (" + gestorCliente.getLista().get(indiceClienteAModificar).getNombre() + "): ");
            String nombre = sc.nextLine();
            if (!nombre.isBlank()) gestorCliente.getLista().get(indiceClienteAModificar).setNombre(nombre);

            System.out.print("Nuevo apellido (" + gestorCliente.getLista().get(indiceClienteAModificar).getApellido() + "): ");
            String apellido = sc.nextLine();
            if (!apellido.isBlank()) gestorCliente.getLista().get(indiceClienteAModificar).setApellido(apellido);

            System.out.print("Nuevo celular (" + gestorCliente.getLista().get(indiceClienteAModificar).getCelular() + "): ");
            String celular = sc.nextLine();
            if (!celular.isBlank()) gestorCliente.getLista().get(indiceClienteAModificar).setCelular(celular);

            System.out.print("Nueva ciudad de origen (" + gestorCliente.getLista().get(indiceClienteAModificar).getCiudadOrigen() + "): ");
            String ciudadOrigen = sc.nextLine();
            if (!ciudadOrigen.isBlank()) gestorCliente.getLista().get(indiceClienteAModificar).setCiudadOrigen(ciudadOrigen);

            System.out.println("Cliente modificado correctamente: " + gestorCliente.getLista().get(indiceClienteAModificar));
        }catch (PersonaNoEncontradaException e){
            System.out.println(e.getMessage());
        }

    }
}

