package clases;

import enums.Rol;
import enums.Turno;

public class Personal extends Persona{

    private Rol rol;
    private Turno turno;

    public Personal(String dni, String nombre, String apellido, String celular, Rol rol, Turno turno) {
        super(dni, nombre, apellido, celular);
        this.rol = rol;
        this.turno = turno;
    }

    public Personal() {
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "=== Personal ===\n" +
                "• Rol: " + rol + "\n" +
                "• Turno: " + turno + "\n";
    }
}

