import java.io*;
import java.util.ArrayList;

public class Programa {
	
	public static String nombreFunc;
	public static int contSents;
	public String nombre;
	public ArrayList<Sentencia> declaraciones;
	public ArrayList<Sentencia> variables;
	public ArrayList<Sentencia> funciones;
	
	public Programa() {
		nombre = "main";
		declaraciones = new ArrayList<>();
		variables = new ArrayList<>();
		funciones = new ArrayList<>();
	}
	
	public StringBuffer imprimir() {
		StringBuffer sb = new StringBuffer();
		if(contieneMain())
			sb.append("program " + nombre + ";\r\n");
		else
			sb.append("unit " + nombre + ";\r\n");
		if(!declaraciones.isEmpty()) {
			sb.append("const\r\n");
			for(Sentencia sent : declaraciones)
				sb.append(sent.mostrarSentencia().toString() + "\r\n");
		Funcion principal = new Funcion();
		for(Funcion f : funciones) {
			if(f.nombre != null) {
				if(f.nombre.equals("main"))
					principal = f;
			}
		}
		if(!principal.declaraciones.isEmpty()) {
			int nSent = principal.declaraciones.get(0).numSentencia;
			String simb = principal.declaraciones.get(0).simbolos.get(0).mostrarSimbolo();
			Simbolo simb2 = new Simbolo();
			sb.append("var\r\n");
			ArrayList<Sentencia> seguidas = new ArraList<>();
			for(Sentencia sent : principal.declaraciones) {
				if(((sent.numSentencia == nSent) || (sent.numSentencia == (nSent+2))) && (sent.simbolos.get(0).mostrarSimbolo().equals(simb))) {
					seguidas.add(sent);
					nSent = sent.numSentencia;
				} else {
					Sentencia sentAux = new Sentencia();
					simb2.tipo = true;
					if(seguidas.get(0).simbolos.get(0).mostrarSimbolo().equals("INTEGER"))
						simb2.t = "INTEGER";
					else
						simb2.t = "REAL";
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
			if(seguidas.get(0).simbolos.get(0).mostrarSimbolo().equals("INTEGER"))
				simb2.t = "INTEGER";
			else
				simb2.t = "REAL";
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
		if(!funciones.isEmpty()) {
			for(Funcion f : funciones) {
				if(f.nombre != null) {
					if(!f.nombre.equals("main") {
						if(f.t.equals(""))
							sb.append(f.mostrarProcedure().toString());
						else {
							Programa.nombreFunc = new String(f.nombre);
							sb.append(f.mostrarFuncion().toString());
						}
					}
				}
			}
			if(contieneMain()) {
				for(Funcion f : funciones) {
					if(f.nombre != null) {
						if(f.nombre.equals("main"))
							sb.append(f.mostrarMain().toString() + "\r\n");
					}
				}
			} else
				sb.append("\r\n.");
			return sb;
		}
	}
	
	public boolean contieneMain() {
		boolean ok = false;
		for(Funcion f : funciones) {
			if(f.nombre != null) {
				if(f.nombre.equals("main"))
					ok = true;
			}
		}
		return ok;
	}
	
	public static void generarFichero(String cuerpo) {
		try {
			StringBuffer sb = new StringBuffer(Analizador.ruta);
			sb.setLength(sb.length()-2);
			File fichero = new File(sb.toString() + ".pas");
			BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
			bw.write(cuerpo);
			bw.close();
		} catch(IOException ex) {
			System.err.println("Error al generar el fichero");
		}
	}
}