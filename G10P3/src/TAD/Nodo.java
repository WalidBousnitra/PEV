package TAD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Nodo {

	private Random rand = new Random();
	private String[] funciones = {"+", "-", "*"};
	private List<String> terminales = new ArrayList<String>(Arrays.asList("x","-2","-1","0","1","2"));
	
	private String valor;
	
	public Nodo() {
		if(rand.nextBoolean()) {
			valor = funciones[rand.nextInt(0,3)];
		}
		else {
			valor = terminales.get(rand.nextInt(0,6));
		}
	}
	
	//constructor de copia
	public Nodo(String valor) {
		this.valor= valor;
	}
	
	//Getter valor del nodo
	public String getValor() {	return valor;}
	
	public void setTerminal() {	valor = terminales.get(rand.nextInt(0,6));}
	
	//funcion de comprabacion de valor
	public boolean isTerminal() {return terminales.contains(valor);	}
	public boolean isX() {return valor== "x";}
}
