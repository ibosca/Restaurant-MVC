package restaurantmvc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author isaac
 */
public class Controller {

    static Scanner scanner = new Scanner(System.in);
    static int select = -1;

    public Controller() {
        while (select != 0) {
            try {

                switch (select) {
                    case 1: //DEMANAR MENJAR.
                        demanar_menjar();
                        select = vols_reiniciar();
                        break;
                    case 2:

                        break;
                    case 3: //MOSTRAR ENCARREGATS
                        mostrar_encarregats();
                        select = vols_reiniciar();
                        break;
                    case 4:

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

    public static void demanar_menjar() {
        Plat[] plats = Plat.getPlats();
        View.render_carta(plats);
        int selector = Integer.parseInt(scanner.nextLine());
        View.pinta("Has seleccionat: " + plats[selector].getNom());
        Encarregat encarregat = Encarregat.findEncarregatByPlat(plats[selector]);
        View.pinta("La persona encarregada de la realització del plat es: " + encarregat.getNom());
        Object[] arr = Ingredient.findIngredients_I_ReceptesByPlat(plats[selector]);
        Ingredient[] ingredients = (Ingredient[]) arr[0];
        Recepta[] receptes = (Recepta[]) arr[1];
        ArrayList ingredientsBaixos = comprovar_existencies(ingredients, receptes);
        boolean suficient = ingredientsBaixos.isEmpty();
        View.render_IngredientsIReceptes(ingredients, receptes, suficient);
        if (suficient == false) {
            realitzar_comanda(ingredientsBaixos);
        }

    }

    public static void mostrar_encarregats() {
        Encarregat[] encarregats = Encarregat.getEncarregats();
        View.render_encarregats(encarregats);
    }

    public static int vols_reiniciar() {
        View.pinta("\nVols tornar al menú principal o prefereixes finalitzar el programa?");
        View.pinta("\n1) Reiniciar \n0) Exir");
        int select = Integer.parseInt(scanner.nextLine());
        return select;
    }

    public static ArrayList comprovar_existencies(Ingredient[] ingredients, Recepta[] receptes) {
        ArrayList<Ingredient> IngredientsBaixos = new ArrayList<>();
        for (int i = 0; i < ingredients.length; i++) {
            if (ingredients[i].getExistencies() < receptes[i].getCantitat()) {
                IngredientsBaixos.add(ingredients[i]);
            }
        }
        return IngredientsBaixos;
    }

    public static void realitzar_comanda(ArrayList ingredientsBaixos) {
        View.pinta("Els següents elements estan per baix del stock mínim:");

        Iterator<Ingredient> iteradorIngredientsBaixos = ingredientsBaixos.iterator();
        
        while (iteradorIngredientsBaixos.hasNext()) {
            Ingredient ingredientBaix = iteradorIngredientsBaixos.next();
            View.pinta(ingredientBaix.getNom());

        }

        View.pinta("Vols realitzar la comanda ara?\n 1) Sí\n 2) No");
        
        int opcio = 0;
        while (opcio != 1 && opcio != 2) {
            opcio = Integer.parseInt(scanner.nextLine());
            switch (opcio) {
                case 1:
                    View.pinta("Has seleccionat 1.");//Realitzar la comanda
                    break;
                case 2:
                    View.pinta("Has seleccionat 2.");
                    break;
                default:
                    View.pinta("Torna a intentar-ho");
                    break;
            }
            
            View.pinta("Estic fora del switch, pero en el while de merda.");
            //if(opcio == 1 || opcio ==2) break; //REVISAR!

        }
        View.pinta("Per fi, fora del while ;-) ");

    }

}
