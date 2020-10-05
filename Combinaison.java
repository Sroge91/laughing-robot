public class Combinaison {
    String nom;
    int valeur = 0;
    boolean notSelected =true;

    void affiche(){
        System.out.println("Vous avez choisi :");
        System.out.println(nom);
        System.out.println("Points : +" + valeur);
    }

    public Combinaison(){
        nom = null;
        valeur = 0;
        notSelected = true;
    }

    public Combinaison(String s){
        nom = s;
        valeur = 0;
        notSelected = true;
    }

    void afficheCombinaison(){
        if (notSelected == true ){
            System.out.println(nom + " :");
        }
        else {
            System.out.println(nom + " : " + valeur);
        }
    }
}
