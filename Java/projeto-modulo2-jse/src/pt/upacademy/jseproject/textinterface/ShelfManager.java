package pt.upacademy.jseproject.textinterface;

import java.util.NoSuchElementException;
import java.util.Scanner;

import pt.upacademy.jseproject.model.Shelf;
import pt.upacademy.jseproject.repositories.ShelfRepository;

public class ShelfManager {
	private ShelfRepository shelfRepository = ShelfRepository.getInstance();
	private Scanner sc = new Scanner(System.in);
	
	public void showMenu() {
		boolean mostrarMenu = true;

		while (mostrarMenu) {
			System.out.println("== < Menu de Prateleiras > ==\n" 
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
					addNewShelf();
					break;
				case 2:
					editShelf();
					break;
				case 3:
					showShelf();
					break;
				case 4:
					removeShelf();
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
	
	// TODO Validar que a capacidade e o preço são validos
	public void addNewShelf() {
		System.out.println("== < Prateleira > - [Criar nova prateleira] ==\n");
		System.out.print("Insira o capacidade da nova Prateleira: ");
		int shelfCapacity = sc.nextInt();
		
		System.out.print("Insira o preço de aluguer da nova Prateleira (por dia) : ");
		double rentPrice = sc.nextDouble();
		
		sc.nextLine(); // Limpar o buffer;
		
		// Criação da nova prateleira com os parametros recebidos
		Shelf newShelf = new Shelf(shelfCapacity, rentPrice);
		
		String answer;
		// Validação para caso o User não coloque a letra "S" ou "N"
		// ignoreCase caso o User introduza letra maiscula/minuscula
		do {
			System.out.print("Pretende adicionar um Produto à nova Prateleira? (S/N): ");
			answer = sc.nextLine();
			// Adição de um 'Product' à nova 'Shelf' (caso o User responda 'sim')
			if (answer.equalsIgnoreCase("s")) {
				// Verificar se o 'Product' existe antes de adicionar à nova 'Shelf'
				
				// > Colocar depois de fazer as verificaçõe <
				// shelfRepository.addEntity(newShelf);
			} else if (answer.equalsIgnoreCase("n")) {
				shelfRepository.addEntity(newShelf);
				System.out.println("\nPrateleira adicionada com sucesso!\n");
			}			
			// Mensagem de erro caso a resposta não seja "S" ou "N"
			else {
				System.out.println("Resposta inválida! Responda 'S' ou 'N'");
			}
		} while (!answer.equalsIgnoreCase("s") && !answer.equalsIgnoreCase("n"));		
	}
	
	public void editShelf() {
		
	}
	
	public void showShelf() {		
		if (shelfRepository.isEmpty()) {
			System.out.println("\nNão existem prateleira na base de dados!\n");
			return;
		} 
		
		System.out.print("Introduza o ID da prateleira que pretende consultar: ");
		Long id = null;
		
		if (sc.hasNextLong()) {
			id = sc.nextLong();
			sc.nextLine(); // Limpar buffer
		} else {
			System.out.println("\nID inválido. Deverá ser um número!\n");
			sc.nextLine(); // Limpar buffer
			return;
		}
		
		try {
			Shelf foundShelf = (Shelf) shelfRepository.getById(id);
			System.out.println(foundShelf.toString());
		} catch (NoSuchElementException e) {
			System.out.println("\nPrateleira com ID: " + id + " não encontrada!\n");
		}		
	}
	
//	public void showAllShelves() {
//		List<Shelf> allShelves = shelfRepository.getAllShelves();
//		
//		if (allShelves.isEmpty()) {
//			System.out.println("\nNão existem prateleira na base de dados!\n");
//			return;
//		} 
//		
//		for (Shelf shelf : allShelves) {
//			System.out.println(shelf);
//		}
//	}
	
	public void removeShelf() {
//		if (shelfRepository.isEmpty()) {
//			System.out.println("\nNão existem prateleira na base de dados!\n");
//			return;
//		} 
//		
//		shelfRepository.getAllIds().forEach((Long id) -> {
//			System.out.println("Prateleira");
//		});
//		shelfRepository.remove(null);
		
		if (shelfRepository.isEmpty()) {
			System.out.println("\nNão existem prateleira na base de dados! Impossível remover\n");
			return;
		} 
		
		System.out.print("Introduza o ID da prateleira que pretende remover: ");
		Long id = null;
		
		if (sc.hasNextLong()) {
			id = sc.nextLong();
			sc.nextLine(); // Limpar buffer
		} else {
			System.out.println("\nID inválido. Deverá ser um número!\n");
			sc.nextLine(); // Limpar buffer
			return;
		}
		
		try {
			Shelf foundShelf = shelfRepository.getById(id);
//			shelfRepository.removeEntity(foundShelf);
			System.out.println("\nPrateleira com ID: " + id + " removida com sucesso!\n");
		} catch (NoSuchElementException e) {
			System.out.println("\nPrateleira com ID: " + id + " não encontrada! Prateleira não removida\n");
		}
	}
}
