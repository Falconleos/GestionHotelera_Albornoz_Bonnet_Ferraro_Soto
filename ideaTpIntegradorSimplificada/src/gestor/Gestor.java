package gestor;

import excepcions.ListaVaciaException;

import java.util.ArrayList;
import java.util.List;

public abstract class Gestor <T>{

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
            throw new ListaVaciaException("Aun no hay elementos que mostrar...");
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

    //Buscar elemento por texto (metodo usado para eliminar persona,agregar comentario a cliente y modificar datos cliente. Devuelve el indice para modificar elemento desde la lista )
    public abstract int buscarIndicePorTexto(String textoABuscar);


}
