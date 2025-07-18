package pt.upacademy.jseproject.textinterface;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import pt.upacademy.jseproject.model.Product;
import pt.upacademy.jseproject.model.Shelf;

public class ShelfManager {
	private List<Product> products = new ArrayList<>();
	private List<Shelf> shelves = new ArrayList<>();
	private Scanner sc;
	
	public ShelfManager(Scanner sc) {
		this.sc = sc;
	}
	
	public void showMenu() {
		boolean mostrarMenu = true;

		while (mostrarMenu) {
			System.out.println("== Menu de Prateleiras ==\n" 
					+ "Por favor selecione uma das seguintes opções: \n"
					+ "\t1) Criar nova prateleira\n" 
					+ "\t2) Editar uma prateleira existente\n"
					+ "\t3) Consultar o detalhe de uma prateleira\n" 
					+ "\t4) Remover uma prateleira\n"
					+ "\t5) Voltar ao ecrã anterior\n");
			System.out.print("Escolha uma opção do menu: ");

			int opcao = -1;

			if (sc.hasNextInt()) {
				opcao = sc.nextInt();
				sc.nextLine(); // Limpar o buffer
			} else {
				sc.nextLine(); // Limpar inputs incorretos
			}

			switch (opcao) {
				case 1:
					adicionarPrateleira();
					break;
				case 2:
					editarPrateleira();
					break;
				case 3:
					consultarPrateleira();
					break;
				case 4:
					removerPrateleira();
					break;
				case 5:
					mostrarMenu = false;
					System.out.println("Voltando ao menu principal..");
					break;
				default:
					System.out.println("Opção inválida! Tente novamente!");
			}
		}
	}
	
	private void adicionarPrateleira() {
		
	}
	
	private void editarPrateleira() {
		
	}
	
	private void consultarPrateleira() {
		
	}
	
	public void removerPrateleira() {
		
	}
}
