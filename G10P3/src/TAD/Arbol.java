package TAD;

import java.text.DecimalFormat;
import java.util.Random;

public class Arbol {
	
	private Arbol izquierda;
	private Arbol derecha;
	private Nodo nodo;
	private int altura;
	private int numTerminales;
	private int min;
	private int max;
	private int n;
	private Random rand;
	private DecimalFormat f = new DecimalFormat("0");
	
	//Constructor
	public Arbol(int min, int max, String tipo){
		this.min = min;
		this.max = max;
		altura = 0;
		setNumTerminales(0);
		rand = new Random();
		Arbol obj = null;
		switch(tipo) {
		case "Completa":
			obj = inicializacionCompleta(0);

			this.setNumTerminales((int) Math.pow(2,altura));
			break;
		case "Creciente":
			obj= inicializacionCreciente(0);
			this.setNumTerminales(obj.getNumTerminales());
			break;			
		}
		this.nodo = obj.nodo;
		this.izquierda = obj.izquierda;
		this.derecha = obj.derecha;
		this.altura = obj.altura;
		this.n = obj.n;
	}
	
	//Arbol solo con n
	public Arbol(boolean b) {
		izquierda = null;
		derecha  = null;
		nodo = new Nodo(b);
		if(b)
			setNumTerminales(getNumTerminales() + 1);
		altura = 1;
		n = 1;
	}
	
	//Completo
	public Arbol inicializacionCompleta(int p) {
		if(p<max) {
			Arbol a = new Arbol(false);
			a.izquierda = inicializacionCompleta(p+1);
			a.derecha = inicializacionCompleta(p+1);
			a.altura = a.izquierda.altura+1;
			a.n = a.izquierda.n+ a.derecha.n + 1;
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
			a.setNumTerminales(a.izquierda.getNumTerminales() + a.izquierda.getNumTerminales());
			a.altura = Math.max(a.izquierda.altura, a.izquierda.altura) + 1;
			a.n = a.izquierda.n+ a.derecha.n + 1;
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
		if(org.izquierda != null) {
			this.izquierda = new Arbol(org.izquierda);
		}
		else {
			this.izquierda = null;
		}
		if(org.derecha != null) {
			this.derecha = new Arbol(org.derecha);
		}
		else {
			this.derecha = null;
		}
	}
	
	//Funcion para insertar arbol en un punto
	public void insertar(int p, Arbol subA) {
		
	}

	//Funcion para extrar subArbol;
	public Arbol extraer(int p) {
		
		if() {
			
		}
		
		Arbol ret = new Arbol(true);
		
		return ret;
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
	
	//recorrido en orden para construir formula de forma compacta
	public String inOrden() {
		
		String sol = "";
		if(nodo.isTerminal()) {
			sol+=nodo.getValor();
		}	
		else{
			if(izquierda.nodo.isTerminal() && !izquierda.nodo.isX() && derecha.nodo.isTerminal() && !derecha.nodo.isX()) {
				double izq = Double.parseDouble(izquierda.nodo.getValor());
				double der = Double.parseDouble(derecha.nodo.getValor());
				switch(nodo.getValor()) {
				case"+":
					sol=String.valueOf(formato(izq+der));
					break;
				case"-":
					sol=String.valueOf(formato(izq-der));
					break;
				case"*":
					sol=String.valueOf(formato(izq*der));
					break;
				}
			}
			else {
			if(nodo.getValor()!="*")
				sol+= "(";
			sol+=izquierda.inOrden();
			sol+=nodo.getValor();
			sol+=derecha.inOrden();		
			if(nodo.getValor()!="*")
				sol+= ")";
			}
		}
		
		return sol;
	}
	
	//Getters y Setters necesarios
	public int getN() {	return n;}
	public void setN(int n) {this.n = n;}
	public int getNumTerminales() {	return numTerminales;}
	public void setNumTerminales(int numTerminales) {this.numTerminales = numTerminales;}

	public void cambioTerminal(int nextInt) {
		//TODO
	}

	public void permutarNodo(int nextInt) {
		//TODO		
	}

	public void hacerTerminal(int nextInt) {
		// TODO
	}
	
	//Funcion para dar el formato necesario
	public double formato(double num) {
		
		String str = f.format(num);
		double sol = 0;
		
		if( str.indexOf(",") != -1 )
		     str = str.replace(',','.');
		
		sol = Double.parseDouble(str);
		
		return sol;
	}
}
