package menu;

import claseHotel.Hotel;
import clases.Cuenta;
import clases.Estadia;
import clases.Pago;
import enums.TipoPago;
import excepcions.ElementoNuloException;
import gestor.*;

import java.util.Scanner;

public class MenuPago {

    public static void mostrarMenu(GestorEstadia gestorEstadia, GestorReserva gestorReserva,
                                   GestorCliente gestorCliente, GestorServicio gestorServicio,
                                   GestorHabitacion gestorHabitacion,GestorPago gestorPago, GestorCuenta gestorCuenta,Scanner sc) {
        boolean salir = false;

        while (!salir) {
            Hotel.limpiarPantallaSubMenu();
            System.out.println("\n--- MENÚ PAGO ---");
            System.out.println("1. Generar Pago");
            System.out.println("2. Ver pagos");
            System.out.println("3. Buscar pago por Apellido");
            System.out.println("4. eliminar Pago");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    generarPago(sc,gestorPago,gestorCuenta,gestorEstadia);
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

    public static void generarPago(Scanner sc,GestorPago gestorPago,GestorCuenta gestorCuenta,GestorEstadia gestorEstadia){

        try {
            System.out.println("Ingrese habitacion: ");
            int numHabitacion = Integer.parseInt(sc.nextLine());

            Estadia estadiaEncontrada = gestorEstadia.buscarEstadiaXHabitacion(numHabitacion);

            if(estadiaEncontrada == null){
                throw new ElementoNuloException("No existe estadia en esa habitacion...");
            }
            Cuenta cuentaSeleccionada = null;

            for(Cuenta c : gestorCuenta.getLista()){
                if(c.getEstadia().equals(estadiaEncontrada)){
                    cuentaSeleccionada = c;
                }
            }

            TipoPago tipoPago = null;

            boolean salir = false;
            while (!salir){

                System.out.println("Elija el tipo de pago:");
                System.out.println("1.Efectivo");
                System.out.println("2.Debito");
                System.out.println("3.Credito");
                System.out.println("4.Transferencia");

                int opcion = -1;

                try {
                    opcion = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("⚠ Formato inválido. Ingrese un número.");
                    continue;
                }

                switch (opcion) {
                    case 1:
                        tipoPago = TipoPago.EFECTIVO;
                        salir = true;
                        break;
                    case 2:
                        tipoPago = TipoPago.DEBITO;
                        salir = true;
                        break;
                    case 3:
                        tipoPago = TipoPago.CREDITO;
                        salir = true;
                        break;
                    case 4:
                        tipoPago = TipoPago.TRANSFERENCIA;
                        salir = true;
                        break;
                    case 0:
                        salir = true;
                        System.out.println("🔹 Volviendo al menú...");
                        break;
                    default:
                        System.out.println("⚠️ Opción incorrecta.");
                }
            }

            System.out.println("Ingrese porcentaje de descuento:");
            int descuento = 0;

            try {
                descuento = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Formato inválido. Ingrese un número.");
            }

            gestorPago.agregar(new Pago(cuentaSeleccionada,tipoPago,descuento));


        } catch (NumberFormatException e) {
            System.out.println("⚠ Formato inválido. Ingrese un número.");
        }catch (ElementoNuloException e){
            System.out.println(e.getMessage());
        }

    }

}
