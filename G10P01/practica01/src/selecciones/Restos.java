package selecciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Individuos.Individuo;

public class Restos extends AlgoritmoSeleccion{
	
	private int k;
	private Random rand = new Random();
	
	@Override
	public <T> void seleccionar(List<Individuo<T>> individuos, double[] fitness) {
		
		double fitnessTotal = 0;
		double[] probSeleccion = new double[fitness.length+1];
		boolean[] seleccionado = new boolean[individuos.size()];

		List<Individuo<T>> newIndividuos = new ArrayList<Individuo<T>>(individuos.size());
		k = individuos.size();
		int nIndiv = 0;
		
		for(int i = 0; i<fitness.length; i++) {
			fitnessTotal += fitness[i];
		}
		for(int i = 0; i<fitness.length; i++) {
			probSeleccion[i] = fitness[i]/fitnessTotal*k;
			nIndiv++;
		}
		List<Individuo<T>> restos = new ArrayList<Individuo<T>>(nIndiv);
		double[] restosFitness = new double[nIndiv];
		int pos = 0, pos2=0;
		for(int i = 0; i<probSeleccion.length; ++i) {
			if(probSeleccion[i]>=1) {
				newIndividuos.add(pos, individuos.get(i));
				seleccionado[i] = true;
				pos++;
			}else {
				restos.add(pos2,individuos.get(i));
				restosFitness[pos2] = fitness[i];
				pos2++;
			}
		}
		
		switch(rand.nextInt(0,5)) {
			case 0:
				EstocasticoUni metodo = new EstocasticoUni();
				metodo.seleccionar(restos, restosFitness);
				break;
			case 1:
				Ruleta metodo2 = new Ruleta();
				metodo2.seleccionar(restos, restosFitness);
				break;
			case 2:
				TorneoDet metodo3= new TorneoDet();
				metodo3.seleccionar(restos, restosFitness);
				break;
			case 3:
				TorneoProb metodo4 = new TorneoProb();
				metodo4.seleccionar(restos, restosFitness);
				break;
			case 4:
				Truncamiento metodo5 = new Truncamiento();
				metodo5.seleccionar(restos, restosFitness);
				break;
		}
		
		for(int i = 0; i< restos.size(); i++) {
			newIndividuos.add(pos, restos.get(i));
			pos++;
		}
		
		individuos.clear();
		individuos = newIndividuos;
	}

}