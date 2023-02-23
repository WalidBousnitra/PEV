package cruces;

import java.util.List;

public abstract class AlgoritmosCruce<T>{
	
	public abstract List<T[]> cruzar(T[] padre1, T[] padre2, double p);
}