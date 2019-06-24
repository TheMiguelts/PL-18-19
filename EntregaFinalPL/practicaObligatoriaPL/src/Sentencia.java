import java.util.ArrayList;

public class Sentencia {
	
	public boolean procSinParentesis;
	public boolean error;
	public boolean declaracionFunc;
	public boolean declaracionCte;
	public boolean devuelto;
	public boolean paramFunc;
	public boolean asign;
	public boolean noTab;
	public boolean controlFlujo;
	public boolean esCondFor1;
	public boolean esCondFor2;
	public boolean esCondFor3;
	public boolean esCondicion;
	
	public static String nombreFunc;
	public static int contTabs = 1;
	public String nombre;
	public int numSentencia;
	
	public ArrayList<Simbolo> simbolos;
	public ArrayList<Simbolo> condicion;
	public ArrayList<Simbolo> condFor1;
	public ArrayList<Simbolo> condFor2;
	public ArrayList<Simbolo> condFor3;
	public ArrayList<Sentencia> bloque;
	public ArrayList<Sentencia> bloqueElse;
	public ArrayList<Sentencia> declaraciones;
	
	public Simbolo idFor;
	public Expresion e1;
	public Expresion e2;
	
	public Sentencia() {
		simbolos = new ArrayList<>();
		condicion = new ArrayList<>();
		bloque = new ArrayList<>();
		declaraciones = new ArrayList<>();
		bloqueElse = new ArrayList<>();
		condFor1 = new ArrayList<>();
		condFor2 = new ArrayList<>();
		condFor3 = new ArrayList<>();
		e1 = new Expresion();
		e2 = new Expresion();		
	}
	
