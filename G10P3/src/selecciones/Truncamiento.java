package selecciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Individuo.Individuo;

public class Truncamiento extends AlgoritmoSeleccion{

	private Random rand = new Random();

	@Override
	public <T> List<Individuo<T>> seleccionar(List<Individuo<T>> individuos, double[] fitness) {
		
		Collections.sort(individuos);
		Collections.reverse(individuos);
		double r = rand.nextDouble(0.1,0.5);
		List<Individuo<T>> newIndividuos = new ArrayList<Individuo<T>>(individuos.size());
		
		for(int i = 0; i<(int)(r*individuos.size());i++) {
			newIndividuos.add(crear(individuos.get(i)));
		}
		
		for(int i = (int) (r*individuos.size()); i<individuos.size();i++) {
			newIndividuos.add(crear(individuos.get(i-(int) (r*individuos.size()))));
		}
		return newIndividuos;
	}
}
