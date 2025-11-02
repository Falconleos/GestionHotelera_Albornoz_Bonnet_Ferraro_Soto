package gestor;

import clases.Servicio;

public class GestorServicio extends Gestor<Servicio>{

    public Servicio buscarServicioPorId(int idServicio){
        for(Servicio s : lista){

            if(idServicio == s.getIdServicio()){
                return s;
            }
        }
        return null;
    }

    //Implementacion metodo abstracto


    @Override
    public int buscarIndicePorTexto(String textoABuscar) {
        return 0;
    }
}
