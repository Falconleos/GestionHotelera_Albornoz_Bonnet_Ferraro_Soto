package clases;

import java.time.LocalDate;


public abstract class Persona {

    private static int contador = 1;
    private int idPersona;
    private String dni;
    private String nombre;
    private String apellido;
    private String celular;
    private LocalDate fechaAlta;

    public Persona(String dni, String nombre, String apellido, String celular) {
        this.idPersona = contador++;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.fechaAlta = LocalDate.now();
    }

    public Persona() {
    }

    public int getIdPersona() {
        return idPersona;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString() {
        return  "=== Persona ===\n" +
                "• ID: " + idPersona + "\n" +
                "• DNI: " + dni + "\n" +
                "• Nombre: " + nombre + "\n" +
                "• Apellido: " + apellido + "\n" +
                "• Celular: " + celular + "\n" +
                "• Fecha de alta: " + fechaAlta + "\n";
    }
}

