package gestor;

import clases.Cuenta;
import clases.Pago;
import excepcions.ListaVaciaException;

import java.util.ArrayList;
import java.util.List;

public class GestorPago extends Gestor<Pago>{

    @Override
    public int buscarIndicePorTexto(String textoABuscar) {
        return 0;
    }

    private void validarListaVacia() throws ListaVaciaException {
        if (lista.isEmpty()) {
            throw new ListaVaciaException("Aún no hay pagos generados...");
        }
    }

    public List<Pago> listarPagos()throws ListaVaciaException {
        validarListaVacia();
        return lista;
    }

    public Pago buscarPagoPorCuenta(Cuenta cuenta){
        validarListaVacia();
        for(Pago p : lista){
            if(p.getCuenta().equals(cuenta)){
                return p;
            }
        }
        return null;
    }

    public List<Pago> buscarPagoPorAapellido(String apellido)throws ListaVaciaException{
        validarListaVacia();
        List<Pago>encontrados = new ArrayList<>();
        for(Pago p : lista){
            if(p.getApellido().equalsIgnoreCase(apellido)){
                encontrados.add(p);
            }
        }
        return encontrados;
    }

    public Pago elegirPagoPorId(List<Pago>lista,int idPago)throws ListaVaciaException{
        if(listarPagos().isEmpty()){
            throw new ListaVaciaException("No hay pagos seleccionados...");
        }
        for(Pago p : listarPagos()){
            if(p.getIdPago() == idPago){
                return p;
            }
        }
        return null;
    }







}
