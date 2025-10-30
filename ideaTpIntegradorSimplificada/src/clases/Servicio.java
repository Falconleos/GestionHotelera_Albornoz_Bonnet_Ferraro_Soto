package clases;

public class Servicio {

    private static int contador = 1;
    private int idServicio;
    private String detalle;
    private double precio;

    public Servicio(String detalle, double precio) {
        this.idServicio = contador++;
        this.detalle = detalle;
        this.precio = precio;
    }

    public Servicio() {
    }

    public int getIdServicio() {
        return idServicio;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "idServicio=" + idServicio +
                ", detalle='" + detalle + '\'' +
                ", precio=" + precio +
                '}';
    }
}
