

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.migracion.file.FicheroTexto;
import com.ipartek.formacion.migracion.modelo.PersonaDAO;
import com.ipartek.formacion.migracion.pojo.Persona;

/**
 * Objetivo: Hay que incluir todos los usuarios de un fichero de texto en una BBDD<br>
 * 
 * CONSTANTES:<br>
 * <ul>
 * <li>{@code PATH_FILE_TEST_FOLDER} Ruta de la carpeta donde se guardan los ficheros</li>
 * <li>{@code filePathPruebaTxt} Nombre del fichero de lectura</li>
 * <li>{@code filePathResumenTxt} Nombre del fichero resultados</li>
 * </ul>
 * <br>
 *  
 * El programa principal es el encargado de ejecutar paso a paso el programa:<br>
 * <ul>
 * <ol>Lectura de fichero de entrada: Incluir cada entrada en el ArrayList y devolver el número de errores de lectura
 * 			<li>@param listaPersonas = ArrayList<Persona> => Lista de personas leidas</li>
 * 			<li>@param errores = int => Nº errores de lectura de personas</li>
 * </ol>
 * <ol>Rellenar fichero Resumen
 * 			<li>inserciones: listaPersonas.tamaño</li>
 *			<li>errores: int</li>
 * </ol>
 * <ol>PersonaDAO.insertar
 * 		 	<li>Conectar con BBDD
 * 			<li>Recorrer ArrayList
 * 			<li>Insertar cada elemento en BBDD</li><br>
 *  		Si todo ha ido bien <br>
 * 				Commit<br>
 * 			sino<br>
 * 				Rollback<br>
 * </ol>
 * 
 * @author SantiSVS
 *
 */
public class Principal implements Serializable{

	static final String PATH_FILE_TEST_FOLDER = "Ficheros";
	static String filePathPruebaTxt = "/personas.txt";
	static String filePathResumenTxt = "/resumen.txt";
	
	
	public static void main(String[] args) throws SQLException {
		
		/*
		 * 1/Lectura Fichero de entrada
		 * 
		 */
		//Inicialización de parametros
		FicheroTexto fichero = new FicheroTexto(PATH_FILE_TEST_FOLDER + filePathPruebaTxt);
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
		int errores = 0;
		
		errores = fichero.leer(listaPersonas);
		
		
		/*
		 * Rellenar fichero Resumen
		 */
		FicheroTexto resumen = new FicheroTexto(PATH_FILE_TEST_FOLDER + filePathResumenTxt);
		
		resumen.escribir(listaPersonas.size(), errores);
		
		
		/*
		 * PersonaDAO.insertar
		 */
		PersonaDAO daoPersona = new PersonaDAO();
		
		if ( daoPersona.insertarLista(listaPersonas) ){
			System.out.println("Todo OK");
		}else{
			System.out.println("Fallo");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*FicheroTexto fichero = new FicheroTexto(PATH_FILE_TEST_FOLDER + filePathPruebaTxt);
		FileWriter fw = null;
        PrintWriter pw = null;
        
        try {
            fw = new FileWriter(PATH_FILE_TEST_FOLDER + filePathResumenTxt);
            pw = new PrintWriter(fw, true);
				
            fichero.leer(pw);
		
        } catch (Exception e) {
        	
            e.getMessage();
        
        } finally {
           try {
        	   if ( null!= pw ){
        		   pw.close();
        	   }
        	   if ( null != fw ){
        		   fw.close();
        	   }
           } catch (Exception e2){
              e2.printStackTrace();
           }
        }*/
	}

}
