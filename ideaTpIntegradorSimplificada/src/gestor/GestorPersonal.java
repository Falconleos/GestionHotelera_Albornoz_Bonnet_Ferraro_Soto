package gestor;

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
