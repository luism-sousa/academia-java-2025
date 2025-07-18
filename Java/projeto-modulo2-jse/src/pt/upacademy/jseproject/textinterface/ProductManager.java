package pt.upacademy.jseproject.textinterface;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import pt.upacademy.jseproject.model.Product;
import pt.upacademy.jseproject.model.Shelf;

public class ProductManager {
	private List<Product> products = new ArrayList<>();
	private List<Shelf> shelves = new ArrayList<>();
	private Scanner sc;
	
	public ProductManager(Scanner sc) {
		this.sc = sc;
	}
	
	public void showMenu() {
		boolean mostrarMenu = true;

		while (mostrarMenu) {
			System.out.println("== < Menu de Produtos > ==\n" 
					+ "Por favor selecione uma das seguintes opções: \n"
					+ "\t1) Criar novo produto\n" 
					+ "\t2) Editar um produto existente\n"
					+ "\t3) Consultar o detalhe de um produto\n" 
					+ "\t4) Remover um produto\n"
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
					addNewProduct();
					break;
				case 2:
					editProduct();
					break;
				case 3:
					showProduct();
					break;
				case 4:
					removeProduct();
					break;
				case 5:
					mostrarMenu = false;
					System.out.println("Voltando ao menu principal...");
					break;
				default:
					System.out.println("Opção inválida! Tente novamente!");
			}
		}
	}

	// region Class Methods
	public void addNewProduct() {		
		System.out.println("== < Produtos > - [Criar novo produto] ==\n");
		// TODO Validar que o produto existe (ou não) 
		// TODO Validar que os dados introduzidos são validos / não vazios
		
		System.out.print("Insira o nome do novo Produto: ");
		String productName = sc.nextLine();
		
		System.out.print("Introduza o desconto do novo Produto (%): ");
		float productDiscount = sc.nextFloat();
		
		System.out.print("Insira o IVA do novo Produto (%): ");
		int productVAT = sc.nextInt();
		
		System.out.print("Introduza o PVP do novo Produto: ");
		float productPVP = sc.nextFloat();
		
		// Criar um objeto da classe "Produto" com os dados para adicionar à lista de Produtos disponiveis
		Product newProduct = new Product(productName, productDiscount, productVAT, productPVP);
		
		// TODO: Mostrar lista de Shelves disponiveis onde posso adicionar o produto
	}
	
	public void addProductToShelf() {
		
	}

	public void editProduct() {
		// TODO Fazer metodo "editProduct"
	}
	
	public void showProduct() {
		// TODO Fazer metodo "showProduct"
	}
	
	public void removeProduct() {
		// TODO Fazer metodo "removeProduct"
	}
	// endregion
}
