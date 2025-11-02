package gestor;

import clases.Cliente;
import clases.Personal;
import enums.Rol;
import enums.Turno;

import java.util.ArrayList;
import java.util.List;

public class GestorPersonal extends Gestor <Personal>{
    //Sin atributos
    //Sin constructores
    //Sin getters & setters

    //Metodos de gestion del personal
    //Buscar persona por DNI (metodo usado para eliminar persona,agregar comentario a cliente y modificar datos cliente. Devuelve el indice para modificar elemento desde la lista )
    //Metodo buscarIndicePorDNI es copia del GestorCliente
    @Override
    public int buscarIndicePorTexto(String dniABuscar) {
        for (int i = 0; i < lista.size(); i++) {
            Personal personaBuscada = lista.get(i);
            if (personaBuscada.getDni().equalsIgnoreCase(dniABuscar)) {
                return i; // devuelve el índice donde se encontró
            }
        }
        return -1; // devuelve -1 si no se encontró
    }
    //Filtrar por rol
    public List<Personal> filtrarXRol(Rol rol) {
        List <Personal> listaFiltrada = new ArrayList<>();
        for (Personal p : lista) {
            if (p.getRol() == rol) {
                listaFiltrada.add(p);
            }
        }
        return listaFiltrada;
    }
    //Filtrar por Turno
    public List<Personal> filtrarXTurno(Turno turno) {
        List <Personal> listaFiltrada = new ArrayList<>();
        for (Personal p : lista) {
            if (p.getTurno() == turno) {
                listaFiltrada.add(p);
            }
        }
        return listaFiltrada;
    }




}
