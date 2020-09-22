import java.util.Scanner;

public class Yams {
    public static void main(String[] args){

      System.out.println("---YAMS---");
      int n = 5; //nombre de dés
      int[] des = new int[n];

      des = lancer(n); //1er lancer

      System.out.println(n + " dés lancés.");
      for (int i=0; i<n; i++){
        System.out.println("Dé " + (i+1) + ": " + des[i]);
      }

      System.out.println("--------------");


      Scanner saisieUtilisateur = new Scanner(System.in); 
      int choix = 0;
      do {      
      System.out.println("Que voulez-vous faire ?");
      System.out.println("1. Relancer des dés ?");
      System.out.println("2. Garder ce lancer");
      choix = saisieUtilisateur.nextInt(); 

      if (choix == 1){
        System.out.println("Vous avez choisi de relancer les dés.");
        System.out.println("Quels dés souhaitez vous relancer ?");
      }
      else{
        if(choix == 2) {
        System.out.println("Vous avez choisi de garder ce lancer");
        }
        else{
        System.out.println("Erreur : veuillez saisir une valeur correcte.");
        }
    }
   } while (choix < 1 || choix > 2);

    }
    
    public static int[] lancer(int n){ //Lancé de n dés
      if (n>5){
        n=5;
      }

      int dés_lancés[] = new int[n];
      for (int i=0; i<n; i++){
        dés_lancés[i] = 1 + (int)(Math.random() * ((6 - 1) + 1));
      }

      return dés_lancés;
  }
  }

  
