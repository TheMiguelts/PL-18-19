import java.util.ArrayList;

public class Expresion {
	
	public ArrayList<Simbolo> simbolos;
	public boolean error;
	
	public Expresion() {
		simbolos = new ArrayList<>();
	}
	
	public StringBuffer mostrarExpresion() {
		StringBuffer expresion = new StringBuffer();
		for(Simbolo simbolo : simbolos)
			expresion.append(simbolo.mostrarSimbolo() + " ");
		return expresion;
	}
	
}