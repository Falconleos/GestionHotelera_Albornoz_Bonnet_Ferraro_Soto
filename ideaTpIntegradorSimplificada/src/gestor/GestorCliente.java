package gestor;


import clases.Cliente;
import clases.Habitacion;
import excepcions.ListaVaciaException;


public class GestorCliente extends Gestor <Cliente> {

    //Sin atributos

    //Sin Constructor

    //Sin Getters & Setters

    //Metodos de gestion de clientes
    //Buscar persona por DNI (metodo usado para eliminar persona,agregar comentario a cliente y modificar datos cliente. Devuelve el indice para modificar elemento desde la lista )

    @Override
    public int buscarIndicePorTexto(String dniABuscar) {
        for (int i = 0; i < lista.size(); i++) {
            Cliente personaBuscada = lista.get(i);
            if (personaBuscada.getDni().equalsIgnoreCase(dniABuscar)) {
                return i; // devuelve el índice donde se encontró
            }
        }
        return -1; // devuelve -1 si no se encontró
    }

    public boolean existeCliente(String apellido)throws ListaVaciaException {
        for(Cliente cliente : lista){
            if(cliente.getApellido().equalsIgnoreCase(apellido)){
                return true;
            }
        }
        return false;
    }

}


