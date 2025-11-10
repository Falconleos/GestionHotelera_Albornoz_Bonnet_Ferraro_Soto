import claseHotel.Hotel;
import excepcions.PersonaNoEncontradaException;
import manejoJSON.GestionJSONdeserializar;
import menu.MenuPrincipal;

public class App {

    public static void main(String[] args)  {
        Hotel hotel = new Hotel("Hotel por defecto","Ubicacion por defecto");

        //DESERIALIZACION AUTOMATICA
        GestionJSONdeserializar.cargarHotelDesdeJSON(hotel);

        MenuPrincipal menuPrincipal = new MenuPrincipal(hotel);
        menuPrincipal.mostrarMenu();
    }
}