	public StringBuffer mostrarSentencia() {
		StringBuffer sb = new StringBuffer();
		if(declaracionCte) {
			for(Simbolo s : simbolos) {
				if(!s.definicion) {
					if(s.identificador)
						sb.append("\t" + s.mostrarSimbolo().toString() + ";");
					else
						sb.append(s.mostrarSimbolo().toString() + ";");
				}
			}
		} else if(true/*s.declaracionFunc*/) { //COMENTADOS FALLOS QUE NECESITO MIRAR
			sb.append(imprimirTabs(Sentencia.contTabs));
			for(Simbolo s : simbolos) {
				if((!s.tipo) && (!s.puntoYComa) &&(!s.coma))
					sb.append(s.mostrarSimbolo().toString() + ",");
				else if(s.tipo) {
					/*sb.setLength(sentencia.length()-2);*/ //MIRAR
					sb.append(":").append(s.t).append(";\r\n");
				}
			}
		} else if(paramFunc) {
			for(Simbolo s : simbolos) {
				if((!s.tipo) && (!s.puntoYComa) &&(!s.coma))
					sb.append(s.mostrarSimbolo().toString() + ",");
				else if(s.tipo) {
					/*sb.setLength(sentencia.length()-2);*/ //MIRAR
					sb.append(":").append(s.t).append("; ");
				}
			}
		} else if(devuelto) {
			for(Simbolo s : simbolos) {
				if((!s.retorno) && (!s.asignacion) && (!s.puntoYComa))
					sb.append(imprimirTabs(Sentencia.contTabs)+ "return = " + s.mostrarSimbolo().toString() + ";");
			}
		} else if(asign) {
			sb.append(imprimirTabs(Sentencia.contTabs));
			for(Simbolo s : simbolos) {
				if(s.mostrarSimbolo().toString().equals("="))
					sb.append("= ");
				else
					sb.append(s.mostrarSimbolo().toString() + "");
			}
		} else if(procSinParentesis) {
			sb.append(imprimirTabs(Sentencia.contTabs));
			for(Simbolo s : simbolos) {
				if((!s.mostrarSimbolo().toString().equals("(")) && (!s.mostrarSimbolo().toString().equals(")")))
					sb.append(s.mostrarSimbolo().toString());
			}
		} else if(controlFlujo) {
			if(nombre.equals("if")) {
				Sentencia sentAux = new Sentencia();
				sentAux.simbolos = condicion;
				sentAux.esCondicion = true;
				sb.append(imprimirTabs(Sentencia.contTabs) + "if (").append(") then \r\n");
				sb.append(imprimirTabs(Sentencia.contTabs) + "{\r\n");
				Sentencia.contTabs++;
				for(Sentencia sent : bloque)
					sb.append(sent.mostrarSentencia().toString() + "\r\n");
				Sentencia.contTabs--;
				sb.append(imprimirTabs(Sentencia.contTabs) + "}\r\n");
				sb.append(imprimirTabs(Sentencia.contTabs) + "else\r\n").append(imprimirTabs(Sentencia.contTabs) + "{\r\n");
				Sentencia.contTabs++;
				for(Sentencia sent : bloqueElse)
					sb.append(sent.mostrarSentencia().toString() + "\r\n");
				Sentencia.contTabs--;
				sb.append(imprimirTabs(Sentencia.contTabs) + "}\r\n");
			} else if(nombre.equals("while")) {
				Sentencia sentAux = new Sentencia();
				sentAux.simbolos = condicion;
				sentAux.esCondicion = true;
				sb.append(imprimirTabs(Sentencia.contTabs) + "while (").append(" " + sentAux.mostrarSentencia() + ")").append("\r\n");
				sb.append(imprimirTabs(Sentencia.contTabs) + "{\r\n");
				Sentencia.contTabs++;
				for(Sentencia sent : bloque)
					sb.append(sent.mostrarSentencia().toString() + "\r\n");
				Sentencia.contTabs--;
				sb.append(imprimirTabs(Sentencia.contTabs) + "}\r\n");
			} else if(nombre.equals("do")) {
				Sentencia sentAux = new Sentencia();
				sentAux.simbolos = condicion;
				sentAux.esCondicion = true;
				sb.append(imprimirTabs(Sentencia.contTabs) + "{\r\n");
				Sentencia.contTabs++;
				for(Sentencia sent : bloque)
					sb.append(sent.mostrarSentencia().toString() + "\r\n");
				Sentencia.contTabs--;
				sb.append(imprimirTabs(Sentencia.contTabs) + "}\r\n");
				sb.append(imprimirTabs(Sentencia.contTabs) + "until ").append(" " + sentAux.mostrarSentencia() + ");").append("\r\n");
			} else if(nombre.equals("for")) {
				boolean forMas = true;
				boolean forMenos = true;
				Sentencia sentFor2 = new Sentencia();
				sentFor2.simbolos = condFor2;
				for(Simbolo s : e2.simbolos) {
					if((!s.mostrarSimbolo().equals(idFor.mostrarSimbolo())) && (!s.mostrarSimbolo().equals("+")) && (!s.mostrarSimbolo().equals("1")))
						forMas = false;
				}
				for(Simbolo s : e2.simbolos) {
					if((!s.mostrarSimbolo().equals(idFor.mostrarSimbolo())) && (!s.mostrarSimbolo().equals("-")) && (!s.mostrarSimbolo().equals("1")))
						forMenos = false;
				}
				Sentencia sentFor2Aux = new Sentencia();
				sentFor2Aux.noTab = true;
				boolean opeC = false;
				for(Simbolo s : sentFor2.simbolos) {
					if(opeC)
						sentFor2Aux.simbolos.add(s);
					if(s.operacionC)
						opeC = true;
				}
				if(forMas)
					sb.append(imprimirTabs(Sentencia.contTabs) + "for (" + idFor.mostrarSimbolo() + "= " + e1.mostrarExpresion() + ";" + idFor.mostrarSimbolo()+"<"+sentFor2Aux.mostrarSentencia() + ";"+idFor.mostrarSimbolo()+"="+idFor.mostrarSimbolo()+"+1)\r\n");
				if(forMenos)
					sb.append(imprimirTabs(Sentencia.contTabs) + "for (" + idFor.mostrarSimbolo() + "= " + e1.mostrarExpresion() + ";" + idFor.mostrarSimbolo()+">"+sentFor2Aux.mostrarSentencia() + ";"+idFor.mostrarSimbolo()+"="+idFor.mostrarSimbolo()+"-1)\r\n");
				sb.append(imprimirTabs(Sentencia.contTabs) + "{\r\n");
				Sentencia.contTabs++;
				for(Sentencia sent : bloque)
					sb.append(imprimirTabs(Sentencia.contTabs)).append(idFor.mostrarSimbolo().toString() + "\r\n");
				Sentencia.contTabs--;
				sb.append(imprimirTabs(Sentencia.contTabs) + "}\r\n");
			}
		} else if(true/*condicion*/) { //MIRAR
			for(Simbolo s : simbolos)
				sb.append(s.mostrarSimbolo());
		} else if(noTab) {
			for(Simbolo s : simbolos)
				sb.append(s.mostrarSimbolo().toString() + "");
		} else {
			sb.append(imprimirTabs(Sentencia.contTabs));
			for(Simbolo s : simbolos)
				sb.append(s.mostrarSimbolo().toString() + "");		
		}
		return sb;
	}
	
