package pt.upacademy.examples.Example1;

import java.util.Scanner;

public class Hello {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String nomeUser;
		int idadeUser;
		
		System.out.println("Bem-vindo!");
		System.out.print("Introduza o seu nome: ");
		nomeUser = sc.next();
		
		System.out.print("Introduza a sua idade: ");
		idadeUser = sc.nextInt();
		
		sc.close();
				
		System.out.println("O seu nome é: " + nomeUser + " e têm " + idadeUser + " anos");
	}
}
