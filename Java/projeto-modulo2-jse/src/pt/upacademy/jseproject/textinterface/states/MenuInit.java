package pt.upacademy.jseproject.textinterface.states;

public class MenuInit extends State {

	@Override
	public int run() {
		System.out.println("Por favor selecione uma das seguintes opções:");
		System.out.println("\t1) Listar produtos");
		System.out.println("\t2) Listar prateleiras");
		System.out.println("\t3) Sair");
		return sc.getValidInt("Selecionar opção : ", 1, 3);
	}

}
