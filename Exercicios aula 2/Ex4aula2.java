
import java.util.Scanner;
public class Ex4aula2 {
    public static void main(String[] args) {
        //Yasmin Mayumi Okubo Sato
        
        Scanner entrada = new Scanner (System.in);

        System.out.printf("Digite seu primeiro número: ");
        int n1 = entrada.nextInt();

        System.out.printf("Digite seu segundo número: ");
        int n2 = entrada.nextInt();

        System.out.printf("Digite seu terceiro número: ");
        int n3 = entrada.nextInt();

        System.out.printf("Digite seu quarto número: ");
        int n4 = entrada.nextInt();

        int soma = n1 + n2 + n3 + n4;

        System.out.printf("A soma desses números é: " +soma+ ".");
        
        entrada.close();
    }
}