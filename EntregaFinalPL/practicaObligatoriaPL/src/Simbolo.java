import java.util.ArrayList;

public class Simbolo {
	
	public boolean identificador;
	public String id;
	
	public boolean constante;
	public String cte;
	
	public boolean tipo;
	public String t;
	
	public boolean opAritmetica;
	public String op;
	
	public boolean parentesis;
	public String p;
	
	public boolean coma;
	public String c;
	
	public boolean llave;
	public String key;
	
	public boolean operacionC;
	public String opC;
	
	public boolean operacionL;
	public String opL;
	
	public boolean negacion;
	public String neg;
	
	public boolean puntoYComa;
	public String pyc;
	
	public boolean retorno;
	public String ret;
	
	public boolean asignacion;
	public String asig;
	
	public boolean definicion;
	public String def;
	
	public String mostrarSimbolo() {
		if(tipo) {
			switch(t) {
				case "INTEGER": return "int";
				case "REAL": return "float";
				case "": return "";
			}
		} else if(opAritmetica) {
			switch(op) {
				case "div": return "/";
				case "mod": return "%";
				case "+": return "+";
				case "-": return "-";
				case "*": return "*";
			}
		} else if(identificador)
			return id;
		else if(constante)
			return cte;
		else if(parentesis)
			return p;
		else if(coma)
			return c;
		else if(llave)
			return key;
		else if(operacionC)
			return opC;
		else if(operacionL)
			return opL;
		else if(negacion)
			return neg;
		else if(puntoYComa)
			return pyc;
		else if(retorno)
			return ret;
		else if(asignacion)
			return asig;
		return "";		
	}
	
}
