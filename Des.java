import java.util.Arrays;

public class Des {
    int[] dés_lancés;

    public Des(){
      dés_lancés = new int[5];
      for (int i=0; i<5; i++){
        dés_lancés[i] = 1 + (int)(Math.random() * ((6 - 1) + 1));
      }
    }

    public Des(int[] T){
      dés_lancés= new int[5];
      for (int i=0;i<5;i++){
        dés_lancés[i] = T[i];
      }
    }

    int[] trier(){
      Arrays.sort(dés_lancés);
      return dés_lancés;
    }

    int[] toutLancer(){
        dés_lancés = new int[5];
          
        for (int i=0; i<5; i++){
          dés_lancés[i] = 1 + (int)(Math.random() * ((6 - 1) + 1));
        }
        return dés_lancés;
    }

    int[] relance(int[] n){
      if (existe(n, 0) == -1){
        dés_lancés = toutLancer();
        System.out.println("Tous les dés sont relancés.");
      }
      else{
        for (int i=0;i<n.length;i++){
          if (n[i]!=0){
            dés_lancés[n[i]-1] =  1 + (int)(Math.random() * ((6 - 1) + 1));
            System.out.println("Dé " + n[i] + " relancé.");
          } 
        }
      }
      return dés_lancés;
    }

    void affiche(){
      for(int i =0; i<5;i++){
        System.out.println("Dé " + (i+1) + " : " + dés_lancés[i]);
      }
    }

    int get(int n){
          return dés_lancés[n];
    }

    int somme(int n){
      int total = 0;
      for(int i = 0; i<5;i++){
          if(dés_lancés[i]==n){
              total += n;
          }
      }
      return total;
  }

  int yams(){
    if (dés_lancés[0] == dés_lancés[1] && dés_lancés[1] == dés_lancés[2] && dés_lancés[2] == dés_lancés[3] && dés_lancés[3] == dés_lancés[4]){
      return 50;
    }
    else{
      return 0;
    }
  }

  int carre(){ //retourne la valeur du dé qui est quadruplé
    int nb_identique=0;
    for (int i=0;i<5;i++){
      if(dés_lancés[0]==dés_lancés[i]){
        nb_identique++;
      }
    }
    if (nb_identique >= 4){
      return dés_lancés[0];
    }
    else{
      nb_identique=0;
      for (int i=0;i<5;i++){
        if(dés_lancés[1]==dés_lancés[i]){
          nb_identique++;
        }
    }
    if(nb_identique>=4){
      return dés_lancés[1];
    }
    else{
      return 0; //pas de carré
      }
    }
  }

  int valeurCarre(){
    if (this.carre() == 0){
      return 0;
    }
    else{
      return this.totalDes();
    }
  }

  int totalDes(){
    int total =0;
    for(int i=0; i<5;i++){
      total += dés_lancés[i];
    }
    return total;
  }

  int valeurBrelan(){
    if (this.brelan() == 0){
      return 0;
    }
    else {
      return totalDes();
  }
}


  int existe(int[] T, int val){
    int position=-1;
    for (int i=0; i<T.length;i++){
      if (T[i] == val){
        position = i;
      }
    }
    return position;
  }

  int brelan(){ //a optimiser
    int nb_identique=0;
    for (int i=0;i<5;i++){
      if(dés_lancés[0]==dés_lancés[i]){
        nb_identique++;
      }
    }
    if (nb_identique >= 3){
      return dés_lancés[0];
    }
    else{
      nb_identique=0;
      for (int i=0;i<5;i++){
        if(dés_lancés[1]==dés_lancés[i]){
          nb_identique++;
        }
    }
    if(nb_identique>=3){
      return dés_lancés[1];
    }
    else{
      nb_identique=0;
      for (int i=0;i<5;i++){
        if(dés_lancés[2]==dés_lancés[i]){
          nb_identique++;
        }
      }
      if(nb_identique>=3){
          return dés_lancés[2];
        } 
        else return 0; //pas de brelan
      }
    }
  }

  int grandeSuite(){
    this.trier();
    int compteur=0;
    for(int i=0; i<4;i++){
      if ((dés_lancés[i]+1) == dés_lancés[i+1]) compteur++;
    }
    if (compteur == 4) return 40;
    else return 0;
  }

  int petiteSuite(){
    this.trier();
    int compteur=0;
    for(int i=0; i<4;i++){
      if ((dés_lancés[i]+1) == dés_lancés[i+1]) compteur++;
    }
    if (compteur <= 2){
      compteur =0;
      for(int i=1; i<4;i++){
        if ((dés_lancés[i]+1) == dés_lancés[i+1]) compteur++;
     }
    }
    if (compteur >= 3) return 30;
    else return 0;
  }

int full(){
  this.trier();
  int compteur =0;
  if(this.brelan() == 0){
   return 0;   
  }
  else{
    for(int i=0; i<4;i++){
      if((dés_lancés[i] == dés_lancés[i+1]) && (dés_lancés[i] != this.brelan())){
        compteur ++;
      }
    }
    if (compteur ==1){
      return 25;
    }
    else return 0;
    }
  }
}