import java.io.*;

public class Analizador{
	private static String nombreFichero;
	public static void main(String argv[]){
		if(argv.length==0){
			System.out.println("Inserta nombre del archivo\n" + "( Usage: java Analizador <nombreArchivo>)");
		}else{
			AnalizadorLexico lexico=null;
			try{
				nombreFichero = argv[0];
				lexico=new AnalizadorLexico(new FileReader(argv[0]));
				parser sintactico=new parser(lexico);
				sintactico.debug_parse();
			}catch (FileNotFoundException e){
				System.out.println("Archivo \""+argv[0]+"\" no encontrado.");
			}catch (IOException e){
				System.out.println("Error durante lectura de" + " archivo \""+argv[0]+"\".");
				e.printStackTrace();
			}catch (Exception e){
				System.out.println("Excepcion: ");
				e.printStackTrace();
			}
		}
	}
	public static void imprimir(Programa programa) throws IOException{
		int numeroAux=0;
		Func main=null;
		for(Func func:program.getFunciones()){
			if(func.ident.getSimb().equals("main")||func.ident.getSimb().equals("Main")){
				numeroAux++;
				main=func;
			}
		}
		if (numeroAux>1){
			System.err.println("Error, se ha encontrado mas de un main");
		}else{
			File fichero=new File(nombreFichero+".pas");
			FileWriter fw=new FileWriter(fichero);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(programa.escribir);
		}
		bw.flush();
		bw.close();
	}
}
	


