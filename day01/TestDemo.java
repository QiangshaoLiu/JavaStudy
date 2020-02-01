public class TestDemo{
      public static void main(String args[]){
                int rol = 10;
                for(int i = 0; i < rol; i++){
                         for(int j = rol - i; j > 0; j--){
                                System.out.print(" ");      
                          }
                         for(int j = 2*i; j >= 0; j--){
                                System.out.print("*");
                         }
                         System.out.println();
                 }
      }
}