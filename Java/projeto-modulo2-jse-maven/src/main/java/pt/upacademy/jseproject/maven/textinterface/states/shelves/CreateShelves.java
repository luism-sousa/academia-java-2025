package pt.upacademy.jseproject.maven.textinterface.states.shelves;

import pt.upacademy.jseproject.maven.model.Shelf;
import pt.upacademy.jseproject.maven.services.ShelfService;
import pt.upacademy.jseproject.maven.textinterface.states.State;
import pt.upacademy.jseproject.maven.utils.StateContext;
import pt.upacademy.jseproject.maven.utils.StateContext.ShelfStatus;

public class CreateShelves extends State {
	// Obj. para o Serviço de Prateleiras/Produtos
	private ShelfService SS = new ShelfService();
	private StateContext context = new StateContext();

	public CreateShelves(StateContext context, ShelfService SS) {
		this.context = context;
		this.SS = SS;
	}

	@Override
	public int run() {
		System.out.println("\n== < Prateleira > - [Criar nova prateleira] ==\n");
		int shelfCapacity = sc.getInt("Insira o capacidade da nova Prateleira: ");		
		double rentPrice = sc.getDouble("Insira o preço de aluguer da nova Prateleira (por dia) : ");
		
		// Criação da nova prateleira com os parametros recebidos
		Shelf newShelf = new Shelf(shelfCapacity, rentPrice);
		SS.create(newShelf);
		context.setAssociatedShelves(newShelf);
		context.setShelfStatus(ShelfStatus.CREATED);
		System.out.println("\nPrateleira adicionada com sucesso!\n");
		
		// Validação para caso o User não coloque a letra "S" ou "N"
		// ignoreCase caso o User introduza letra maiscula/minuscula
//		String answer = sc.getString("Pretende adicionar um Produto à nova Prateleira? (S/N): ");
//		do {
//			// Adição de um 'Product' à nova 'Shelf' (caso o User responda 'sim')
//			if (answer.equalsIgnoreCase("s")) {
//				// Verificar se o 'Product' existe antes de adicionar à nova 'Shelf'
//				try {
//					String productName = sc.getString("Insira o nome do Produto a associar a prateleria: ");
//					Product product;
//					Product existingProduct = PS.findByName(productName);
//					
//					if (existingProduct != null) {						
//						System.out.println("\nProduto encontrado. A associar produto à nova prateleira..");
//						product = existingProduct;
//						newShelf.setProduct(product);
//						product.getShelvesId().add(newShelf.getId());
//					} else {
//						System.out.println("\nProduto não encontrado. Criando prateleira sem produto..");
//					}
//										
//					SS.create(newShelf);					
//					System.out.println("\nPrateleira adicionada com sucesso!\n");
//				} catch (Exception e) {
//					System.out.println("Erro ao criar e adicionar Produto à prateleira: " + e.getMessage());
//				}
//				// > Colocar depois de fazer as verificaçõe <
//				// shelfRepository.addEntity(newShelf);
//			} else if (answer.equalsIgnoreCase("n")) {
//				SS.create(newShelf);
//				System.out.println("\nPrateleira adicionada com sucesso!\n");
//			}			
//			// Mensagem de erro caso a resposta não seja "S" ou "N"
//			else {
//				System.out.println("Resposta inválida! Responda 'S' ou 'N'");
//			}
//		} while (!answer.equalsIgnoreCase("s") && !answer.equalsIgnoreCase("n"));
		
		return 2;
	}
}
