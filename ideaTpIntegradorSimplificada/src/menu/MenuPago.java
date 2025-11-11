package menu;

import claseHotel.Hotel;
import clases.Cuenta;
import clases.Estadia;
import clases.Pago;
import enums.TipoPago;
import excepcions.ElementoNuloException;
import excepcions.ListaVaciaException;
import gestor.*;

import java.util.List;
import java.util.Scanner;

public class MenuPago {

    public static void mostrarMenu(GestorEstadia gestorEstadia, GestorReserva gestorReserva,
                                   GestorCliente gestorCliente, GestorServicio gestorServicio,
                                   GestorHabitacion gestorHabitacion,GestorPago gestorPago, GestorCuenta gestorCuenta,Scanner sc) {
        boolean salir = false;

        while (!salir) {
            Hotel.limpiarPantallaSubMenu();

            System.out.println("==============================================");
            System.out.println("                 MENÚ PAGO  ✅                ");
            System.out.println("==============================================");
            System.out.println(" 1. Generar pago");
            System.out.println(" 2. Ver pagos");
            System.out.println(" 3. Buscar pago por apellido");
            System.out.println(" 4. Eliminar pago");
            System.out.println("----------------------------------------------");
            System.out.println(" 0. Volver al menú principal");
            System.out.println("==============================================");
            System.out.print  (" Seleccione una opción: ");

            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    generarPago(sc,gestorPago,gestorCuenta,gestorEstadia);
                    break;
                case 2:
                    mostrarPagos(gestorPago);
                    break;
                case 3:
                    buscarPagoPorApellido(sc,gestorPago);
                    break;
                case 4:
                    break;
                case 5:
                    eliminarPagoPorApellido(sc,gestorPago);
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

            if(numHabitacion<0){
                throw new NumberFormatException("Habitacion invalida.");
            }

            Estadia estadiaEncontrada = gestorEstadia.buscarEstadiaXHabitacion(numHabitacion);

            if(estadiaEncontrada == null){
                throw new ElementoNuloException("No existe estadia con ese numero de habitacion/Revisar numero de habitacion o estadias");
            }

            Cuenta cuentaSeleccionada = gestorCuenta.devorlverCuentaXestadia(estadiaEncontrada);
            System.out.println(cuentaSeleccionada);

            TipoPago tipoPago = null;

            boolean salir = false;
            while (!salir){

                System.out.println("Elija el tipo de pago o 0 para cancelar");
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
                        System.out.println("Efectivo");
                        break;
                    case 2:
                        tipoPago = TipoPago.DEBITO;
                        salir = true;
                        System.out.println("Debito");
                        break;
                    case 3:
                        tipoPago = TipoPago.CREDITO;
                        salir = true;
                        System.out.println("Credito");
                        break;
                    case 4:
                        tipoPago = TipoPago.TRANSFERENCIA;
                        salir = true;
                        System.out.println("Transferencia");
                        break;
                    case 0:
                        salir = true;
                        System.out.println("🔹 Volviendo al menú...");
                        return;
                    default:
                        System.out.println("⚠️ Opción incorrecta.");
                }
            }

            int descuento = -1;

            while (descuento <0){
                try {
                    System.out.println("Ingrese porcentaje de descuento o 0 Sin descuento");
                    descuento = Integer.parseInt(sc.nextLine());

                    if(descuento<0){
                        System.out.println("Ingrese un descuento valido o 0 sin descuento");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("⚠ Formato inválido. Ingrese un número.");
                }
            }

            double total = cuentaSeleccionada.getTotal() - ( cuentaSeleccionada.getTotal() * (descuento / 100.0) );

            Pago pagoConcretado = new Pago(cuentaSeleccionada,tipoPago,descuento,total);
            gestorPago.agregar(pagoConcretado);//descuento aplicado y pago generado

            cuentaSeleccionada.setPago(true); //cuenta calificada como abonada...

            System.out.println("Pago concretado: ");
            System.out.println(pagoConcretado);


        } catch (NumberFormatException e) {
            System.out.println("⚠ Formato inválido. Ingrese un número.");
        }catch (ElementoNuloException e){
            System.out.println(e.getMessage());
        }
    }

    public static void mostrarPagos(GestorPago gestorPago){
        try {
            List<Pago>pagosEncontrados = gestorPago.listarPagos();
            for(Pago p : pagosEncontrados){
                System.out.println(p);
            }
        }catch (ListaVaciaException e){
            System.out.println(e.getMessage());
        }
    }

    public static void buscarPagoPorApellido(Scanner sc, GestorPago gestorPago){
        try {

            if(gestorPago.getLista().isEmpty()){
                throw new ListaVaciaException("aun no hay pagos registrados...");
            }

            System.out.println("Ingrese el apellido:");
            String apellidoBuscar = sc.nextLine();

            List<Pago>pagosEncontrados = gestorPago.buscarPagoPorAapellido(apellidoBuscar);

            if(pagosEncontrados.isEmpty()){
                System.out.println("Aun no hay pagos registrados de " + apellidoBuscar);
            }else {
                System.out.println("Pagos encontrados de " + apellidoBuscar);
                for(Pago p : pagosEncontrados){
                    System.out.println(p);
                }
            }
        }catch (ListaVaciaException e){
            System.out.println(e.getMessage());
        }
    }

    public static void eliminarPagoPorApellido(Scanner sc,GestorPago gestorPago){

        try {

            if(gestorPago.getLista().isEmpty()){
                throw new ListaVaciaException("Aun no hay pagos registrados...");
            }

            System.out.println("Ingrese el apellido del pago a eliminar:");
            String apellido = sc.nextLine();

            List<Pago>pagosEncontrados = gestorPago.buscarPagoPorAapellido(apellido);

            if(pagosEncontrados.isEmpty()){
                System.out.println("Aun no hay pagos registrados de " + apellido);
            }else {
                System.out.println("Pagos encontrados de " + apellido);
                for(Pago p : pagosEncontrados){
                    System.out.println(p);
                }

                System.out.println("Seleccione el id del pago a eliminar");
                int idSeleccionado = Integer.parseInt(sc.nextLine());

                Pago pagoEliminar = gestorPago.elegirPagoPorId(pagosEncontrados,idSeleccionado);

                if(pagoEliminar == null){
                    System.out.println("Id inexistente...");
                }else {
                    gestorPago.eliminar(pagoEliminar);
                    System.out.println("Pago de " +apellido+ " eliminado correctamente...");
                }

            }

        }catch (ListaVaciaException e){
            System.out.println(e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("id invalido...");
        }

    }


}
