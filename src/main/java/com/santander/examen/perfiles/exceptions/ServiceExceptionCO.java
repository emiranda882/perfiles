package com.santander.examen.perfiles.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceExceptionCO extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5296151658429415515L;

	
	private final List<String> codigos;
	private final Map<String, List<String>> mensajes;
	
	
	public ServiceExceptionCO(String message, String code) {
		super(message);
		codigos = new ArrayList<>();
		codigos.add(code);
		mensajes = new HashMap<>();
	}
	
	
	public ServiceExceptionCO(String message) {
		super(message);
		codigos = new ArrayList<>();
		mensajes = new HashMap<>();
	}
	
	public ServiceExceptionCO(Throwable error, Class<?> clase, String metodo, String message) {
		super(message);
		codigos = new ArrayList<>();
		mensajes = new HashMap<>();
	}
	
	
	public List<String> getCodigos() {
		return codigos;
	}

	public Map<String, List<String>> getMensajes() {
		return mensajes;
	}

}
