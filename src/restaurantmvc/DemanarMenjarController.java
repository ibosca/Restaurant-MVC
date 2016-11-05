/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantmvc;

import java.util.ArrayList;
import java.util.Iterator;
import static restaurantmvc.Controller.scanner;

/**
 *
 * @author isaac
 */
public class DemanarMenjarController extends Controller{
    public static void demanar_menjar() {
        Plat[] plats = Plat.getPlats();
        View.render_carta(plats);
        View.pinta("Selecciona un plat: ");
        int selector = Integer.parseInt(scanner.nextLine());
        View.pinta("Has seleccionat: " + plats[selector].getNom());
        
        recopilar_informacio_plat_seleccionat(plats[selector]);
        

    }
    
    public static void recopilar_informacio_plat_seleccionat(Plat platSeleccionat){
        Encarregat encarregat = Encarregat.findEncarregatByPlat(platSeleccionat);
        View.pinta("La persona encarregada de la realització del plat es: " + encarregat.getNom());
        Object[] arr = Ingredient.findIngredients_I_ReceptesByPlat(platSeleccionat);
        Ingredient[] ingredients = (Ingredient[]) arr[0];
        Recepta[] receptes = (Recepta[]) arr[1];
        ArrayList ingredientsBaixos = comprovar_existencies(ingredients, receptes);
        boolean suficient = ingredientsBaixos.isEmpty();
        View.render_IngredientsIReceptes(ingredients, receptes, suficient);
        if (suficient == false) {
            realitzar_comanda(ingredientsBaixos);
        }
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
