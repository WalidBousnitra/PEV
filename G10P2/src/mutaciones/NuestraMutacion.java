package mutaciones;

import Individuos.Individuo;

public class NuestraMutacion<T> extends AlgoritmosMutacion<T>{

	public NuestraMutacion(double probMutacion) {
		super(probMutacion);
	}

	@Override
	public void cromosoma(Individuo<T> individuo) {
		
		//Seleccionamos los 2 utlimos elementos
		T pos1 = individuo.getCromosoma().get(individuo.getCromosoma().size()-1);
		T pos2 = individuo.getCromosoma().get(individuo.getCromosoma().size()-2);
		
		//Hcemos una rotacion ciruclar hacia la derecha de 2 posiciones
		individuo.getCromosoma().remove(individuo.getCromosoma().size()-1);
		individuo.getCromosoma().remove(individuo.getCromosoma().size()-1);
		individuo.getCromosoma().add(0,pos2);
		individuo.getCromosoma().add(0,pos1);
	}

}
