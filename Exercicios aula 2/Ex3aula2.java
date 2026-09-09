
import java.util.Scanner;

public class Ex3aula2 {
    public static void main(String[] args) {
        //Yasmin Mayumi Okubo Sato
        
        Scanner entrada = new Scanner (System.in);

        System.out.printf("Digite o primeiro número inteiro:");
        int n1 = entrada.nextInt();

        System.out.println("Digite o segundo número inteiro:");
        int n2 = entrada.nextInt();

        int produto = n1 * n2;
        System.out.printf("O produto é: " +produto+ ".");
        entrada.close();

    }
}
