package pt.upacademy.appsenhas;

import java.util.LinkedList;
import java.util.Scanner;

public class AppSenhas {
	// Variaveis
	private Scanner sc = new Scanner(System.in);
	private boolean mostrarMenu = true; // Variavel que guarda o estado do menu
	private int opcaoEscolhida = -1;
	private int senha = 1;
	private String nome, problema = "";
	private LinkedList<Problema> listaProblemas = new LinkedList<>();

	public void iniciarMenu() {
		while (mostrarMenu) {
			System.out.println("");
			System.out.println("===================================");
			System.out.println("         MENU DE SENHAS");
			System.out.println("===================================");
			System.out.println(">| 1. Tirar nova senha");
			System.out.println(">| 2. Chamar próxima senha");
			System.out.println(">| 3. Ver senhas na fila");
			System.out.println(">| 0. Sair");
			System.out.println("");
			System.out.print("Escolha uma opção do menu: ");

			// Ler a opção escolhida
			if (sc.hasNextInt()) {
				opcaoEscolhida = sc.nextInt();
				sc.nextLine();
			} else {
				System.out.println("");
				System.out.println(">> Opção incorreta! Tente novamente! <<");
				sc.nextLine(); // Limpar o scanner
			}

			switch (opcaoEscolhida) {
				case 1:
					System.out.println();
					System.out.println("======================");
					System.out.println("===== NOVA SENHA =====");
					System.out.println("A sua Senha é a nº : " + senha);
					System.out.print("Introduza o seu nome: ");
					nome = sc.nextLine();
					while (nome.isEmpty()) {
						System.out.println("Nome inválido. Introduza novamente!");
						System.out.print("Introduza o seu nome: ");
						nome = sc.nextLine();
					}
					System.out.print("Descreva o seu problema: ");
					problema = sc.nextLine();
					while (problema.isEmpty()) {
						System.out.println("Problema inválido. Introduza novamente!");
						System.out.print("Descreva o seu problema: ");
						problema = sc.nextLine();
					}
					listaProblemas.add(new Problema(nome, problema, senha));
					System.out.println();
					System.out.println(">> O seu Problema foi adicionado a lista de espera. <<");
					System.out.println(">> Por favor, aguarde até ser chamado. <<");
					senha++;
					break;
				case 2:
					if (listaProblemas.isEmpty()) {
						System.out.println(">> Não existem senhas para serem atendidas! <<");
					} else {
						System.out.println(">> Próxima senha chamada! <<");
						listaProblemas.getFirst().print();
						System.out.println();
						System.out.print(">|O seu problema foi resolvido? Digite 'S' ou 'N':");
					}
					break;
				case 3:
					if (listaProblemas.isEmpty()) {
						System.out.println(">> Não existem senhas na fila! <<");
					} else {
						System.out.println("===== Senhas atualmente na fila =====");
						for (Problema p : listaProblemas) {
							p.print();
						}
					}
					break;
				case 0:
					mostrarMenu = false;
					System.out.println("=== Obrigado por usar o Sistema de senhas! ===");
					System.out.println("=== Tenha um ótimo dia :) ===");
					System.out.println("=== FIM PROGRAMA ===");
					break;
				default:
					break;
			}
		}
		sc.close(); // Fechar o scanner
	}

	public static void main(String[] args) {
		AppSenhas app = new AppSenhas();
		app.iniciarMenu();
	}
}
