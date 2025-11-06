package clases;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona{

    private List<String> comentarios;
    private String ciudadOrigen;

    public Cliente(String dni, String nombre, String apellido, String celular, String ciudadOrigen) {
        super(dni, nombre, apellido, celular);
        this.comentarios = new ArrayList<>();
        this.ciudadOrigen = ciudadOrigen;
    }

    public Cliente() {
    }

    public List<String> getComentario() {
        return comentarios;
    }

    public void setComentario(List<String> comentarios) {
        this.comentarios = comentarios;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "=== Datos del Cliente ===\n" +
                "• Comentarios: " + comentarios + "\n" +
                "• Ciudad de origen: " + ciudadOrigen + "\n";
    }

    //Metodo de dudosa utilidad
    public void agregarComentario(String comentarioAAgregar){
        comentarios.add(comentarioAAgregar);
        System.out.println("comentario agregado a " + this.getNombre() + " " + this.getApellido());
    }

}
