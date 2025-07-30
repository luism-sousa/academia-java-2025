package pt.upacademy.jseproject.textinterface.states.product;

import pt.upacademy.jseproject.repositories.ProductRepository;
import pt.upacademy.jseproject.textinterface.states.State;

public class MenuProducts extends State {
	private ProductRepository DBP = ProductRepository.getInstance();

	@Override
	public int run() {
//		DBP.getAllIds().forEach(id -> System.out.println("ID do produto : " + id));
		
		if (!DBP.isEmpty()) {
			System.out.println("Produtos disponiveis: ID: " + DBP.getAllIds());
		}
		
		int[] options = new int[] { 1, 5 };
		System.out.println("== Menu de Produtos ==");
		System.out.println("\t1) Criar Produtos");
		if (!DBP.isEmpty()) {
			options = new int[] { 1, 2, 3, 4, 5 };
			System.out.println("\t2) Editar Produto");
			System.out.println("\t3) Consultar Produto");
			System.out.println("\t4) Remover Produto");
		}
		System.out.println("\t5) Voltar");

		return sc.getValidInt("Selecionar opção : ", options);

	}

}
