package gestor;

import clases.Servicio;
import excepcions.ListaVaciaException;

import java.util.List;

public class GestorServicio extends Gestor<Servicio>{

    private void validarListaVacia() throws ListaVaciaException {
        if (lista.isEmpty()) {
            throw new ListaVaciaException("Aún no hay servicios cargados...");
        }
    }

    public Servicio buscarServicioPorId(int idServicio){
        validarListaVacia();
        for(Servicio s : lista){

            if(idServicio == s.getIdServicio()){
                return s;
            }
        }
        return null;
    }


    public void mostrarServicios(){
        validarListaVacia();
        System.out.println("Servicios:");
        for(Servicio s : lista){
            System.out.println(s);
        }
    }

    public Servicio obtenerPorId(int idServicio){
        validarListaVacia();
        for(Servicio s : lista){
            if(idServicio == s.getIdServicio()){
                return s;
            }
        }
        return null;
    }

    public void modificarServicio(Servicio servicio,String detalle,double precio){
        validarListaVacia();
        servicio.setDetalle(detalle);
        servicio.setPrecio(precio);
        System.out.println("Servicio modificado");
    }

    public void modificarValorServicio(Servicio servicio,double precio){
        validarListaVacia();
        servicio.setPrecio(precio);
        System.out.println("Precio de servicio modificado");
    }

    public static double calcularTotal(List<Servicio> servicios){
        double total = 0;
        for(Servicio s : servicios){
            total += s.getPrecio();
        }
        return total;
    }



    //Implementacion metodo abstracto


    @Override
    public int buscarIndicePorTexto(String textoABuscar) {
        return 0;
    }
}
