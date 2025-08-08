package pt.upacademy.jseproject.maven.textinterface.states.product;

import pt.upacademy.jseproject.maven.repositories.ProductRepository;
import pt.upacademy.jseproject.maven.textinterface.states.State;

public class MenuProducts extends State {
	private ProductRepository DBP = ProductRepository.getInstance();

	@Override
	public int run() {		
		if (!DBP.isEmpty()) {
			System.out.println("Produtos disponiveis: ID: " + DBP.getAllIds());
		}
		
		int[] options = new int[] { 1, 6 };
		System.out.println("== Menu de Produtos ==");
		System.out.println("\t1) Criar Produtos");
		if (!DBP.isEmpty()) {
			options = new int[] { 1, 2, 3, 4, 5, 6 };
			System.out.println("\t2) Editar Produto");
			System.out.println("\t3) Consultar Produto");
			System.out.println("\t4) Listar todos os Produtos");
			System.out.println("\t5) Remover Produto");
		}
		System.out.println("\t6) Voltar");

		return sc.getValidInt("Selecionar opção : ", options);
	}

}
