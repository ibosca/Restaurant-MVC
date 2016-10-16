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
public class View {
    
    public static void pinta(String string){
        System.out.println(string);
    }
    
    public static void render_home(){
        System.out.println("Tria una opció:\n"
                + "1.- Veure la carta" +
		"\n2.- Comprovar stock\n" +
		"3.- Mostrar encarregats\n" +
		"0.- Eixir");
        System.out.println("\n\nOpció: ");
    }
    
    public static void render_carta(Plat[] plats){
        System.out.println("<------ CARTA ------->");
        for (int i = 0; i < plats.length; i++) {
            System.out.println(i+".- "+plats[i].getNom()+" ; "+plats[i].getPreu()+"€ ; Categoría: "+plats[i].getCategoria());
        }
        System.out.println("\nTria un plat: ");
    }
    
    public static void render_encarregats(Encarregat[] encarregats){
        for (int i = 0; i < encarregats.length; i++) {
            System.out.println(i+".- Nom: "+encarregats[i].getNom()+", "+encarregats[i].getCognom()+"; e-mail: "+encarregats[i].getEmail()+"; telèfon: "+encarregats[i].getTelefon());
        }
    }
    
    public static void render_IngredientsIReceptes(Ingredient[] ingredients, Recepta[] receptes, boolean suficient){
        if(suficient == true){
        View.pinta("Per a realitzar el plat s'utilitzaran els següents ingredients: ");
        }else{
            View.pinta("No hi han existències suficients. Implementar la comanda. ");
        }
        for (int i = 0; i < ingredients.length; i++) {
            View.pinta("Ingredient: "+ingredients[i].getNom()+"; Existènies:"+ingredients[i].getExistencies()+" "+ingredients[i].getUnitat_medida()+" Quantitat necessaria: "+receptes[i].getCantitat());
        }
    }
}