	public Sentencia constantesEnteras() {
		Simbolo simb = new Simbolo();
		simb.c = ",";
		simb.coma = true;
		Sentencia sent = new Sentencia();
		Sentencia sentAux = new Sentencia();
		if(declaracionFunc) {
			if(simbolos.get(0).t != null) {
				if(simbolos.get(0).t.equals("int")) {
					sentAux.simbolos.addAll(simbolos);
					sentAux.simbolos.remove(0);
					for(Simbolo s : sentAux.simbolos) {
						if(!s.puntoYComa)
							sent.simbolos.add(s);
						else
							sent.simbolos.add(simb);
					}
				}
			}
		}
		return sent;
	}
	
	public Sentencia constantesReales() {
		Simbolo simb = new Simbolo();
		simb.c = ",";
		simb.coma = true;
		Sentencia sent = new Sentencia();
		Sentencia sentAux = new Sentencia();
		if(declaracionFunc) {
			if(simbolos.get(0).t != null) {
				if(simbolos.get(0).t.equals("float")) {
					sentAux.simbolos.addAll(simbolos);
					sentAux.simbolos.remove(0);
					for(Simbolo s : sentAux.simbolos) {
						if(!s.puntoYComa)
							sent.simbolos.add(s);
						else
							sent.simbolos.add(simb);
					}
				}
			}
		}
		return sent;
	}
	
	public Sentencia parametrosEnteros() {
		Simbolo simb = new Simbolo();
		simb.c =", ";
		simb.coma = true;
		Sentencia sent = new Sentencia();
		boolean entero = false;
		if(paramFunc) {
			for(Simbolo s : simbolos) {
				if(s.tipo) {
					if(s.t.equals("int"))
						entero = true;
				}
				if((s.identificador) && (entero)) {
					sent.simbolos.add(s);
					sent.simbolos.add(simb);
					entero = false;
				}
			}
		}
		if(!sent.simbolos.isEmpty())
			sent.simbolos.remove(sent.simbolos.size()-1);
		return sent;
	}
	
	public Sentencia parametrosReales() {
		Simbolo simb = new Simbolo();
		simb.c =", ";
		simb.coma = true;
		Sentencia sent = new Sentencia();
		boolean real = false;
		if(paramFunc) {
			for(Simbolo s : simbolos) {
				if(s.tipo) {
					if(s.t.equals("float"))
						real = true;
				}
				if((s.identificador) && (real)) {
					sent.simbolos.add(s);
					sent.simbolos.add(simb);
					real = false;
				}
			}
		}
		if(!sent.simbolos.isEmpty())
			sent.simbolos.remove(sent.simbolos.size()-1);
		return sent;
	}
	
	public StringBuffer imprimirTabs(int n) {
		StringBuffer sb = new StringBuffer();
		for(int i=1;i<=n;i++)
			sb.append("\t");
		return sb;
	}
}