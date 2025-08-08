package pt.upacademy.jseproject.maven.textinterface.states.shelves;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import pt.upacademy.jseproject.maven.model.Product;
import pt.upacademy.jseproject.maven.model.Shelf;
import pt.upacademy.jseproject.maven.services.ProductService;
import pt.upacademy.jseproject.maven.services.ShelfService;
import pt.upacademy.jseproject.maven.textinterface.states.State;
import pt.upacademy.jseproject.maven.utils.StateContext;

public class AssociateProductToShelf extends State {

	private StateContext context;
	private ProductService PS = new ProductService();
	private ShelfService SS = new ShelfService();
	
	public AssociateProductToShelf(StateContext context, ShelfService SS, ProductService PS) {
		this.context = context;
		this.SS = SS;
		this.PS = PS;
	}
	
	@Override
	public int run() {
		Shelf shelf = ensureShelfIsAvailable();
		if (shelf == null) {
			System.out.println("Nenhuma prateleira selecionada. Cancelado");
			return 1;
		}
		
		return handleProductAssociation(shelf);
	}
	
	private Shelf ensureShelfIsAvailable() {
		Shelf shelf = context.getAssociatedShelves();
		if (shelf == null) {
			System.out.println("Necessário selecionar prateleira primeiro: ");
			while (shelf == null) {
				Long shelfId = sc.getLong("Insira o ID da prateleira onde pretende associar o produto (0 para cancelar)");
				if (shelfId == 0) return null;
				
				try {
					shelf = SS.findById(shelfId);
					context.setAssociatedShelves(shelf);
					context.setShelfStatus(StateContext.ShelfStatus.SELECTED);
				} catch (NoSuchElementException e) {
					System.out.println("Prateleira não encontrada. Erro. Tente novamente");
				}
			}
		}
		return shelf;
	}
	
	/**
	 * Gere a lógica de encontrar ou criar um produto e associá-lo à prateleira.
	 */
	private int handleProductAssociation(Shelf shelf) {
		
	    // Verifica se já existe um produto recém-criado no contexto
	    Product productToAssociate = null;

	    // Se não houver produto pré-criado, pergunta ao utilizador
        String answer = sc.getString("\nDeseja associar um produto a esta prateleira? (S/N): ");
        if (!answer.equalsIgnoreCase("s")) {
            System.out.println("\nOperação cancelada. A prateleira permanece sem produto.");
            return 1; // Volta ao menu de prateleiras
        }

        String productName = sc.getString("Insira o nome do Produto a associar: ");
        productToAssociate = PS.findByName(productName);
        
        if (productToAssociate == null) {
            // Produto não existe, prepara o contexto para o estado de criação
            System.out.println("Produto não encontrado. A avançar para a criação de um novo produto...");
            context.setProductName(productName);
            // A prateleira já está no contexto, não precisa de setAssociatedShelves novamente
            return 2; // Transição para o estado CreateProducts
        }
	    
	    // Se chegamos aqui, temos um produto para associar (ou encontrado ou vindo do contexto)
	    return associate(shelf, productToAssociate);
	}

