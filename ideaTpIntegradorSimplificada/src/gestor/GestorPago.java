package gestor;

import clases.Pago;
import excepcions.ListaVaciaException;

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

    public static void generarPago(GestorPago gestorPago){

    }

}
