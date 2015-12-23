

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
 * El programa principal es el encargado de ejecutar pasos a paso el hilo de ejcución:
 * 
 * 1/ 	Lectura de fichero de entrada
 * 			Incluir cada entrada en el ArrayList y devolver el número de errores de lectura
 * 			@param listaPersonas = ArrayList<Persona> => Lista de personas leidas
 * 			@param errores = int => Nº errores de lectura de personas
 * 2/	Rellenar fichero Resumen
 * 			- inserciones: listaPersonas.tamaño
			- errores: int
 * 3/ 	PersonaDAO.insertar
 * 		 	Conectar con BBDD
 * 			Recorrer ArrayList
 * 			Insertar cada elemento en BBDD
 * 4/ 	Si todo ha ido bien 
 * 			Commit
 * 		sino
 * 			Rollback
 * 
 * 
 * @author SantiSVS
 *
 */
public class Principal implements Serializable{

	static final String PATH_FILE_TEST_FOLDER = "Ficheros";
	static String filePathPruebaTxt = "/prueba.txt";
	static String filePathResumenTxt = "/resumen.txt";
	
	
	public static void main(String[] args) throws SQLException {
		
		/*
		 * Lectura Fichero de entrada
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
		
		for (int i = 0 ; i<listaPersonas.size(); i++){
			System.out.println(""+listaPersonas.get(i).toString());
		}
		
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
