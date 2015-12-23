package com.ipartek.formacion.migracion.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.ipartek.formacion.migracion.pojo.Persona;

public class PersonaDAO implements Persistible<Persona>{

	@Override
	public boolean insertarLista(ArrayList<Persona> listaPersonas) throws SQLException {
		boolean resul = true;
		
		//Crear conexion
		DbConnection db;
		Connection conn;
		
		db = new DbConnection();
		conn = db.getConnection();
    	
    	//Recorrer ArrayList
		String nombre = "";
		String dni = "";
		String observaciones = "";
		String email = "";
		String sql = "";
		for(int i = 0; i<listaPersonas.size(); i++){
    		
			nombre = listaPersonas.get(i).getNombre()+" "+listaPersonas.get(i).getApellido1()+" "+listaPersonas.get(i).getApellido2();
    		dni = listaPersonas.get(i).getDni();
    		observaciones = listaPersonas.get(i).getRol();
    		email = listaPersonas.get(i).getEmail();
	    	
    		//Crear la consulta
	    	sql = "INSERT INTO `persona` (`pass`, `nombre`, `dni`, `fecha_nacimiento`, `observaciones`, `email`) VALUES (?, ?, ?, ?, ?, ?);";
	    	
	    	//Creamos la consulta
	    	PreparedStatement pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    	pst.setString(1, "");
	    	pst.setString(2, nombre);
	    	pst.setString(3, dni);
	    	pst.setTimestamp(4, null);
	    	pst.setString(5, observaciones);
	    	pst.setString(6, email);
	    	
	    	//Ejecutar la consulta
	    	if ( pst.executeUpdate() != 1 ){
	    		resul = false;
	    	}
	    	pst.close();
    	}
    	
    	if(resul){
    		conn.setAutoCommit(false);
    	} else {
    		conn.rollback();
    	}
    	
    	//cerrar recursos en orden inversa
    	
    	db.desconectar();
		
		
		return resul;
	}
	
	
	
	
	
	

}
