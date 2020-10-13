import java.util.Scanner;


public class Partie {
    private int nb_tour=0;
    private Combinaison[] tableau;
    private Des hand;

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
        int choix = 1;
        int indexTableau = 0;
        do{
        choix = choixCombinaison.nextInt();
        indexTableau = choix-1;
        } while ((choix < 1 || choix >13) && tableau[indexTableau].getNotSelected() == true); //empêche l'utilisateur de saisir une valeur incorrecte

        tableau[indexTableau].selected();

        switch(choix){
            case 1: tableau[indexTableau].setValeur(des.somme(1));
                    tableau[13].selected();
                    tableau[15].selected();
                    break;
            case 2: tableau[indexTableau].setValeur(des.somme(2));
                    tableau[13].selected();
                    tableau[15].selected();
                    break;
            case 3: tableau[indexTableau].setValeur(des.somme(3));
                    tableau[13].selected();
                    tableau[15].selected();
                    break;
            case 4: tableau[indexTableau].setValeur(des.somme(4));
                    tableau[13].selected();
                    tableau[15].selected();
                    break;
            case 5: tableau[indexTableau].setValeur(des.somme(5));
                    tableau[13].selected();
                    tableau[15].selected();
                    break;
            case 6: tableau[indexTableau].setValeur(des.somme(6));
                    tableau[13].selected();
                    tableau[15].selected();
                    break;
            case 7: tableau[indexTableau].setValeur(des.valeurBrelan());
                    tableau[16].selected();
                    break;
            case 8: tableau[indexTableau].setValeur(des.valeurCarre()); 
                    tableau[16].selected();
                    break;              
            case 9: tableau[indexTableau].setValeur(des.full());
                    tableau[16].selected();    
                    break;
            case 10: tableau[indexTableau].setValeur(des.petiteSuite());
                    tableau[16].selected();
                    break;
            case 11: tableau[indexTableau].setValeur(des.grandeSuite());
                    tableau[16].selected(); 
                    break;
            case 12: tableau[indexTableau].setValeur(des.yams());
                    tableau[16].selected();
                    break;
            case 13: tableau[indexTableau].setValeur(des.totalDes());
                    tableau[16].selected();
                    break;                        
            default: System.out.println("Erreur");
            }
            tableau[17].selected();
            bonus();
            calculScore();
        }
        

    void afficheTableau(){
        System.out.println("----------\n    TABLEAU DE SCORE\n----------");
        for (int i=0;i<6;i++){
            tableau[i].afficheCombinaison();
        }
        tableau[13].afficheCombinaison(); //sous-total 1
        tableau[14].afficheCombinaison(); //Bonus si > à 62
        tableau[15].afficheCombinaison(); // TOTAL 1
        for (int i=6; i<18;i++){
            if (i != 13 && i != 14 && i != 15){ //condition pour ne pas re-afficher les combinaisons ci-dessus
                tableau[i].afficheCombinaison(); 
            }
        }
    }

    Combinaison[] init(){
        tableau = new Combinaison[18];
        for (int i=0; i<18; i++){
            tableau[i] = null;
        }
        tableau[0] = new Combinaison("Somme des 1");
        tableau[1] = new Combinaison("Somme des 2");
        tableau[2] = new Combinaison("Somme des 3");
        tableau[3] = new Combinaison("Somme des 4");
        tableau[4] = new Combinaison("Somme des 5");
        tableau[5] = new Combinaison("Somme des 6");

        tableau[6] = new Combinaison("Brelan");
        tableau[7] = new Combinaison("Carré");
        tableau[8] = new Combinaison("Full");
        tableau[9] = new Combinaison("Petite suite");
        tableau[10] = new Combinaison("Grande suite");
        tableau[11] = new Combinaison("Yams");
        tableau[12] = new Combinaison("Chance");

        tableau[13] = new Combinaison("Sous-total 1");
        tableau[14] = new Combinaison("Bonus si > à 62");
        tableau[15] = new Combinaison("TOTAL 1");
        tableau[16] = new Combinaison("TOTAL 2");
        tableau[17] = new Combinaison("TOTAL 1+2");
        
        return tableau;
    }
    
    void listeCombinaison(Des des){
        for(int i=0;i<13;i++){
            if(tableau[i].getNotSelected()==true && i<6){
                System.out.println((i+1) + ". Mettre " + des.somme(i+1) + " dans les " + (i+1));
            }
            if(tableau[i].getNotSelected()==true){
                switch(i){
                    case 6: System.out.println((i+1) + ". Mettre " + des.valeurBrelan() + " dans le brelan");
                    break;

                    case 7: System.out.println((i+1) + ". Mettre " + des.valeurCarre() + " dans le carré");
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
        int nbCombinaisonRemplie =0;
        for (int i=0; i<6; i++){
            if(tableau[i].getNotSelected() == false){
                nbCombinaisonRemplie += 1;
            }
        }
        if (nbCombinaisonRemplie == 6){
            for (int i =0; i<6;i++){
            totalSomme += tableau[i].getValeur();
            }
            if (totalSomme>62){
                tableau[13].setValeur(35);
                System.out.println("Bonus accordé ! +35");
            }
            else{
                tableau[13].setValeur(0);
                System.out.println("Sous-total 1 < 63 : Pas de bonus");
            }
        }
    }

    int scoreTotal(){
        int total=0;
        for (int i=0; i<14;i++){
            total += tableau[i].getValeur();
        }
        return total;
    }


void calculScore(){
    tableau[13].setValeur(0); //sous-total 1
    tableau[15].setValeur(0); //Total 1
    tableau[16].setValeur(0); //Total 2
    tableau[17].setValeur(0); //TOTAL 1+2

    int st1=0;

    for(int i=0;i<6;i++){
        st1 += tableau[i].getValeur();
    }
    tableau[13].setValeur(st1);
    tableau[15].setValeur(tableau[13].getValeur() + tableau[14].getValeur());
    int total1 = 0;

    for (int i = 6; i<13;i++){
        total1 += tableau[i].getValeur();
    }
    tableau[16].setValeur(total1);

    tableau[17].setValeur(tableau[15].getValeur() + tableau[16].getValeur());
}
}