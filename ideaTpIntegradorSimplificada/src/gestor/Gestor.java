package gestor;

import clases.Habitacion;
import excepcions.ListaVaciaException;

import java.util.ArrayList;
import java.util.List;

public class Gestor <T>{

    protected List<T>lista;

    public Gestor() {
        this.lista = new ArrayList<>();
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }

    //------metodos generales--------//

    public boolean agregar(T t){
        return lista.add(t);
    }

    public boolean eliminar(T t){
        return lista.remove(t);
    }

    public void mostrar()throws ListaVaciaException {
        if(lista.isEmpty()){
            String tipo = lista.getClass().getSimpleName();
            throw new ListaVaciaException("Aun no hay elementos de tipo " + tipo + " almacenados en la lista...");
        }
        for(T t : lista){
            System.out.println(t);
        }
    }

    public T buscarElemento(T t){
        if(lista.isEmpty()){
            String tipo = lista.getClass().getSimpleName();
            throw new ListaVaciaException("Aun no hay elementos de tipo " + tipo + " almacenados en la lista...");
        }
        return t;
    }

    //-----------Metodos exclusivos de las clases----------------//

}
