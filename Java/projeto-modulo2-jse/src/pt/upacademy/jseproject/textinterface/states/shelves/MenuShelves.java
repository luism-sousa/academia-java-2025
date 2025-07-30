package pt.upacademy.jseproject.textinterface.states.shelves;

import pt.upacademy.jseproject.repositories.ShelfRepository;
import pt.upacademy.jseproject.textinterface.states.State;

public class MenuShelves extends State {
	private ShelfRepository DBS = ShelfRepository.getInstance();

	@Override
	public int run() {
//		DBS.getAllIds().forEach(id -> System.out.println("ID do prateleira : " + id));
		
		if (!DBS.isEmpty()) {
			System.out.println("Prateleiras disponiveis: ID: " + DBS.getAllIds());
		}
		
		int[] options = new int[] { 1, 5 };
		System.out.println("== Menu de Prateleiras ==");
		System.out.println("\t1) Criar Prateleira");
		if (!DBS.isEmpty()) {
			options = new int[] { 1, 2, 3, 4, 5 };
			System.out.println("\t2) Editar Prateleira");
			System.out.println("\t3) Consultar Prateleira");
			System.out.println("\t4) Remover Prateleira");
		}
		System.out.println("\t5) Voltar");

		return sc.getValidInt("Selecionar opção : ", options);
	}

}
