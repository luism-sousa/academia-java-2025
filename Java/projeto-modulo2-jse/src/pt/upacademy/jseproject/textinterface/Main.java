package pt.upacademy.jseproject.textinterface;

import java.util.Scanner;

public class Main {
	// Variaveis
	private Scanner sc = new Scanner(System.in);
	private boolean mostrarMenu = true;				 // Variavel que guarda o estado do menu 
	private int opcaoEscolhida = -1;				 
	private ProductManager productManager;			 // Objeto para o menu dos produtos
	private ShelfManager shelfManager;				 // Objeto para o menu das prateleiras
	
	public static void main(String[] args) {
		Main app = new Main();
		app.startMenu();
	}
	
	public Main() {
		this.productManager = new ProductManager(sc);
		this.shelfManager = new ShelfManager(sc);
	}
		
	public void startMenu() {
		while (mostrarMenu) {
			System.out.println("== Menu Principal ==\n"
					+ "Por favor selecione uma das seguintes opções: \n"
					+ "\t1) Listar produtos\n"
					+ "\t2) Listar prateleiras\n"
					+ "\t3) Sair\n");
			System.out.print("Escolha uma opção do menu: ");
			
			if (sc.hasNextInt()) {
		        opcaoEscolhida = sc.nextInt();
		        sc.nextLine(); 			// Limpar o buffer
		    } else {		    
		        sc.nextLine(); 			// Limpar inputs incorretos
		        opcaoEscolhida = -1; 	// Reset "opcaoEscolhida" para input invalido
		    }
			
			switch (opcaoEscolhida) {
				case 1:
					productManager.showMenu();
					break;
				case 2:
					shelfManager.showMenu();
					break;
				case 3:
					mostrarMenu = false;
					System.out.println("\nFim do Programa");
					break;
				default:
					System.out.println("\nOpção incorreta! Tente novamente!");
			}
		}		
		sc.close();		// Fechar scanner
	}
}
