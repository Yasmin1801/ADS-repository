package exemplos;
import java.util.Scanner;

public class Exemplo2{
    public static void main (String[]args){
        Scanner entrada = new Scanner(System.in);

        System.out.println("Listar os 5 livros favoritos."); 

        System.out.println("Digite qual é o seu livro favorito:");
        String Livro1 = entrada.nextLine();

        System.out.println("Digite qual é o seu segundo livro favorito:");
        String Livro2 = entrada.nextLine();

        System.out.println("Digite qual é o seu terceiro livro favorito:");
        String Livro3 = entrada.nextLine();

        System.out.println("Digite qual é o seu quarto livro favorito:");
        String Livro4 = entrada.nextLine();

        System.out.println("Digite qual é o seu quinto livro favorito:");
        String Livro5 = entrada.nextLine();

        System.out.println("\nSeus 5 livros favoritos são:");
        System.out.println("1. "+Livro1);
        System.out.println("2. "+Livro2);
        System.out.println("3. "+Livro3);
        System.out.println("4. "+Livro4);
        System.out.println("5. "+Livro5);
        entrada.close();
    }
    
}
