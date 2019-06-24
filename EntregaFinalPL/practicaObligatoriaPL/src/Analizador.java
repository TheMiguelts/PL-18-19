import java.io.IOException;
import java.io.PrintWriter;
/**
*/
public class Analizador{
	static String ruta;
	public static void main(String argv[]){
		String ruta="/Users/sergiohernandezdominguez/Desktop/universidad/cuarto/pl/practica1819/PL-18-19/entrada.pas";
		AnalizadorLexico lexico = null;
		try {
			lexico = new AnalizadorLexico( new java.io.FileReader(ruta));
			parser sintactico = new parser(lexico);
			sintactico.parse();
			}
		catch (java.io.FileNotFoundException e) {
			System.out.println("Archivo no encontrado.");
		}
		catch (java.io.IOException e) {
		System.out.println("Error durante la lectura del archivo");
		e.printStackTrace();
		}
		catch (Exception e) {
		System.out.println("Excepcion:");
		e.printStackTrace();
		}
	}
}
