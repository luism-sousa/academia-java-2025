package pt.upacademy.jseproject.maven.textinterface.states.product;

import java.util.ArrayList;
import java.util.List;

import pt.upacademy.jseproject.maven.model.Product;
import pt.upacademy.jseproject.maven.model.Shelf;
import pt.upacademy.jseproject.maven.services.ProductService;
import pt.upacademy.jseproject.maven.services.ShelfService;
import pt.upacademy.jseproject.maven.textinterface.states.State;
import pt.upacademy.jseproject.maven.utils.StateContext;
import pt.upacademy.jseproject.maven.utils.VatRate;

public class CreateProducts extends State {
	// Obj. para o Repositório de Produtos
	private StateContext context;
	private ProductService PS;
	private ShelfService SS;

	public CreateProducts(StateContext context, ProductService PS, ShelfService SS) {
		this.context = context;
		this.PS = PS;
		this.SS = SS;
	}

	@Override
	public int run() {
		System.out.println("\n== < Produtos > - [Criar novo produto] ==\n");
		// TODO Validar que o produto existe (ou não) 
		// TODO Validar que os dados introduzidos são validos / não vazios
				
		String productName = sc.getString("Insira o nome do novo Produto: ");
		
		if (PS.findByName(productName) != null) {
			System.out.println("\nEste produto já existe! Não é possível criar");
			return 2;
		}
		
		double productDiscount = sc.getDouble("Introduza o desconto do novo Produto (%): ");
		int inputtedVAT = sc.getInt("Insira o IVA do novo Produto (%): ");
		VatRate productVAT = VatRate.fromInt(inputtedVAT);
		while (productVAT == null) {
			System.out.println("Valor de IVA inválido.");
			inputtedVAT = sc.getInt("Insira o IVA do novo Produto (%): ");
			productVAT = VatRate.fromInt(inputtedVAT);
		}
		double productPVP = sc.getDouble("Introduza o PVP do novo Produto (€): ");
		
		// Criar um objeto da classe "Produto" com os dados para adicionar à lista de Produtos disponiveis
		Product newProduct = new Product(productName, productDiscount, productVAT, productPVP);
		
		PS.create(newProduct);
		context.setCreatedProduct(newProduct);
		
		Shelf shelf = context.getAssociatedShelves();
		
		if (shelf != null) {			
			shelf.setProduct(newProduct);
			
			// Atualizar lista de Shelves do Product
			List<Long> shelvesList = newProduct.getShelvesId();
			if (shelvesList == null) {
				shelvesList = new ArrayList<>();
			}
			
			if (!shelvesList.contains(shelf.getId())) {
				shelvesList.add(shelf.getId());
				newProduct.setShelvesId(shelvesList);
			}
						
			// Atualizar as entidades usando os Services
			SS.update(shelf);
			PS.update(newProduct);
		} else {
			System.out.println("Prateleira não encontrada. Não é possível associar Produto à Prateleira!!");
		}
		
		System.out.println("\nProduto adicionado com sucesso!");
		return 2;
	}
}
