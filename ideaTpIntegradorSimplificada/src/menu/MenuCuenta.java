package menu;

import claseHotel.Hotel;
import clases.Cuenta;
import excepcions.ListaVaciaException;
import excepcions.NumeroHabitacionInvalidoException;
import gestor.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuCuenta {

    public static void mostrarMenu(GestorEstadia gestorEstadia, GestorReserva gestorReserva,
                                   GestorCliente gestorCliente, GestorServicio gestorServicio,
                                   GestorHabitacion gestorHabitacion, GestorCuenta gestorCuenta,Scanner sc) {
        boolean salir = false;

        while (!salir) {
            Hotel.limpiarPantallaSubMenu();
            System.out.println("\n--- MENÚ CUENTA ---");
            System.out.println("1. Mostrar cuentas");
            System.out.println("2. Mostrar cuenta por habitacion");
            System.out.println("3. Mostrar cuenta por apellido");
            System.out.println("4. Mostrar cuentas sin abonar");
            System.out.println("5. Mostrar cuentas abonadas");
            System.out.println("6. Mostrar cuentas con check out del día");
            System.out.println("7. Mostrar cuentas con check out de un dia especifico");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = -1;

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Formato inválido. Ingrese un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    mostrarCuentas(gestorCuenta);
                    break;
                case 2:
                    mostrarCuentasHabitacion(sc,gestorCuenta);
                    break;
                case 3:
                    mostrarCuentasApellido(sc,gestorCuenta);
                    break;
                case 4:
                    mostrarCuentasSinAbonar(gestorCuenta);
                    break;
                case 5:
                    mostrarCuentasAbonadas(gestorCuenta);
                    break;
                case 6:
                    cuentasConCheckOutDelDia(gestorCuenta);
                    break;
                case 7:
                    cuentasConCheckOutDeDiaEspecifico(sc,gestorCuenta);
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

    public static void mostrarCuentas(GestorCuenta gestorCuenta){
        if(gestorCuenta.getLista().isEmpty()){
            System.out.println("Aun no hay cuentas generadas...");
        }else{

            gestorCuenta.mostrar();
        }
    }

    public static void mostrarCuentasHabitacion(Scanner sc,GestorCuenta gestorCuenta){

        if(gestorCuenta.getLista().isEmpty()){
            System.out.println("Aun no hay cuentas generadas...");
        }else{

            try {

                System.out.println("Ingrese el numero de habitacion:");
                int numHabitacion = Integer.parseInt(sc.nextLine());

                if(numHabitacion<0){
                    throw new NumeroHabitacionInvalidoException("Numero de habitacion invalido...");
                }

                List<Cuenta>cuentasEncontradas = gestorCuenta.cuentaPorHabitacion(numHabitacion);
                if(!cuentasEncontradas.isEmpty()){
                    for(Cuenta c : cuentasEncontradas){
                        System.out.println(c);
                    }
                }else{
                    System.out.println("no hay cuentas en esa habitacion...");
                }

            }catch (NumberFormatException e){
                System.out.println("se espera un numero valido...");
            }catch (NumeroHabitacionInvalidoException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void mostrarCuentasApellido(Scanner sc,GestorCuenta gestorCuenta){

        if(gestorCuenta.getLista().isEmpty()){
            System.out.println("Aun no hay cuentas generadas...");
        }else{

            try {
                System.out.println("Ingrese el apellido:");
                String apellido = sc.nextLine();

                List<Cuenta>cuentasEncontradas = gestorCuenta.cuentaPorApellido(apellido);
                if(!cuentasEncontradas.isEmpty()){
                    for(Cuenta c : cuentasEncontradas){
                        System.out.println(c);
                    }
                }else{
                    System.out.println("No hay cuentas con el apellido: " + apellido);
                }

            }catch (NumberFormatException e){
                System.out.println("se espera un numero valido...");
            }catch (NumeroHabitacionInvalidoException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void mostrarCuentasSinAbonar(GestorCuenta gestorCuenta){

        if(gestorCuenta.getLista().isEmpty()){
            System.out.println("Aun no hay cuentas generadas...");
        }else{
            List<Cuenta>cuentasEncontradas = gestorCuenta.cuentaSinAbonar();

            if(cuentasEncontradas.isEmpty()){
                System.out.println("No hay cuentas sin abonar");
            }else{
                System.out.println("Cuentas sin abonar:");
                for(Cuenta c : cuentasEncontradas){
                    System.out.println(c);
                }
            }
        }
    }

    public static void mostrarCuentasAbonadas(GestorCuenta gestorCuenta){

        if(gestorCuenta.getLista().isEmpty()){
            System.out.println("Aun no hay cuentas generadas...");
        }else{
            List<Cuenta>cuentasEncontradas = gestorCuenta.cuentaAbonadas();

            if(cuentasEncontradas.isEmpty()){
                System.out.println("No hay cuentas abonadas");
            }else{
                System.out.println("Cuentas abonadas:");
                for(Cuenta c : cuentasEncontradas){
                    System.out.println(c);
                }
            }
        }
    }

    public static void cuentasConCheckOutDelDia(GestorCuenta gestorCuenta){

        if(gestorCuenta.getLista().isEmpty()){
            System.out.println("Aun no hay cuentas generadas...");
        }else{

            List<Cuenta>cuentasEncontradas = gestorCuenta.cuentaCheckOutDelDia();

            if(cuentasEncontradas.isEmpty()){
                System.out.println("No hay cuentas con check out del dia");
            }else{
                System.out.println("cuentas con check out del dia:");
                for(Cuenta c : cuentasEncontradas){
                    System.out.println(c);
                }
            }
        }

    }

    public static void cuentasConCheckOutDeDiaEspecifico(Scanner sc,GestorCuenta gestorCuenta){

        if(gestorCuenta.getLista().isEmpty()){
            System.out.println("Aun no hay cuentas generadas...");
        }else{

            try {

                System.out.println("Ingrese el dia especifico (yyyy-mm-dd):");
                LocalDate dia = LocalDate.parse(sc.nextLine());

                List<Cuenta>cuentasEncontradas=gestorCuenta.cuentaCheckOutPorDia(dia);

                if(cuentasEncontradas.isEmpty()){
                    System.out.println("No hay cuentas con check out del dia: " + dia);
                }else{
                    System.out.println("Cuentas con checkout del dia " + dia + ": ");
                    for(Cuenta c:cuentasEncontradas){
                        System.out.println(c);
                    }
                }

            }catch (DateTimeException e){
                System.out.println("Fecha invalida...");
            }

        }

    }

}
