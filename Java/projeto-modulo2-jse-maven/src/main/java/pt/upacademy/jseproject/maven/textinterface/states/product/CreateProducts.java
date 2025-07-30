package pt.upacademy.jseproject.maven.textinterface.states.product;

import pt.upacademy.jseproject.maven.model.Product;
import pt.upacademy.jseproject.maven.services.ProductService;
import pt.upacademy.jseproject.maven.textinterface.states.State;

public class CreateProducts extends State {
	// Obj. para o Repositório de Produtos
	private ProductService PS = new ProductService();
	private Product newProduct;

	@Override
	public int run() {
		System.out.println("\n== < Produtos > - [Criar novo produto] ==\n");
		// TODO Validar que o produto existe (ou não) 
		// TODO Validar que os dados introduzidos são validos / não vazios
				
		String productName = sc.getString("Insira o nome do novo Produto: ");
		double productDiscount = sc.getDouble("Introduza o desconto do novo Produto (%): ");
		int productVAT = sc.getInt("Insira o IVA do novo Produto (%): ");
		double productPVP = sc.getDouble("Introduza o PVP do novo Produto (€): ");
		
		// Criar um objeto da classe "Produto" com os dados para adicionar à lista de Produtos disponiveis
		newProduct = new Product(productName, productDiscount, productVAT, productPVP);
		PS.create(newProduct);
		System.out.println("\nProduto adicionado com sucesso!");
		return 1;
	}
	
	public Product getNewShelfProduct() {
		return newProduct;
	}
}
