package TAD;

import java.util.Random;

public class Arbol {
	
	private Arbol izquierda;
	private Arbol derecha;
	private Nodo nodo;
	private int altura;
	private int min;
	private int max;
	private int n;
	private Random rand;
	
	//Constructor
	public Arbol(int min, int max, int tipo){
		this.min = min;
		this.max = max;
		rand = new Random();
		switch(tipo) {
		case 0:
			Arbol obj = inicializacionCompleta(0);
			this.nodo = obj.nodo;
			this.izquierda = obj.izquierda;
			this.derecha = obj.derecha;
			break;
		case 1:
			break;
		case 2:
			break;
			
		}
	}
	
	//Arbol solo con n
	public Arbol(boolean b) {
		izquierda = null;
		derecha  = null;
		nodo = new Nodo(b);
	}
	
	//Completo
	public Arbol inicializacionCompleta(int p) {
		if(p<max) {
			Arbol a = new Arbol(false);
			a.izquierda = inicializacionCompleta(p+1);
			a.derecha = inicializacionCompleta(p+1);
			return a;
		}
		else {
			return new Arbol(true);
		}
	}
	
	//Creciente
	public Arbol inicializacionCreciente(int p) {
		
		if(p<min) {
			Arbol a = new Arbol(false);
			a.izquierda = inicializacionCreciente(p+1);
			a.derecha = inicializacionCreciente(p+1);
			return a;
		}
		else if(p<max) {
			Arbol a = new Arbol(rand.nextBoolean());
			if(!a.nodo.isTerminal()) {
				a.izquierda = inicializacionCreciente(p+1);
				a.derecha = inicializacionCreciente(p+1);
			}
			return a;
		}
		else {
			return new Arbol(true);
		}
	}	
	
	//constructor de copia
	public Arbol(Arbol org) {
		this.nodo = new Nodo(org.nodo);
		if(org.izquierda == null && org.derecha == null) {
			this.izquierda = null;
			this.derecha  = null;			
		}
		else if(org.izquierda != null) {
			this.izquierda = new Arbol(org.izquierda);
		}
		else if(org.derecha != null) {
			this.derecha = new Arbol(org.derecha);
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
