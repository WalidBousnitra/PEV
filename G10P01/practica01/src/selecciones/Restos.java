package selecciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Individuos.Individuo;

public class Restos extends AlgoritmoSeleccion{
	
	private int k;
	private Random rand = new Random();
	
	public Restos(String funcion) {
		super(funcion);
	}

	@Override
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos, double[] fitness) {
		
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
		List<Individuo<T>> restos = new ArrayList<Individuo<T>>(nIndiv+1);
		double[] restosFitness = new double[nIndiv+1];
		int pos = 0, pos2=0;
		for(int i = 1; i<probSeleccion.length; ++i) {
			if(!seleccionado[i-1] && probSeleccion[i]>=1) {
				newIndividuos.add(pos, crear(individuos.get(i-1)));
				seleccionado[i-1] = true;
				pos++;
			}else if (!seleccionado[i-1]){
				restos.add(pos2,crear(individuos.get(i-1)));
				restosFitness[pos2] = fitness[i-1];
				pos2++;
			}
		}
		
		switch(rand.nextInt(0,5)) {
			case 0:
				EstocasticoUni metodo = new EstocasticoUni(getFuncion());
				restos = metodo.seleccionar(restos, restosFitness);
				break;
			case 1:
				Ruleta metodo2 = new Ruleta(getFuncion());
				restos = metodo2.seleccionar(restos, restosFitness);
				break;
			case 2:
				TorneoDet metodo3= new TorneoDet(getFuncion());
				restos = metodo3.seleccionar(restos, restosFitness);
				break;
			case 3:
				TorneoProb metodo4 = new TorneoProb(getFuncion());
				restos = metodo4.seleccionar(restos, restosFitness);
				break;
			case 4:
				Truncamiento metodo5 = new Truncamiento(getFuncion());
				restos = metodo5.seleccionar(restos, restosFitness);
				break;
		}
		
		newIndividuos.addAll(restos);
		
		return newIndividuos;
	}

}