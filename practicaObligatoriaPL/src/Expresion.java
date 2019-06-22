import java.util.ArrayList;

public class Expresion {
	
	public ArrayList<Simbolo> simbolos;
	public boolean error;
	
	public Expresion() {
		simbolos = new ArrayList<>();
	}
	
	public StringBuffer mostrarExpresion() {
		StringBuffer sb = new StringBuffer();
		for(Simbolo simbolo : simbolos)
			sb.append(simbolo.mostrarSimbolo() + " ");
		return sb;
	}
	
}