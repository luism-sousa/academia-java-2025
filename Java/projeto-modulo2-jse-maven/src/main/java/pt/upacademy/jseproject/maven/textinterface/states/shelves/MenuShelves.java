package pt.upacademy.jseproject.maven.textinterface.states.shelves;

import pt.upacademy.jseproject.maven.services.ShelfService;
import pt.upacademy.jseproject.maven.textinterface.states.State;
import pt.upacademy.jseproject.maven.utils.StateContext;
import pt.upacademy.jseproject.maven.utils.StateContext.ShelfStatus;

public class MenuShelves extends State {
	// Obj. para o Serviço de Prateleiras/Produtos
	private ShelfService SS;
	private StateContext context;
	
	public MenuShelves(StateContext context, ShelfService SS) {
		this.context = context;
		this.SS = SS;
	}
	
	@Override
	public int run() {
		context.setAssociatedShelves(null);
		context.setShelfStatus(ShelfStatus.SELECTED);
		if (!SS.isEmpty()) {
			System.out.println("Prateleiras disponiveis: ID: " + SS.findAll());
		}
		
		int[] options = new int[] { 1, 7 };
		System.out.println("== Menu de Prateleiras ==");
		System.out.println("\t1) Criar Prateleira");
		if (!SS.isEmpty()) {
			options = new int[] { 1, 2, 3, 4, 5, 6, 7 };
			System.out.println("\t2) Editar Prateleira");
			System.out.println("\t3) Consultar Prateleira");
			System.out.println("\t4) Listar todas as prateleiras");
			System.out.println("\t5) Remover Prateleira");
			System.out.println("\t6) Associar Produto a Prateleira");
		}
		System.out.println("\t7) Voltar");

		return sc.getValidInt("Selecionar opção : ", options);
	}
}
