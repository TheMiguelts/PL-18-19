import java.io.*;
import java.util.ArrayList;

public class Programa {
	
	public static String nombreFunc;
	public static int contSents;
	public String nombre;
	public ArrayList<Sentencia> declaraciones;
	public ArrayList<Sentencia> variables;
	public ArrayList<Sentencia> funciones;
	
	public Programa() {
		nombre = "PRUEBA PL: ";
		declaraciones = new ArrayList<>();
		variables = new ArrayList<>();
		funciones = new ArrayList<>();
	}
	
	public StringBuffer imprimir() {
		StringBuffer sb = new StringBuffer();
		Sentencia principal = new Sentencia();
		for(Sentencia f : funciones) {
			if(f.nombre != null) {
				if(f.nombre.equals("main"))
					principal = f;
			}
		}
		if(!principal.declaraciones.isEmpty()) {
			int nSent = principal.declaraciones.get(0).numSentencia;
			String simb = principal.declaraciones.get(0).simbolos.get(0).mostrarSimbolo();
			Simbolo simb2 = new Simbolo();
			ArrayList<Sentencia> seguidas = new ArrayList<>();
			for(Sentencia sent : principal.declaraciones) {
				if(((sent.numSentencia == nSent) || (sent.numSentencia == (nSent+2))) && (sent.simbolos.get(0).mostrarSimbolo().equals(simb))) {
					seguidas.add(sent);
					nSent = sent.numSentencia;
				} else {
					Sentencia sentAux = new Sentencia();
					simb2.tipo = true;
					if(seguidas.get(0).simbolos.get(0).mostrarSimbolo().equals("int"))
						simb2.t = "int";
					else
						simb2.t = "float";
					for(Sentencia sent1 : seguidas) {
						for(Simbolo s : sent1.simbolos) {
							if((!s.tipo) && (!s.puntoYComa))
								sentAux.simbolos.add(s);
						}
					}
					sentAux.simbolos.add(simb2);
					sentAux.declaracionFunc = true;
					sb.append(sentAux.mostrarSentencia());
					seguidas.clear();
					seguidas.add(sent);
					nSent = sent.numSentencia;
					simb = sent.simbolos.get(0).mostrarSimbolo();
				}
			}
			Sentencia sent2 = new Sentencia();
			simb2.tipo = true;
			if(seguidas.get(0).simbolos.get(0).mostrarSimbolo().equals("int"))
				simb2.t = "int";
			else
				simb2.t = "float";
			for(Sentencia sent : seguidas) {
				for(Simbolo s : sent.simbolos) {
					if((!s.tipo) && (!s.puntoYComa))
						sent2.simbolos.add(s);
				}
			}
			sent2.simbolos.add(simb2);
			sent2.declaracionFunc = true;
			sb.append(sent2.mostrarSentencia());
		}
		return sb;
		}
	
	public static void generarFichero(String cuerpo) {
		try {
			StringBuffer sb = new StringBuffer(Analizador.ruta);
			sb.setLength(sb.length()-2);
			File fichero = new File(sb.toString() + ".c");
			BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
			bw.write(cuerpo);
			bw.close();
		} catch(IOException ex) {
			System.err.println("Error al generar el fichero");
		}
	}
}