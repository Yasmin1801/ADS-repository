

import java.util.Scanner;

public class Ex11aula2 {
    public static void main(String[] args) {
        //Yasmin Mayumi Okubo Sato

    Scanner entrada = new Scanner(System.in);

    System.out.print("Digite o ano que você nasceu: ");
    int ano1 = entrada.nextInt();

    System.out.print("Digite o ano atual: ");
    int ano2 = entrada.nextInt();

    int idade1 = ano2-ano1;
    int idade2 = 2030-ano1;

    System.out.println("Você atualmente tem :" + idade1 + " anos. ");
    System.out.println("em 2030 você terá: " + idade2 + " anos.");
        
    entrada.close();
    }
    
}
