package pt.upacademy.jseproject.textinterface;

import pt.upacademy.jseproject.textinterface.states.MenuInit;
import pt.upacademy.jseproject.textinterface.states.State;
import pt.upacademy.jseproject.textinterface.states.product.CreateProducts;
import pt.upacademy.jseproject.textinterface.states.product.DeleteProducts;
import pt.upacademy.jseproject.textinterface.states.product.MenuProducts;
import pt.upacademy.jseproject.textinterface.states.product.ReadProducts;
import pt.upacademy.jseproject.textinterface.states.product.UpdateProducts;
import pt.upacademy.jseproject.textinterface.states.shelves.CreateShelves;
import pt.upacademy.jseproject.textinterface.states.shelves.DeleteShelves;
import pt.upacademy.jseproject.textinterface.states.shelves.MenuShelves;
import pt.upacademy.jseproject.textinterface.states.shelves.ReadShelves;
import pt.upacademy.jseproject.textinterface.states.shelves.UpdateShelves;

//1. Create a "wrapper" class that models the state machine
public class TextInterfaceStateMachine {
	// 2. states
	private State[] states = { new MenuInit(), // State 0
			new MenuProducts(), // State 1
			new MenuShelves(), // State 2
			new CreateShelves(), // State 3
			new UpdateShelves(), // State 4
			new ReadShelves(), // State 5
			new DeleteShelves(), // State 6
			new CreateProducts(), // State 7
			new UpdateProducts(), // State 8
			new ReadProducts(), // State 9
			new DeleteProducts() };// State 10
	// 3. transitions
	private int[][] transition = { 
			{ 1, 2 }, 				// State 0
			{ 7, 8, 9, 10, 0 }, 	// State 1
			{ 3, 4, 5, 6, 0 }, 		// State 2
			{ 2 }, 					// State 3
			{ 2 }, 					// State 4
			{ 2 }, 					// State 5
			{ 2 }, 					// State 6
			{ 1 },					// State 7
			{ 1 }, 					// State 8
			{ 1 }, 					// State 9
			{ 1 } 					// State 10
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