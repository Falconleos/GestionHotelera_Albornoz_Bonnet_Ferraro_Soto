package clases;

public class Habitacion implements Comparable<Habitacion> {
    /*
    * numero
    * tipo
    * capacidadMaxima
    * precio
    * descripcion
    * */
    private static int contador = 1;
    private int idHabitacion;
    private int numero;
    private String tipo;
    private int capacidadMaxima;
    private double precio;
    private String descripcion;

    public Habitacion(int numero, String tipo, int capacidadMaxima, double precio, String descripcion) {
        this.idHabitacion = contador++;
        this.numero = numero;
        this.tipo = tipo;
        this.capacidadMaxima = capacidadMaxima;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Habitacion() {
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "=== Habitación ===\n" +
                "• ID: " + idHabitacion + "\n" +
                "• Número: " + numero + "\n" +
                "• Tipo: " + tipo + "\n" +
                "• Capacidad máxima: " + capacidadMaxima + "\n" +
                "• Precio: " + precio + "\n" +
                "• Descripción: " + descripcion + "\n";
    }

    @Override
    public int compareTo(Habitacion o) {
        return Integer.compare(capacidadMaxima,o.getCapacidadMaxima());
    }
}
