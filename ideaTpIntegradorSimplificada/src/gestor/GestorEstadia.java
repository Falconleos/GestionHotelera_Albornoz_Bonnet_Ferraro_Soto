package gestor;

import clases.Estadia;
import clases.Servicio;
import excepcions.ListaVaciaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorEstadia extends Gestor<Estadia>{

    @Override
    public int buscarIndicePorTexto(String textoABuscar) {
        return 0;
    }

    private void validarListaVacia() throws ListaVaciaException {
        if (lista.isEmpty()) {
            throw new ListaVaciaException("Aún no hay estadias cargadas...");
        }
    }

    public Estadia buscarEstadiaXHabitacion(int numHabitacion) {
        validarListaVacia();

        for (Estadia estadia : lista) {
            if (estadia.getNumHabitacion() == numHabitacion &&
                    (estadia.getFechaCheckIn().isEqual(LocalDate.now()) ||
                            (estadia.getFechaCheckIn().isBefore(LocalDate.now()) && estadia.getFechaCheckOut().equals(LocalDate.now())) ||
                                estadia.getFechaCheckOut().isAfter(LocalDate.now())
                    )
            ){
                return estadia;
            }
        }
        return null;
    }


    public List<Estadia>buscarEstadiaPorApellido(String apellido){
        validarListaVacia();
        List<Estadia>estadiasEncontradas = new ArrayList<>();
        LocalDate hoy = LocalDate.now();
        for(Estadia e : lista){
            if(e.getCliente().getApellido().equalsIgnoreCase(apellido)
                    &&   (hoy.isEqual(e.getFechaCheckIn()) ||
                    hoy.isEqual(e.getFechaCheckOut()) ||
                    (hoy.isAfter(e.getFechaCheckIn()) && hoy.isBefore(e.getFechaCheckOut())))
            ){
                estadiasEncontradas.add(e);
            }
        }
        return estadiasEncontradas;
    }

    public void agregarServicioEstadia(Servicio servicio,Estadia estadia){

        List<Servicio>listaServiciosEstadia = estadia.getListaServicios();
        listaServiciosEstadia.add(servicio);

        estadia.setListaServicios(listaServiciosEstadia);
        System.out.println("Servicio agregado correctamente...");
    }



}
