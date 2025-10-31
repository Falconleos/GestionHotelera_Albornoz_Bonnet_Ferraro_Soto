package menu;

import clases.Cliente;
import clases.Persona;
import gestor.GestorCliente;
import gestor.GestorPersonal;

import java.util.Scanner;

public class MenuCliente {

    //Atributos
    private GestorPersonas<Cliente> gestorCliente;
    private Scanner sc;

    //Constructor
    public MenuCliente(GestorPersonas<Cliente> gestorCliente) {
        this.gestorCliente = gestorCliente;
        this.sc = new Scanner(System.in);
    }

    //Metodos del menu

    //1. Visualizacion de las opciones del menu de clientes
    public void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== MENÚ CLIENTE =====");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Eliminar Cliente");
            System.out.println("3. Mostrar Todos los Clientes");
            System.out.println("4. Agregar Comentario a Cliente");
            System.out.println("5. Modificar Cliente");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    agregarCliente();
                    break;
                case 2:
                    eliminarCliente();
                    break;
                case 3:
                    gestorCliente.mostrarPersonas();
                    break;
                case 4:
                    agregarComentario();
                    break;
                case 5:
                    modificarCliente();
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

    private void agregarCliente() {
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

        Cliente cliente = new Cliente(dni, nombre, apellido, celular, null, ciudadOrigen);
        gestorCliente.agregarPersona(cliente);
    }

    private void eliminarCliente() {
        System.out.print("Ingrese DNI del cliente a eliminar: ");
        String dni = sc.nextLine();
        gestorCliente.eliminarPersonaPorDni(dni);
    }

    private void agregarComentario() {
        System.out.print("Ingrese DNI del cliente: ");
        String dni = sc.nextLine();
        int indiceClienteAModificar = gestorCliente.buscarIndicePorDni(dni);
        if (indiceClienteAModificar < 0) throw new PersonaNulaException("No se encontro persona en la lista con el DNI ingresado");
        System.out.print("Ingrese comentario: ");
        String comentario = sc.nextLine();
        gestorCliente.getListaPersonas().get(indiceClienteAModificar).setComentario(comentario);
    }

    private void modificarCliente() {
        System.out.print("Ingrese DNI del cliente a modificar: ");
        String dni = sc.nextLine();
        Persona cliente = gestorCliente.buscarPorDni(dni);

        if (cliente == null) {
            System.out.println("⚠️ No se encontró cliente con DNI: " + dni);
            return;
        }

        System.out.println("Cliente encontrado: " + cliente);

        System.out.print("Nuevo nombre (" + cliente.getNombre() + "): ");
        String nombre = sc.nextLine();
        if (!nombre.isBlank()) cliente.setNombre(nombre);

        System.out.print("Nuevo apellido (" + cliente.getApellido() + "): ");
        String apellido = sc.nextLine();
        if (!apellido.isBlank()) cliente.setApellido(apellido);

        System.out.print("Nuevo celular (" + cliente.getCelular() + "): ");
        String celular = sc.nextLine();
        if (!celular.isBlank()) cliente.setCelular(celular);

        System.out.print("Nueva ciudad de origen (" + cliente.getCiudadOrigen() + "): ");
        String ciudadOrigen = sc.nextLine();
        if (!ciudadOrigen.isBlank()) cliente.setCiudadOrigen(ciudadOrigen);

        System.out.println("✅ Cliente modificado correctamente: " + cliente);
    }
}

