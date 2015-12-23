package com.ipartek.formacion.migracion.modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Persistible<P> {

	
	boolean insertarLista(ArrayList<P> persistable) throws SQLException;
	
}
