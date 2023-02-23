package Main;

public class Main {

	public static void main(String[] args) {
		new AlgoritmoGeneticoGUI();
		AlgoritmoGenetico algoritmo = new AlgoritmoGenetico(100,100,0.6,0.05,0.001, null, null, null, 0, 0);
		algoritmo.run();
	}

}
