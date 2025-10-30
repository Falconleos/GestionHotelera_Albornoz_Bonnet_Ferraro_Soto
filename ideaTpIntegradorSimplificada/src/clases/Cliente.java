package clases;

public class Cliente extends Persona{

    private String comentario;
    private String ciudadOrigen;

    public Cliente(String dni, String nombre, String apellido, String celular, String ciudadOrigen) {
        super(dni, nombre, apellido, celular);
        this.comentario = null;
        this.ciudadOrigen = ciudadOrigen;
    }

    public Cliente() {
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Cliente{" +
                "comentario='" + comentario + '\'' +
                ", ciudadOrigen='" + ciudadOrigen + '\'' +
                '}';
    }

    //Metodo de dudosa utilidad
    public void agregarComentario(String comentario){
        this.setComentario(comentario);
        System.out.println("comentario agregado a " + this.getNombre() + " " + this.getApellido());
    }

}
