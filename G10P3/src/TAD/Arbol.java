package TAD;

public class Arbol {
	
	private Arbol izquierda;
	private Arbol derecha;
	private Nodo nodo;
	private int altura;
	private int n;
	
	public Arbol() {
		izquierda = null;
		derecha  = null;
		nodo.setTerminal();
	}
	
	public Arbol(int profundidad, int min, int max){
		altura = 0;
		while(altura < min && altura < max) {
			izquierda = new Arbol();
			derecha = new Arbol();			
			altura = Math.max(izquierda.altura, derecha.altura);
		}
	}
	
	//Funcion para insertar arbol en un punto
	public void insertar(int p, Arbol subA) {
		
	}

	//Funcion para extrar subArbol;
	public Arbol extraer(int p) {
		
	}
	
	//Calcular f(x)
	public double calcular(double x) {
		
	}

	public int getN() {	return n;}
	public void setN(int n) {this.n = n;}
}
