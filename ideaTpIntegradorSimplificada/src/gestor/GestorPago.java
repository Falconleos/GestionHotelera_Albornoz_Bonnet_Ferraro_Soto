package gestor;

import clases.Cuenta;
import clases.Pago;
import excepcions.ListaVaciaException;

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



}
