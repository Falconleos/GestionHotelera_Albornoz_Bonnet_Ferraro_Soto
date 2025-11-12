package gestor;

import clases.Cuenta;
import clases.Estadia;
import excepcions.ListaVaciaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorCuenta extends Gestor<Cuenta>{

    @Override
    public int buscarIndicePorTexto(String textoABuscar) {
        return 0;
    }

    private void validarListaVacia() throws ListaVaciaException {
        if (lista.isEmpty()) {
            throw new ListaVaciaException("Aún no hay cuentas generadas...");
        }
    }

    public Cuenta devorlverCuentaXestadia(Estadia estadia){
        for (Cuenta c : lista){
            if (c.getEstadia().equals(estadia)){
                return c;
            }
        }
        return null;
    }



    public List<Cuenta> cuentaPorHabitacion(int numHabitacion){
        validarListaVacia();
        List<Cuenta> cuentasEncontradas = new ArrayList<>();
        for(Cuenta cuenta: lista){
            if(cuenta.getEstadia().getNumHabitacion() == numHabitacion){
                cuentasEncontradas.add(cuenta);
            }
        }
        return  cuentasEncontradas;
    }

    public List<Cuenta> cuentaPorApellido(String apellido){
        validarListaVacia();
        List<Cuenta> cuentasEncontradas = new ArrayList<>();
        for(Cuenta cuenta: lista){
            if(cuenta.getEstadia().getCliente().getApellido().equalsIgnoreCase(apellido)){
                cuentasEncontradas.add(cuenta);
            }
        }
        return  cuentasEncontradas;
    }

    public List<Cuenta> cuentaSinAbonar(){
        validarListaVacia();
        List<Cuenta> cuentasEncontradas = new ArrayList<>();

        for(Cuenta cuenta: lista){
            if(!cuenta.isPago()){
                cuentasEncontradas.add(cuenta);
            }
        }
        return  cuentasEncontradas;
    }

    public List<Cuenta> cuentaAbonadas(){
        validarListaVacia();
        List<Cuenta> cuentasEncontradas = new ArrayList<>();

        for(Cuenta cuenta: lista){
            if(cuenta.isPago()){
                cuentasEncontradas.add(cuenta);
            }
        }
        return  cuentasEncontradas;
    }

    public List<Cuenta> cuentaCheckOutDelDia(){
        validarListaVacia();
        List<Cuenta> cuentasEncontradas = new ArrayList<>();

        for(Cuenta cuenta: lista){
            if(cuenta.getEstadia().getFechaCheckOut().equals(LocalDate.now())){
                cuentasEncontradas.add(cuenta);
            }
        }
        return  cuentasEncontradas;
    }

    public List<Cuenta> cuentaCheckOutPorDia(LocalDate dia){
        validarListaVacia();
        List<Cuenta> cuentasEncontradas = new ArrayList<>();

        for(Cuenta cuenta: lista){
            if(cuenta.getEstadia().getFechaCheckOut().equals(dia)){
                cuentasEncontradas.add(cuenta);
            }
        }
        return  cuentasEncontradas;
    }


}
