package pt.upacademy.parouimpar;

public class ScannerTest {
	ScannerUtils sc = new ScannerUtils();
	private int min = 1;
	private int max = 50;
	
	public void parOuImpar() {
		System.out.println(sc.getValidInt("Escreva um numero.", min, max));
		
	}
	
	public static void main(String[] args) {
		ScannerTest main = new ScannerTest();
		main.parOuImpar();
	}

}
