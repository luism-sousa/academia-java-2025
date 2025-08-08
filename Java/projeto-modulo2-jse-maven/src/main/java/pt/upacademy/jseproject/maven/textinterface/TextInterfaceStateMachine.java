package pt.upacademy.jseproject.maven.textinterface;

import pt.upacademy.jseproject.maven.services.ProductService;
import pt.upacademy.jseproject.maven.services.ShelfService;
import pt.upacademy.jseproject.maven.textinterface.states.MenuInit;
import pt.upacademy.jseproject.maven.textinterface.states.State;
import pt.upacademy.jseproject.maven.textinterface.states.product.CreateProducts;
import pt.upacademy.jseproject.maven.textinterface.states.product.DeleteProducts;
import pt.upacademy.jseproject.maven.textinterface.states.product.ListProducts;
import pt.upacademy.jseproject.maven.textinterface.states.product.MenuProducts;
import pt.upacademy.jseproject.maven.textinterface.states.product.ReadProducts;
import pt.upacademy.jseproject.maven.textinterface.states.product.UpdateProducts;
import pt.upacademy.jseproject.maven.textinterface.states.shelves.AssociateProductToShelf;
import pt.upacademy.jseproject.maven.textinterface.states.shelves.CreateShelves;
import pt.upacademy.jseproject.maven.textinterface.states.shelves.DeleteShelves;
import pt.upacademy.jseproject.maven.textinterface.states.shelves.ListShelves;
import pt.upacademy.jseproject.maven.textinterface.states.shelves.MenuShelves;
import pt.upacademy.jseproject.maven.textinterface.states.shelves.ReadShelves;
import pt.upacademy.jseproject.maven.textinterface.states.shelves.UpdateShelves;
import pt.upacademy.jseproject.maven.utils.StateContext;

//1. Create a "wrapper" class that models the state machine
public class TextInterfaceStateMachine {
	private StateContext context = new StateContext();
	private ShelfService SS = new ShelfService();
	private ProductService PS = new ProductService();
	
	// 2. states
	private State[] states = { new MenuInit(), 	// State 0
			new MenuProducts(), 				// State 1
			new MenuShelves(context, SS), 					// State 2
			new CreateShelves(context, SS), 				// State 3
			new UpdateShelves(), 				// State 4
			new ReadShelves(),					// State 5
			new ListShelves(),					// State 6
			new DeleteShelves(), 				// State 7
			new AssociateProductToShelf(context, SS, PS),		// State 8
			new CreateProducts(context, PS, SS), 				// State 9
			new UpdateProducts(), 				// State 10
			new ReadProducts(), 				// State 11
			new ListProducts(),					// State 12
			new DeleteProducts() };				// State 13
	// 3. transitions
	private int[][] transition = {
			{ 1, 2 },						// State 0 - MenuInit
			{ 9, 10, 11, 12, 13, 0 },		// State 1 - MenuProducts
			{ 3, 4, 5, 6, 7, 8, 0 },			// State 2 - MenuShelves
			{ 2, 8 },							// State 3 - CreateShelves
			{ 2 },							// State 4 - UpdateShelves
			{ 2 },							// State 5 - ReadShelves
			{ 2 },							// State 6 - ListShelves
			{ 2 },							// State 7 - DeleteShelves
			{ 2, 9, 8 },							// State 8 - AssociateProductToShelf
			{ 2, 1 },							// State 9 - CreateProducts
			{ 1 },							// State 10 - UpdateProducts
			{ 1 },							// State 11 - ReadProducts
			{ 1 },							// State 12 - ListProducts
			{ 1 }							// State 13 - DeleteProducts
	};
	// 4. current
	private int current = 0;

	// 5. All client requests are simply delegated to the current state object
	public void start() {
		
		while(true) {
			int option = states[current].run();
			if (current == 0 && option == 3) {
				System.out.println("Fim do Programa");
				break;
			}
			current = transition[current][option-1];
		}
	}
}

//6. Create a state base class that makes the concrete states interchangeable
//7. The State base class specifies default behavior