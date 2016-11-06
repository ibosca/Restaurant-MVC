/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantmvc;

/**
 *
 * @author isaac
 */
public class ConsultesController extends Controller {

    public static void mostrar_encarregats() {
        Encarregat[] encarregats = Encarregat.getEncarregats();
        View.render_encarregats(encarregats);
    }
    
    public static void mostrar_plats() {
        Plat[] plats = Plat.getPlats();
        View.render_carta(plats);
    }
    
    public static void mostrar_ingredients(){
        Ingredient[] ingredients = Ingredient.getIngredients();
        View.render_ingredients(ingredients);
    }
    
    public static void mostrar_plats_per_categoria(){
        Categoria[] categories = Categoria.getCategories();
        View.render_categories(categories);
        View.pinta("Tria una categoria: ");
        int select = Integer.parseInt(scanner.nextLine());
        Categoria selected_category = categories[select];
        Plat[] plats = Plat.findPlatsByCategory(selected_category);
        View.render_carta(plats);
        
    }
    
    public static void mostrar_plats_per_ingredient(){
        Ingredient[] ingredients = Ingredient.getIngredients();
        View.render_ingredients(ingredients);
        View.pinta("Tria un ingredient: ");
        int select = Integer.parseInt(scanner.nextLine());
        Ingredient selected_ingredient = ingredients[select];
        Plat[] plats = Plat.findPlatsByIngredient(selected_ingredient);
        View.render_carta(plats);
        
    }
    
    public static void mostrar_plats_per_encarregat() {
        Encarregat[] encarregats = Encarregat.getEncarregats();
        View.render_encarregats(encarregats);
        View.pinta("Tria un encarregat: ");
        int select = Integer.parseInt(scanner.nextLine());
        Encarregat selected_encarregat = encarregats[select];
        Plat[] plats = Plat.findPlatsByEncarregat(selected_encarregat);
        View.render_carta(plats);
    }
    
    public static void mostrar_ingredients_baixos() {
        Ingredient[] ingredients = Ingredient.getIngredientsByStock("<");
        View.render_ingredients(ingredients);
    }
    
    public static void mostrar_ingredients_alts() {
        Ingredient[] ingredients = Ingredient.getIngredientsByStock(">");
        View.render_ingredients(ingredients);
    }
}
