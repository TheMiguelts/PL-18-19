import java.util.ArrayList;

public class ControlFlujo {
	
	public Sentencia cond;
	public String nombre;
	public ArrayList<Sentencia> bloque;
	public ArrayList<Sentencia> bloqueElse;
	public ArrayList<Sentencia> declaraciones;
	
	public ControlFlujo() {
		bloque = new ArrayList<>();
		bloqueElse = new ArrayList<>();
		declaraciones = new ArrayList<>();
	}
	
	public StringBuffer mostrarControlFlujo() {
		StringBuffer sb = new StringBuffer();
		if(nombre == "if") {
			sb.append("if ").append("(" + cond.mostrarSentencia().toString() + ")");
			sb.append("then \r\n");
			sb.append("begin\r\n");
			for(Sentencia sent : bloque)
				sb.append("\t" + sent.mostrarSentencia().toString() + "\r\n");
			sb.append("end\r\n");
			sb.append("else\r\n");
			sb.append("begin\r\n");
			for(Sentencia sent : bloqueElse)
				sb.append(sent.mostrarSentencia().toString() + "\r\n");
			sb.append("end\r\n");
		} else if(nombre == "for") {
			sb.append("for ").append("(" + cond.mostrarSentencia().toString() + ")");
			sb.append("\r\n").append("begin\r\n");
			for(Sentencia sent : bloque)
				sb.append(sent.mostrarSentencia().toString() + "\r\n");
			sb.append("end\r\n");
		} else if(nombre == "do") {
			sb.append("repeat ");
			sb.append("\r\n").append("begin\r\n");
			for(Sentencia sent : bloque)
				sb.append(sent.mostrarSentencia().toString() + "\r\n");
			sb.append("end\r\n").append("until (" + cond.mostrarSentencia().toString() + ")");
		}
		return sb;
	}
	
}