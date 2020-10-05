import java.util.Scanner;


public class Partie {
    int nb_tour=0;
    Combinaison[] tableau;
    Des hand;

    void begin(){
        nb_tour = 0;
        tableau = init();
        hand = new Des();
        System.out.println("Début de partie.");
        
        do {
        System.out.println("Appuyer sur entrée pour lancer la manche...");
        Scanner pause = new Scanner(System.in);
        pause.nextLine();
        nouvelleManche();
        } while (nb_tour < 13);
        bonus();
        end();
    }

    void nouvelleManche(){
        nb_tour += 1;
        System.out.println("----------------\n    Manche" + nb_tour + "\n----------------");
        hand.toutLancer(); //1er lancé
        hand.affiche();
        int nextStep = 0;

        do {
            nextStep = askUser(); // 1 : Relancer    2 : Garder les dés 
            if (nextStep == 1){ //2ème lancé
                System.out.println("------2ème lancé------");
                relancer();
                nextStep = askUser();
                if(nextStep == 1){ //3ème lancé
                    System.out.println("------Dernier lancé------");
                    relancer();
                    nextStep=2;
                }
            }
            else{
                if(nextStep == 2) {
                    System.out.println("Vous gardez ce lancé.");
                }                
                else{
                    System.out.println("Erreur : veuillez saisir une valeur correcte.");
                }
            }
        } while (nextStep != 2 );
        listeCombinaison(hand);
        choixCombinaison(hand);
        afficheTableau();
    }

    void relancer(){ //TO DO : ajouter une fonction qui empeche de saisir 2 fois le même numéro
        System.out.println("Quels dés souhaitez vous relancer ? Saisir un numéro à la fois, saisir 0 pour terminer la selection.");
        int[] relance = new int [5];
        int i=0;
        Scanner desARelancer = new Scanner(System.in); 
        do {
            relance[i] = desARelancer.nextInt();
            i += 1;
        } while (relance[i-1] != 0 && i<5);
        hand.relance(relance);
        hand.affiche();
    }

    int askUser(){
        Scanner saisieUtilisateur = new Scanner(System.in); 
        int choix = 0;
        do {      
        System.out.println("Que voulez-vous faire ?\n1. Relancer des dés ?\n2. Garder ce lancer");
        choix = saisieUtilisateur.nextInt(); 
        if (choix < 1 || choix > 2){
            System.out.println("Veuillez saisir une valeur correcte.");
        }
        } while (choix < 1 || choix > 2);
        return choix;
    } 

    void choixCombinaison(Des des){
        System.out.println("---------------------------------------------");
        System.out.println("\nQue souhaitez-vous faire ? Saisir un numéro");
        Scanner choixCombinaison = new Scanner(System.in);
        int choix = 0;
        do{
        choix = choixCombinaison.nextInt();
        } while (choix < 1 || choix >13);
        
        if(tableau[choix-1].notSelected == true){
            tableau[choix-1].notSelected = false;   
        }            
        switch(choix){
            case 1: tableau[choix-1].valeur = des.somme(1);
                    break;
            case 2: tableau[choix-1].valeur = des.somme(2);
                    break;
            case 3: tableau[choix-1].valeur = des.somme(3);
                    break;
            case 4: tableau[choix-1].valeur = des.somme(4);
                    break;
            case 5: tableau[choix-1].valeur = des.somme(5);
                    break;
            case 6: tableau[choix-1].valeur = des.somme(6);
                    break;
            case 7: tableau[choix].valeur = des.somme(des.brelan());
                    break;
            case 8: tableau[choix].valeur = des.somme(des.carre());
                    break;              
            case 9: tableau[choix].valeur = des.full();
                    break;
            case 10: tableau[choix].valeur = des.petiteSuite();
                break;
            case 11: tableau[choix].valeur = des.grandeSuite(); 
                     break;
            case 12: tableau[choix].valeur = des.yams();
                    break;
            case 13: tableau[choix].valeur = des.totalDes();
                    break;                        
            default: System.out.println("Erreur");
            }
        }  
        

    void afficheTableau(){
        System.out.println("----------\n    TABLEAU DE SCORE\n----------");
        for (int i=0;i<14;i++){
            tableau[i].afficheCombinaison();
        }
    }

    Combinaison[] init(){
        tableau = new Combinaison[14];
        for (int i=0; i<14; i++){
            tableau[i] = null;
        }
        tableau[0] = new Combinaison("Somme des 1");
        tableau[1] = new Combinaison("Somme des 2");
        tableau[2] = new Combinaison("Somme des 3");
        tableau[3] = new Combinaison("Somme des 4");
        tableau[4] = new Combinaison("Somme des 5");
        tableau[5] = new Combinaison("Somme des 6");

        tableau[6] = new Combinaison("Bonus si > à 62");

        tableau[7] = new Combinaison("Brelan");
        tableau[8] = new Combinaison("Carré");
        tableau[9] = new Combinaison("Full");
        tableau[10] = new Combinaison("Petite suite");
        tableau[11] = new Combinaison("Grande suite");
        tableau[12] = new Combinaison("Yams");
        tableau[13] = new Combinaison("Chance");
        
        return tableau;
    }
    
    void listeCombinaison(Des des){
        for(int i=0;i<13;i++){
            if(tableau[i].notSelected==true){
                if(i<6){
                System.out.println((i+1) + ". Mettre " + des.somme(i+1) + " dans les " + (i+1));
                }
                switch(i){
                    case 6: System.out.println((i+1) + ". Mettre " + des.somme(des.brelan()) + " dans le brelan");
                    break;

                    case 7: System.out.println((i+1) + ". Mettre " + des.somme(des.carre()) + " dans le carré");
                    break;

                    case 8: System.out.println((i+1) + ". Mettre " + des.full() + " dans le full");
                    break;

                    case 9: System.out.println((i+1) + ". Mettre " + des.petiteSuite() + " dans la petite suite.");
                            break;

                    case 10: System.out.println((i+1) + ". Mettre " + des.grandeSuite() + " dans la grande suite.");
                             break;

                    case 11: System.out.println((i+1) + ". Mettre " + des.yams() + " dans le Yams");
                             break;
                    case 12: System.out.println((i+1) + ". Mettre " + des.totalDes() + " dans la chance");
                    break;
                    
                    default: break;
                }
                
            }
        }
    }
    void end(){
        System.out.println("-------------------");
        System.out.println("Fin de Partie");
        System.out.println("-------------------");
        System.out.println("VOTRE SCORE : " + scoreTotal());
        System.out.println("Merci d'avoir joué ! - Jeu codé par Simon Rogé - 2020");
    }

    void bonus(){
        int totalSomme = 0;
        for (int i =0; i<6;i++){
            totalSomme += tableau[i].valeur;
        }
        if (totalSomme>62){
            tableau[13].valeur = 35;
        }
    }
    int scoreTotal(){
        int total=0;
        for (int i=0; i<14;i++){
            total += tableau[i].valeur;
        }
        return total;
    }
}