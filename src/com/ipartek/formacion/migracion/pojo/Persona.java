package com.ipartek.formacion.migracion.pojo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Persona {

	/**
	 * Atributos 
	 ***************************/
	private int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int edad;
	private String email;
	private String dni;
	private String rol;
	
	
	/**
	 * Constructores 
	 ***************************/
	public Persona() {
		super();
		setId(-1);
		setNombre("");
		setApellido1("");
		setApellido2("");
		setEdad(0);
		setEmail("");
		setDni("");
		setRol("");
	}
	
	
	public Persona(int id, String nombre, String apellido1, String apellido2, int edad, String email, String dni,
			String rol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.edad = edad;
		this.email = email;
		this.dni = dni;
		this.rol = rol;
	}


	/**
	 * Get´s Set´s
	 ***************************/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	
	/**
	 * Métodos Propios
	 ***************************/
	
	@Override
	public String toString() {
		return ""+getNombre()+","+getApellido1()+","+getApellido2()
		+","+getEdad()+","+getEmail()+","+getDni()+","+getRol()+"\n" ;
	}


	public boolean isNombre(String cadena){
		boolean resul = false;
		Pattern pat = Pattern.compile("([,])([ ])(\\p{L}+)(([ ])(\\p{L}+))?");
		Matcher mat = pat.matcher(cadena);
		if (mat.matches()) {
			resul = true;
		} 
		return resul;
	}
	
	public boolean isEmail(String cadena){
		boolean resul = false;
		Pattern pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mat = pat.matcher(cadena);
		if (mat.matches()) {
			resul = true;
		} 
		return resul;
	}
	
	public boolean isDni(String cadena){
		boolean resul = false;
		Pattern pat = Pattern.compile("(([X-Z]{1})([-]?)(\\d{7})([-]?)([A-Z]{1}))|((\\d{8})([-]?)([A-Z]{1}))");
		Matcher mat = pat.matcher(cadena);
		if (mat.matches()) {
			resul = true;
		} 
		return resul;
	}
	

}

