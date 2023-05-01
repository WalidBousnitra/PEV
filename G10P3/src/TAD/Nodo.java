package TAD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Nodo {

	private Random rand = new Random();
	private List<String> funciones = new ArrayList<String>(Arrays.asList("+", "-", "*"));
	private List<String> terminales = new ArrayList<String>(Arrays.asList("x","-2","-1","0","1","2"));
	private String valor;
	
	//Constructor
	public Nodo(boolean b) {
		if(b)
			valor = terminales.get(rand.nextInt(0,6));
		else
			valor = funciones.get(rand.nextInt(0,3));
	}
	public Nodo(String e) {
		valor = e;
	}
	
	//Constructor nodo invalido
	public Nodo() {
		valor = "E";
	}
	
	//constructor de copia
	public Nodo(Nodo valor) {
		this.valor= valor.valor;
	}
	
	//Getter y setter valor del nodo
	public String getValor() {	return valor;}
	public void setTerminal() {	valor = terminales.get(rand.nextInt(0,6));}
	public void setFuncion() {	valor = funciones.get(rand.nextInt(0,3));}
	public void setTerminal(String s) {	while(valor==s)valor = terminales.get(rand.nextInt(0,6));}
	public void setFuncion(String s) {	while(valor==s)valor = funciones.get(rand.nextInt(0,3));}
	
	//funcion de comprabacion de valor
	public boolean isTerminal() {return terminales.contains(valor);}
	public boolean notFuncional() {return !funciones.contains(valor) && valor!="x";}
	public boolean isX() {return valor=="x";}
	public boolean isNull() {return valor == "E";}
	public boolean negativo() {
		return valor == "-2" || valor == "-1";
	}
}
