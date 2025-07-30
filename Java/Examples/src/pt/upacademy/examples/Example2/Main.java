package pt.upacademy.examples.Example2;

public class Main {
	public static void main(String[] args) {
		String tempNum = "10 20 30 40 50";
		String[] valores = tempNum.split(" ");
		
		int[] arrayFinal = new int[valores.length];
		
		for (int i = 0; i < valores.length; i++) {
			arrayFinal[i] = Integer.parseInt(valores[i]);
			System.out.print(arrayFinal[i] + " ");
		}
//		System.out.println(Arrays.toString(arrayFinal));
	}
}
