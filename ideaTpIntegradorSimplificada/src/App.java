import claseHotel.Hotel;
import menu.MenuPrincipal;

public class App {

    public static void main(String[] args) {

        Hotel hotel = new Hotel("Utn Hotel","Av. de los Pescadores");

        MenuPrincipal menuPrincipal = new MenuPrincipal(hotel);

        menuPrincipal.mostrarMenu();


    }

}
