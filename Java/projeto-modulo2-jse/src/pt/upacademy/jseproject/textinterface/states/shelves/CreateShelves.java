package pt.upacademy.jseproject.textinterface.states.shelves;

import pt.upacademy.jseproject.model.Shelf;
import pt.upacademy.jseproject.repositories.ShelfRepository;
import pt.upacademy.jseproject.textinterface.states.State;

public class CreateShelves extends State {
	// Obj. para o Repositório de Prateleiras
	private ShelfRepository DBS = ShelfRepository.getInstance();

//	@Override
//	public int on() {
//		System.out.println("Criar prateleira");
//		int capacity = sc.getInt("Insira a capacidade");
//		float dailyPrice = sc.getFloat("Insira o preco diario");
//		Shelf shelf = new Shelf(capacity, dailyPrice);
//		DBS.addEntity(shelf);
//		
//		return 1;
//	}

	@Override
	public int run() {
		System.out.println("\n1== < Prateleira > - [Criar nova prateleira] ==\n");
		int shelfCapacity = sc.getInt("Insira o capacidade da nova Prateleira: ");		
		double rentPrice = sc.getDouble("Insira o preço de aluguer da nova Prateleira (por dia) : ");
		
		// Criação da nova prateleira com os parametros recebidos
		Shelf newShelf = new Shelf(shelfCapacity, rentPrice);
		
		DBS.addEntity(newShelf);
		System.out.println("\nPrateleira adicionada com sucesso!\n");
		return 1;
		
//		String answer;
//		// Validação para caso o User não coloque a letra "S" ou "N"
//		// ignoreCase caso o User introduza letra maiscula/minuscula
//		do {
//			System.out.print("Pretende adicionar um Produto à nova Prateleira? (S/N): ");
//			answer = sc.nextLine();
//			// Adição de um 'Product' à nova 'Shelf' (caso o User responda 'sim')
//			if (answer.equalsIgnoreCase("s")) {
//				// Verificar se o 'Product' existe antes de adicionar à nova 'Shelf'
//				
//				// > Colocar depois de fazer as verificaçõe <
//				// shelfRepository.addEntity(newShelf);
//			} else if (answer.equalsIgnoreCase("n")) {
//				shelfRepository.addEntity(newShelf);
//				System.out.println("\nPrateleira adicionada com sucesso!\n");
//			}			
//			// Mensagem de erro caso a resposta não seja "S" ou "N"
//			else {
//				System.out.println("Resposta inválida! Responda 'S' ou 'N'");
//			}
//		} while (!answer.equalsIgnoreCase("s") && !answer.equalsIgnoreCase("n"));	
	}
}
