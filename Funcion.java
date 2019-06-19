import java.util.ArrayList;

public class Funcion {
	
	public String nombre;
	public String tipo;
	public Sentencia param;
	public ArrayList<Sentencia> declaraciones;
	public ArrayList<Sentencia> bloque;
	public boolean err;
	
	public Funcion() {
		declaraciones = new ArrayList<>();
		bloque = new ArrayList<>();
		param = new Sentencia();
	}
	
	public StringBuffer mostrarProcedimiento() {
		StringBuffer sb = new StringBuffer();
		if(!err) {
			sb.append("\r\nprocedure " + nombre + " (" + mostrarParams().toString() + ");\r\n");
			if(!declaraciones.isEmpty()) {
				int nSent = declaraciones.get(0).numSentencia;
				String simb = declaraciones.get(0).simbolos.get(0).mostrarSimbolo();
				Simbolo simb2 = new Simbolo();
				sb.append("var\r\n");
				ArrayList<Sentencia> seguidas = new ArrayList<>();
				for(Sentencia sent : declaraciones) {
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
				sent.declaracionFunc = true;
				sb.append(sent2.mostrarSentencia());
			}
			sb.append("begin\r\n");
			for(Sentencia sent : bloque) {
				if(!sent.error)
					sb.append(sent.mostrarSentencia().toString() + "\r\n");
			}
			sb.append("end;")
		}
		return sb;
	}
	
	public StringBuffer mostrarFuncion() {
		StringBuffer sb = new StringBuffer();
		if(!err) {
			sb.append("\r\nfunction " + nombre + " ( " + imprimirParams().toString() + "): " + convertirTipo(tipo) + ";\r\n");
			if(!declaraciones.isEmpty()) {
				int nSent = declaraciones.get(0).numSentencia;
				String simb = declaraciones.get(0).simbolos.get(0).mostrarSimbolo();
				Simbolo simb2 = new Simbolo();
				sb.append("var\r\n");
				ArrayList<Sentencia> seguidas = new ArrayList<>();
				for(Sentencia sent : declaraciones) {
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
				sent.declaracionFunc = true;
				sb.append(sent2.mostrarSentencia());
			}
			sb.append("begin\r\n");
			for(Sentencia sent : bloque) {
				if(!sent.error) {
					if(sent.devuelto)
						sb.append(sent.mostrarSentencia());
					else
						sb.append(sent.mostrarSentencia().toString() + "\r\n");
				}
			}
			sb.append("\r\nend;\r\n");
		}
		return sb;
	}
	
	public StringBuffer mostrarMain() {
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n");
		sb.append("begin\r\n");
		for(Sentencia sent : bloque) {
			sb.append(sent.mostrarSentencia().toString());
			sb.append("\r\n");
		}
		sb.append("end.\r\n");
		return sb;
	}
	
	public String convertirTipo(String tipo) {
		String conversion = null;
		if(tipo.equals("int"))
			conversion = "INTEGER";
		else if(tipo.equals("float"))
			conversion = "REAL";
		else
			conversion = "";
		return conversion;
	}
	
	public StringBuffer imprimirVars() {
		StringBuffer sb = new StringBuffer();
		Sentencia constInt = new Sentencia();
		Sentencia constFloat = new Sentencia();
		for(Sentencia sent : declaraciones) {
			constInt.simbolos.addAll(sent.constantesEnteras().simbolos);
			constFloat.simbolos.addAll(sent.constantesReales().simbolos);
		}
		if(!constInt.simbolos.isEmpty()) {
			constInt.simbolos.remove(constInt.simbolos.size()-1);
			sb.append("\t" + constInt.mostrarSentencia().toString() + ": INTEGER;\r\n");
		}
		if(!constFloat.simbolos.isEmpty()) {
			constFloat.simbolos.remove(constFloat.simbolos.size()-1);
			sb.append("\t" + constFloat.mostrarSentencia().toString() + ": REAL;\r\n");
		}
		return sb;
	}
	
	public StringBuffer imprimirParams() {
		int numParams = 0;
		StringBuffer sb = new StringBuffer();
		Sentencia sent = new Sentencia();
		ArrayList<Sentencia> parametros = new ArrayList<>();
		int cont = 0;
		if(param != null) {
			for(Simbolo s : param.simbolos) {
				if(cont < 2) {
					if((!s.coma) && (!s.puntoYComa)) {
						cont++;
						sent.simbolos.add(s);
					} 
				} else {
					numParams+=2;
					sent.numSentencia = numParams;
					parametros.add(sent);
					sent = new Sentencia();
					cont = 0;
				}
			}
			sent.numSentencia = numParams+2;
			parametros.add(sent);
			int nSent = parametros.get(0).numSentencia;
			String simb = parametros.get(0).simbolos.get(0).mostrarSimbolo();
			Simbolo simb2 = new Simbolo();
			sb.append("");
			ArrayList<Sentencia> seguidas = new ArrayList<>();
			for(Sentencia sent1 : parametros) {
				if(((sent1.numSentencia == nSent) || (sent1.numSentencia == (nSent+2))) && (sent1.simbolos.get(0).mostrarSimbolo().equals(simb))) {
					seguidas.add(sent1);
					nSent = sent1.numSentencia;
				} else {
					Sentencia sentAux = new Sentencia();
					simb2.tipo = true;
					if(seguidas.get(0).simbolos.get(0).mostrarSimbolo().equals("INTEGER"))
						simb2.t = "INTEGER";
					else
						simb2.t = "REAL";
					for(Sentencia sent2 : seguidas) {
						for(Simbolo s : sent2.simbolos) {
							if((!s.tipo) && (!s.puntoYComa))
								sentAux.simbolos.add(s);
						}
					}
					sentAux.simbolos.add(simb2);
					sentAux.paramFunc = true;
					sb.append(sentAux.mostrarSentencia());
					seguidas.clear();
					seguidas.add(sent1);
					nSent = sent1.numSentencia;
					simb = sent1.simbolos.get(0).mostrarSimbolo();
				}
			}
			Sentencia sentAux2 = new Sentencia();
			simb2.tipo = true;
			if(seguidas.get(0).simbolos.get(0).mostrarSimbolo().equals("INTEGER"))
				simb2.t = "INTEGER";
			else
				simb2.t = "REAL";
			for(Sentencia sent1 : seguidas) {
				for(Simbolo s : sent1.simbolos) {
					if((!s.tipo) && (!s.puntoYComa))
						sentAux2.simbolos(s);
				}
			}
			sentAux2.simbolos.add(simb2);
			sentAux2.paramFunc = true;
			sb.append(sentAux2.mostrarSentencia());
			sb.setLength(sb.length()-2);
		}
		return sb;
	}
	
	public boolean esMain() {
		if(nombre.equals("main"))
			return true;
		return false;
	}
}