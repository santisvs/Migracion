package com.ipartek.formacion.migracion.file;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.ipartek.formacion.migracion.pojo.Persona;

public class FicheroTexto {
	
	private File fichero;
	
	public FicheroTexto(String ruta) {
		super();
		this.fichero = new File(ruta);
	}


	public int leer(ArrayList<Persona> listaPersonas){
		int resul = 0;
		FileReader fr = null;
        BufferedReader br = null;
        
        try {
            fr = new FileReader(fichero);
            br = new BufferedReader(fr);
            
            Persona per = null;
            String linea = null;
            
            while((linea=br.readLine())!=null){
            	per = new Persona();
            	leerCamposPersona(per,linea);
            	if(per.getId()==0){
            		listaPersonas.add(per);
            	}
            	else{
            		resul++;
            	}
            }
             
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
            
        } catch (Exception e) {
        	e.printStackTrace();
            
        } finally {
        	try {
              if ( null!= br ){
        		   br.close();
        	   }
        	   if ( null != fr ){
        		   fr.close();
        	   }
           } catch (Exception e2) {
        	  e2.printStackTrace();
           }
        }
        
        return resul;
	}


	private void leerCamposPersona(Persona per, String linea) {
		String[] campos = linea.split(",");
		if (campos.length != 7){
			per.setId(-1);
		}else{
			//TODO utilizar constructor Persona con campo y comprobar campos en clase persona
			per.setId(0);
			//TODO COmprobar patrones de nombre, apellido1 y apellido2
			per.setNombre(campos[0]);
			per.setApellido1(campos[1]);
			per.setApellido2(campos[2]);
			//TODO COmprobar patrones de edad
			per.setEdad(Integer.parseInt(campos[3]));
			//TODO COmprobar patrón de email
			per.setEmail(campos[4]);
			//TODO Comprobar patrón de Dni
			per.setDni(campos[5]);
			//TODO Comprobar patrón de Rol
			per.setRol(campos[6]);
		}
	}


	public void escribir(int size, int errores) {
		FileWriter fw = null;
        PrintWriter pw = null;
        
        try {
        	fw = new FileWriter(fichero);
        	 pw = new PrintWriter(fw, true);	//con True => autoflush
        	 
             pw.println("inserciones: "+size);
             pw.println("errores: "+errores);
            
            
        } catch (FileNotFoundException e) {
        	e.getMessage();
            
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
           } catch (Exception e2) {
        	  e2.printStackTrace();
           }
		
        }
	}
}
