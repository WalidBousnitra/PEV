package TAD;

import java.util.Random;

public class Arbol {
	
	private Nodo nodo;
	private Arbol izquierda;
	private Arbol derecha;
	private int n;
	private int altura;
	private int numTerminales;
	private int numFunciones;
	private Random rand;
	
	//Constructor
	public Arbol(int min, int max, String tipo){
		rand = new Random();
		Arbol obj = null;
		switch(tipo) {
		case "Completa":
			obj = inicializacionCompleta(0,max);
			break;
		case "Creciente":
			obj= inicializacionCreciente(0,min,max);
			break;			
		}
		nodo = obj.nodo;
		izquierda = obj.izquierda;
		derecha = obj.derecha;
		n = obj.n;
		altura = obj.altura;
		numTerminales = obj.numTerminales;
		setNumFunciones(obj.getNumFunciones());
	}
	
	public void print(String prefix, Arbol n, boolean isLeft) {
        if (n != null) {
            System.out.println (prefix + (isLeft ? "|__ " : "\\__ ") +  n.nodo.getValor());
            print(prefix + (isLeft ? "|   " : "    "), n.izquierda, true);
            print(prefix + (isLeft ? "|   " : "    "), n.derecha, false);
        }
    }

	
	//Arbol solo con un solo nodo
	public Arbol(boolean b) {
		nodo = new Nodo(b);
		izquierda = null;
		derecha  = null;
		n = 1;
		altura = 1;
		numTerminales = (b)?1:0;
		numFunciones = (b)?0:1;
	}
	
	//Completo
	public Arbol inicializacionCompleta(int p, int max) {
		if(p<max-1) {
			Arbol a = new Arbol(false);
			a.izquierda = inicializacionCompleta(p+1, max);
			a.derecha = inicializacionCompleta(p+1, max);
			a.n = a.izquierda.n + a.derecha.n + 1;
			a.altura = a.izquierda.altura+1;
			a.setNumTerminales(a.izquierda.getNumTerminales() + a.derecha.getNumTerminales());
			a.setNumFunciones(a.izquierda.getNumFunciones() + a.derecha.getNumFunciones()+1);
			return a;
		}
		else {
			return new Arbol(true);
		}
	}
	
	//Creciente
	public Arbol inicializacionCreciente(int p, int min, int max) {
		Arbol a = null;
		if(p<min) {
			a = new Arbol(false);
			a.izquierda = inicializacionCreciente(p+1,min,max);
			a.derecha = inicializacionCreciente(p+1,min,max);
		}
		else if(p<max-1) {
			a = new Arbol(rand.nextBoolean());
			if(!a.nodo.isTerminal()) {
				a.izquierda = inicializacionCreciente(p+1,min,max);
				a.derecha = inicializacionCreciente(p+1,min,max);
			}
		}
		else {
			return new Arbol(true);
		}
		if(!a.nodo.isTerminal()) {
			a.n = a.izquierda.n+ a.derecha.n + 1;
			a.altura = Math.max(a.izquierda.altura, a.izquierda.altura) + 1;
			a.numTerminales = a.izquierda.numTerminales + a.derecha.numTerminales;
			a.setNumFunciones(a.izquierda.getNumFunciones() + a.derecha.getNumFunciones()+1);
		}
		return a; 
	}	
	
	//constructor de copia
	public Arbol(Arbol org) {
		if(org != null) {
			nodo = new Nodo(org.nodo);
			this.izquierda = (org.izquierda!=null)?new Arbol(org.izquierda):null;
			this.derecha = (org.derecha!=null)?new Arbol(org.derecha):null;
			n = org.n;
			altura = org.altura;
			numTerminales = org.numTerminales;
			setNumFunciones(org.getNumFunciones());
		}
	}
	
	//Funcion para insertar arbol en un punto
	public void insertar(int p, Arbol subA) {
		if(nodo.isTerminal() || p == izquierda.n+1) {
			nodo = new Nodo(subA.nodo);
			izquierda = null;
			derecha = null;
			if(!nodo.isTerminal()) {
				izquierda = new Arbol(subA.izquierda);
				derecha = new Arbol(subA.derecha);
			}
			n = subA.n;
			numTerminales = subA.numTerminales;
			altura = subA.altura;
			return;
		}
		else if(p<=izquierda.n){
			izquierda.insertar(p,subA);
		}
		else {
			derecha.insertar(p-(izquierda.n+1),subA);
		}
		n = izquierda.n+ derecha.n + 1;
		altura = Math.max(izquierda.altura, izquierda.altura) + 1;
		numTerminales = izquierda.numTerminales + derecha.numTerminales;
	}

	//Funcion para extrar subArbol;
	public Arbol extraer(int p) {
		if(nodo.isTerminal() || p==(this.izquierda.n+1)) {
			return new Arbol(this);
		}
		else if(p<=izquierda.n){
			return izquierda.extraer(p);
		}
		else {
			return derecha.extraer(p-(this.izquierda.n+1));
		}
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
				int izq = Integer.parseInt(izquierda.nodo.getValor());
				int der = Integer.parseInt(derecha.nodo.getValor());
				switch(nodo.getValor()) {
				case"+":
					sol+=String.valueOf(izq+der);
					break;
				case"-":
					sol+=String.valueOf(izq-der);
					break;
				case"*":
					sol+=String.valueOf(izq*der);
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

	public void cambioTerminal(int p) {
		if(nodo.isTerminal()) {
			nodo.setTerminal(nodo.getValor());
		}
		else if(p<=izquierda.numTerminales){
			izquierda.cambioTerminal(p);
		}
		else {
			derecha.cambioTerminal(p-(izquierda.numTerminales));
		}
	}

	public void permutarNodo(int p) {
		if(nodo.isTerminal() || p==izquierda.n+1) {
			Arbol aux = new Arbol(izquierda);
			izquierda = new Arbol(derecha);
			derecha = new Arbol(aux);
		}
		else if(p<=izquierda.n){
			izquierda.permutarNodo(p);
		}
		else {
			derecha.permutarNodo(p-izquierda.n+1);
		}
	}

	public void hacerTerminal(int p) {
		if(p==izquierda.numFunciones+1) {
			nodo.setTerminal();
			izquierda = null;
			derecha = null;
			n = 1;
			altura = 1;
			numTerminales = 1;
			numFunciones = 0;
			return;
		}
		else if(p<=izquierda.getNumFunciones()){
			izquierda.hacerTerminal(p);
		}
		else {
			derecha.hacerTerminal(p-(izquierda.getNumFunciones()+1));
		}
		n = izquierda.n+derecha.n;
		altura = Math.max(izquierda.altura, derecha.altura);
		numTerminales = izquierda.numTerminales + derecha.numTerminales;
		setNumFunciones(izquierda.getNumFunciones()+derecha.getNumFunciones()+1);
	}

	public Arbol getIzquierda() {
		return izquierda;
	}

	public void setIzquierda(Arbol izquierda) {
		this.izquierda = izquierda;
	}

	public Nodo getNodo() {
		return nodo;
	}

	public void setNodo(Nodo nodo) {
		this.nodo = nodo;
	}

	public void cambioFuncion(int p) {
		if(p==izquierda.numFunciones+1) {
			nodo.setFuncion(nodo.getValor());
		}
		else if(p<=izquierda.getNumFunciones()){
			izquierda.cambioFuncion(p);
		}
		else {
			derecha.cambioFuncion(p-(izquierda.getNumFunciones()+1));
		}
	}

	public int getNumFunciones() {
		return numFunciones;
	}

	public void setNumFunciones(int numFunciones) {
		this.numFunciones = numFunciones;
	}
}
