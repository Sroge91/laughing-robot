public class yams {
    public static void main(String[] args){

      System.out.println("---YAMS---");
      lancer(5);
    }
    
    public static int[] lancer(int n){
      if (n>5){
        n=5;
      }
    int dés_lancés[] = new int[n];
    for (int i=0; i<n; i++){
      dés_lancés[i] = 1 + (int)(Math.random() * ((6 - 1) + 1));
    }
    System.out.println(n + " dés lancés.");
    for (int i=0; i<n; i++){
      System.out.println(dés_lancés[i]);
    }
    return dés_lancés;
  }
  }

  