package pt.upacademy.jseproject.maven.textinterface;

/**
 * Main
 * com State Machine - Máq. de estados
 */
public class Main {
	public static void main(String[] args) {
		TextInterfaceStateMachine stateMachine = new TextInterfaceStateMachine();
		stateMachine.start();
	}
}


/**
 * Main
 * sem State Machine - Máq. de estados
 */
//public class Main {	
//	public static void main(String[] args) {
//		// Variaveis
//		Scanner sc = new Scanner(System.in);
//		boolean mostrarMenu = true;				 // Variavel que guarda o estado do menu 
//		int opcaoEscolhida = -1;				 
//		ProductManager productManager = new ProductManager();			 // Objeto para o menu dos produtos
//		ShelfManager shelfManager = new ShelfManager();				 // Objeto para o menu das prateleiras
//		
//		while (mostrarMenu) {
//			System.out.println("== Menu Principal ==\n"
//					+ "Por favor selecione uma das seguintes opções: \n"
//					+ "\t1) Listar produtos\n"
//					+ "\t2) Listar prateleiras\n"
//					+ "\t3) Sair\n");
//			System.out.print("Escolha uma opção do menu: ");
//			
//			if (sc.hasNextInt()) {
//		        opcaoEscolhida = sc.nextInt();
//		        sc.nextLine(); 			// Limpar o buffer
//		    } else {		    
//		        sc.nextLine(); 			// Limpar inputs incorretos
//		        opcaoEscolhida = -1; 	// Reset "opcaoEscolhida" para input invalido
//		    }
//			
//			switch (opcaoEscolhida) {
//				case 1:
//					productManager.showMenu();
//					break;
//				case 2:
//					shelfManager.showMenu();
//					break;
//				case 3:
//					mostrarMenu = false;
//					System.out.println("\nFim do Programa");
//					break;
//				default:
//					System.out.println("\nOpção incorreta! Tente novamente!");
//			}
//		}
//		sc.close();		// Fechar o scanner
//	}
//}
