package selecciones;

public class main {

	public static void main() {
		
		int[] individuos = new int[] {1,2,3,4,5,6,7,8,9,10};
		
		seleccionar(individuos,new double[]{0.18,0.16,0.15,0.13,0.11,0.09,0.07,0.06,0.03,0.02});
		for(int i = 0; i<6; ++i) {
			System.out.print(String.valueOf(individuos[i])+ " ");
		}
	}
	
	public static void seleccionar(int[] individuos, double[] fitness) {
		double fitnessTotal = 0;
		double[] probSeleccion = new double[fitness.length+1];
		int[] newIndividuos = individuos;
		double r = 0.1;
		for(int i = 0; i<fitness.length; i++) {
			fitnessTotal += fitness[i];
		}
		probSeleccion[0] = 0;
		for(int i = 1; i<=fitness.length; i++) {
			probSeleccion[i] = fitness[i-1]/fitnessTotal + probSeleccion[i-1];
		}
		
		for(int i = 0; i<individuos.length; i++) {
			double number = (r+i)/6;
			for(int j = 1; j <probSeleccion.length; j++) {
				if(probSeleccion[j-1] < number && number <= probSeleccion[j]) {
					newIndividuos[i] = individuos[j-1];
					break;
				}
			}
		}
		individuos = newIndividuos;
	}


}
