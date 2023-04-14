package TAD;

public class Arbol {
	
	private Arbol izquierda;
	private Arbol derecha;
	private Nodo nodo;
	private int altura;
	private int n;
	
	public Arbol(String valor) {
		izquierda = null;
		derecha  = null;
		nodo = new Nodo(valor);
	}
	
	public Arbol(int profundidad, int min, int max){
		altura = 0;
		while(altura < min && altura < max) {
			izquierda = new Arbol();
			derecha = new Arbol();			
			altura = Math.max(izquierda.altura, derecha.altura);
		}
	}
	
	//constructor de copia
	public Arbol(Arbol org) {
		Arbol dest = new Arbol();
		if(izquierda == null || derecha == null) {
			return new Arbol(nodo.getValor());
		}
	}
	
	//Funcion para insertar arbol en un punto
	public void insertar(int p, Arbol subA) {
		
	}

	//Funcion para extrar subArbol;
	public Arbol extraer(int p) {
		return derecha;
		
	}
	
	//Calcular f(x)
	public double calcular(double x) {
		
		double sol = 0;
		
		if(nodo.isTerminal()) {
			if(nodo.isX())
				return x;
			else
				return Double.parseDouble(nodo.getValor());
		}
		else{
			double izq = izquierda.calcular(x);
			double der = derecha.calcular(x);
			
			switch(nodo.getValor()) {
			case"+":
				sol=izq+der;
				break;
			case"-":
				sol=izq-der;
				break;
			case"*":
				sol=izq*der;
				break;
			}
			
		}
		
		return sol;
	}
	
	//recorrido en orden para construir formula
	public String inOrden() {
		
		String sol = "";
		
		sol+= "(" + izquierda.inOrden() + ")";
		sol+=nodo;
		sol+= "(" + derecha.inOrden()+ ")";
		
		return sol;
	}
	

	public int getN() {	return n;}
	public void setN(int n) {this.n = n;}
}
