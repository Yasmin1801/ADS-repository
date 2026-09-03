
import java.util.Scanner;

public class Ex5aula2 {
    public static void main(String [] args){
        //Yasmin Mayumi Okubo Sato
        
        Scanner entrada = new Scanner (System.in);
        //Yasmin Mayumi Okubo Sato
        System.out.printf("Digite a primeira nota do aluno: ");
        double nota1 = entrada.nextDouble();

        System.out.printf("Digite a segunda nota do aluno: ");
        double nota2 = entrada.nextDouble();

        System.out.printf("Digite a terceira nota do aluno: ");
        double nota3 = entrada.nextDouble();

        int media = (int) ((nota1 + nota2 + nota3) / 3);

        System.out.printf("A média do aluno é: " +media+ ".");

        entrada.close();

    }
    
}
