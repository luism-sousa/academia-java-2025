package pt.upacademy.jseproject.textinterface.states.product;

import pt.upacademy.jseproject.model.Product;
import pt.upacademy.jseproject.repositories.ProductRepository;
import pt.upacademy.jseproject.textinterface.states.State;

public class CreateProducts extends State {
	// Obj. para o Repositório de Produtos
	private ProductRepository DBP = ProductRepository.getInstance();

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
		Product newProduct = new Product(productName, productDiscount, productVAT, productPVP);
		DBP.addEntity(newProduct);
		System.out.println("\nProduto adicionado com sucesso!\n");
		return 1;
	}
}
