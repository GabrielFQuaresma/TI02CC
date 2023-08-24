import java.util.*;
class SomarDoisNumeros {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String args[]){
		//Declaração de váriaveis
		int num1 = 0;
		int num2 = 0;
		int soma = 0;
		
		//Leituras
		System.out.println("Digite o primeiro número: ");
		num1 = sc.nextInt();
		System.out.println("Digite o segundo número: ");
		num2 = sc.nextInt();
		
		//Somar
		soma = num1 + num2;
		
		//Mostrar na tela
		System.out.println("Soma:" + soma);
	}
}
