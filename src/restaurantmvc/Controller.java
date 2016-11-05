package restaurantmvc;

import java.util.Scanner;

/**
 *
 * @author isaac
 */
public class Controller {

    static Scanner scanner = new Scanner(System.in);

    public Controller() {

        //inici();
        mainMenu();
        View.pinta("La aplicació ha finalitzat.");
    }

    public static void mainMenu() {
        int select = -1;

        do {
            View.render_home();
            select = Integer.parseInt(scanner.nextLine());

            switch (select) {
                case 1:
                    consultesMenu();
                    break;
                case 5:
                    DemanarMenjarController.demanar_menjar();
                case 0:
                    break;
            }

        } while (select != 0);
        
    }

    public static void consultesMenu() {
        int select = -1;
        do {
            View.render_submenu_consultes();
            select = Integer.parseInt(scanner.nextLine());
            switch (select) {
                case 1:
                    ConsultesController.mostrar_plats();
                    break;
                case 2:
                    ConsultesController.mostrar_encarregats();
                    break;
                case 3:
                    ConsultesController.mostrar_ingredients();
                    break;
                case 4:
                    ConsultesController.mostrar_plats_per_categoria();
                    break;
                case 5:
                    ConsultesController.mostrar_plats_per_ingredient();
                    break;
                case 0:
                    break;
            }
        } while (select != 0);
        
    }

    public static void inici() {
        int select = -1;

        while (select != 0) {
            try {

                switch (select) {
                    case 1: //DEMANAR MENJAR.
                        DemanarMenjarController.demanar_menjar();
                        select = DemanarMenjarController.vols_reiniciar();
                        break;
                    case 2:

                        break;
                    case 3: //MOSTRAR ENCARREGATS
                        ConsultesController.mostrar_encarregats();
                        select = DemanarMenjarController.vols_reiniciar();
                        break;
                    case 4:
                        submenu_consultes();
                        select = DemanarMenjarController.vols_reiniciar();
                        break;
                }

                if (select == 0) {
                    break;
                }

                View.render_home();
                select = Integer.parseInt(scanner.nextLine());

            } catch (Exception e) {
                System.out.println("Uoop! Error!");
            }
        }
        View.pinta("Adeu!");
    }

    public static void submenu_consultes() {

        int select = -1;

        while (select != 0) {
            try {

                switch (select) {
                    case 1: //DEMANAR MENJAR.
                        DemanarMenjarController.demanar_menjar();
                        select = DemanarMenjarController.vols_reiniciar();
                        break;
                    case 2:

                        break;
                    case 3: //MOSTRAR ENCARREGATS
                        ConsultesController.mostrar_encarregats();
                        select = DemanarMenjarController.vols_reiniciar();
                        break;
                    case 4:

                        break;
                }

                if (select == 0) {
                    inici();
                }

                View.render_submenu_consultes();
                select = Integer.parseInt(scanner.nextLine());

            } catch (Exception e) {
                System.out.println("Uoop! Error!");
            }
        }
    }

}