	/**
	 * Efetua a associação entre a prateleira e o produto.
	 */
	private int associate(Shelf shelf, Product product) {
	    // Limpa o produto criado do contexto para evitar re-associações acidentais
	    context.setCreatedProduct(null);
	    
	    // Tenta associar Product à Shelf e verifica se a operação foi bem concretizada
	    boolean success = shelf.setProduct(product);
	    
	    if (success) {
	    	// Adiciona a prateleira à lista de prateleiras do produto
		    List<Long> shelfIds = product.getShelvesId();
		    if (shelfIds == null) {
		        shelfIds = new ArrayList<>();
		    }

		    if (!shelfIds.contains(shelf.getId())) {
		        shelfIds.add(shelf.getId());
		        product.setShelvesId(shelfIds);

		        // Persiste as alterações em ambos os objetos
		        SS.update(shelf);
		        PS.update(product);

		        System.out.println(">> Produto '" + product.getName() + "' associado com sucesso à prateleira ID " + shelf.getId() + ".");
	    }
	    
	    } else {
	        System.out.println("Este produto já está associado a esta prateleira.");
	    }

	    return 1; // Volta ao menu de prateleiras
	}
	
//	@Override
//	public int run() {
//	    Shelf shelf = context.getAssociatedShelves();
//	    ShelfStatus shelfStatus = context.getShelfStatus();
//	    
//	    if (shelfStatus == ShelfStatus.CREATED && shelf != null) {
////	    	System.out.println("\nPretende adicionar um Produto à prateleira? (T/1): ");
//	    }
//	    
//	    else if (shelfStatus == ShelfStatus.SELECTED || shelf == null) {
//	        // Tens de pedir o ID da prateleira ao utilizador para que ele selecione
//	        
//	        // Coloca shelf a null para garantir consistência
//	        shelf = null;
//
//	        while (shelf == null) {
//	            Long shelfId = sc.getLong("Insira o ID da prateleira onde pretende associar o produto: ");
//	            
//	            try {
//	            	shelf = SS.findById(shelfId);
//	            } catch (NoSuchElementException e) {
//	            	System.out.println("Prateleira não encontrada. Tente novamente.");
//				}	        
//	        }
//
//	        context.setAssociatedShelves(shelf);
//	        context.setShelfStatus(ShelfStatus.SELECTED);  // mantém o modo atualizado
//
////	        System.out.println("\nPretende adicionar um Produto à prateleira? (S/N): ");
//	    }
//	    else {
//	        // fallback, caso surja algum valor inesperado
//	        System.out.println("Estado inesperado: prateleira ou modo não definido corretamente.");
//	        return 1; // volta ao menu prateleiras
//	    }
//
//	    String answer = sc.getString("Pretende adicionar um Produto à prateleira? (T/2): ");
//
//	    if (answer.equalsIgnoreCase("n")) {
//	        System.out.println("\nPrateleira mantida sem Produto");
//	        return 1; // volta ao menu shelves
//	    } 
//	    else if (answer.equalsIgnoreCase("s")) {
//	        String productName = sc.getString("Insira o nome do Produto a associar à prateleira: ");
//	        Product existingProduct = PS.findByName(productName);
//	        if (existingProduct != null) {
//	            shelf = context.getAssociatedShelves();  // garante shelf atualizada
//	            shelf.setProduct(existingProduct);
//
//	            List<Long> tempShelves = existingProduct.getShelvesId();
//	            if (tempShelves == null) {
//	                tempShelves = new ArrayList<>();
//	            }
//
//	            if (!tempShelves.contains(shelf.getId())) {
//	                tempShelves.add(shelf.getId());
//	                existingProduct.setShelvesId(tempShelves);
//	                SS.update(shelf);
//		            PS.update(existingProduct);
//
//		            System.out.println(">> Produto associado com sucesso à prateleira.");
//	            }
//
//	            return 1;  // volta ao menu shelves
//	        } else {
//	            context.setProductName(productName);
//	            context.setAssociatedShelves(shelf);
//	            return 2;  // vai para CreateProducts
//	        }
//	    } else {
//	        System.out.println("Resposta inválida! Use S ou N.");
//	        return 3;  // repete o estado AssociateProductToShelf
//	    }
//	}

//	    // Se não existe prateleira no contexto, solicitar ao utilizador
//	    if (shelf == null) {
//	        System.out.println("\n=== Associar Produto a Prateleira ===");
//	        Long shelfId = null;
//	        while (true) {
//	            shelfId = sc.getLong("Insira o ID da prateleira para associar o produto: ");
//	            shelf = SS.findById(shelfId);
//	            if (shelf != null) {
//	                context.setAssociatedShelves(shelf);
//	                break; // obtive prateleira válida, sai do ciclo
//	            } else {
//	                System.out.println("Prateleira não encontrada. Tente novamente.");
//	            }
//	        }
//	    }
//
//	    // Mensagem condicional, opcionalmente podes usar um flag que indique shelf nova/existente:
//	    String message = "Pretende adicionar um Produto à prateleira? (S/N): ";
//
//	    String answer = sc.getString(message);
//
//	    if (answer.equalsIgnoreCase("n")) {
//	        System.out.println("\nPrateleira mantida sem Produto");
//	        return 1; // volta ao menu shelves
//	    } 
//	    else if(answer.equalsIgnoreCase("s")) {
//	        String productName = sc.getString("Insira o nome do Produto a associar à prateleira: ");
//	        Product existingProduct = PS.findByName(productName);
//
//	        if (existingProduct != null) {
//	            // Associação bidirecional
//	            shelf.setProduct(existingProduct);
//	            List<Long> tempShelves = existingProduct.getShelvesId();
//	            if (tempShelves == null) {
//	                tempShelves = new ArrayList<>();
//	            }
//	            if (!tempShelves.contains(shelf.getId())) {
//	                tempShelves.add(shelf.getId());
//	                existingProduct.setShelvesId(tempShelves);
//	            }
//	            
//	            SS.update(shelf);
//	            PS.update(existingProduct);
//
//	            System.out.println(">> Produto associado com sucesso à Prateleira.");
//	            return 1; // volta ao menu shelves
//	        } else {
//	            // produto não existe -> grava dados no context e vai criar novo produto
//	            context.setProductName(productName);
//	            context.setAssociatedShelves(shelf);           
//	            return 2; // vai para CreateProducts
//	        }
//	    } else {
//	        System.out.println("Resposta inválida! Use S ou N.");
//	        return 3; // repete este state
//	    }
//	}

//	@Override
//	public int run() {		
//		Shelf shelf = context.getAssociatedShelves();
//		
//		if (shelf == null) {
//			System.out.println("Nenhuma prateleira selecionada");
//			return 1;
//		}
//		
//		if (shelf.getId() == null) {
//			// Prateleira nova
//			
//		}
//		else {
//			// Prateleira existinte
//			
//		}
//		
////		String answer = sc.getString("Pretende adicionar um Produto à nova Prateleira? (S/N): ");
//		String message = isShelfNew
//	            ? "Pretende adicionar um Produto à nova Prateleira? (S/N): "
//	            : "Pretende adicionar um Produto à prateleira existente? (S/N): ";
//
//	    String answer = sc.getString(message);
//		
//		if (answer.equalsIgnoreCase("n")) {
//			System.out.println("\nPrateleira mantida sem Produto");
//			return 1;
//		} 
//		else if(answer.equalsIgnoreCase("s")) {
//			String productName = sc.getString("Insira o nome do Produto a associar a prateleria: ");
//            Product existingProduct = PS.findByName(productName);
//            if(existingProduct != null) {
//                shelf.setProduct(existingProduct);
//                
//                // Atualizar IDs na "Lista de Shelfs" do Product
//                List<Long> tempShelves = existingProduct.getShelvesId();
//                if (tempShelves == null) {
//                	tempShelves = new ArrayList<>();
//                }
//                
//                if (!tempShelves.contains(shelf.getId())) {
//                	tempShelves.add(shelf.getId());
//                	existingProduct.setShelvesId(tempShelves);
//                }
//                
//                SS.update(shelf);
//                PS.update(existingProduct);
//                
//                System.out.println(">> Produto associado com sucesso à Prateleira.");
//                return 1; // volta ao menu shelves
//            } else {
//                // produto não existe -> grava dados no context
//                context.setProductName(productName);
//                context.setAssociatedShelves(shelf);           
//                return 2; // vai para CreateProducts
//            }
//        }
//        else {
//            System.out.println("Resposta inválida! Use S ou N.");
//            return 3; // repete este state
//        }
//	}
}
