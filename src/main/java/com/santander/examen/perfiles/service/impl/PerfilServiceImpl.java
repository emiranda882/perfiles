package com.santander.examen.perfiles.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.examen.perfiles.exceptions.ServiceExceptionCO;
import com.santander.examen.perfiles.model.Perfil;
import com.santander.examen.perfiles.repository.PerfilRepository;
import com.santander.examen.perfiles.service.PerfilService;

@Service("perfilService")
public class PerfilServiceImpl implements PerfilService {

	private static final String ERROR001 = "El nombre del perfil es nulo";
	private static final String ERROR002 = "El maximo de caracteres en el nombre permitidos son 15";
	private static final String ERROR003 = "El nombre solo puede contener letras ";
	private static final String ERROR004 = "El Apellido paterno es nulo";
	private static final String ERROR005 = "El maximo de caracteres en el Apellido paterno permitidos son 15";
	private static final String ERROR006 = "El Apellido paterno solo puede contener letras";
	private static final String ERROR007 = "El maximo de caracteres en el Apellido materno permitidos son 15";
	private static final String ERROR008 = "El Apellido materno solo puede contener letras";
	private static final String ERROR009 = "La edad debe ser mayor a 12";
	private static final String ERROR010 = "La edad contiene mas de tres digitos";
	
	private static final Long ID_INICIAL = 0L;

	@Autowired
	private PerfilRepository perfilRespository;

	@Override
	public List<Perfil> findAll() {
		List<Perfil> user = perfilRespository.findAll();
		return user;
	}

	@Override
	public Perfil savePerfil(Perfil perfil) throws ServiceExceptionCO {
		Perfil respuesta = null;
		try {
			if (validaciones(perfil)) {
				if (Objects.nonNull(perfil.getUserId())) {
					return respuesta = perfilRespository.save(perfil);
				}
				List<Perfil> listPerfiles = perfilRespository.findAll();
				if(Objects.isNull(listPerfiles) || listPerfiles.isEmpty()){
					listPerfiles = new ArrayList<>();
					perfil.setUserId(ID_INICIAL);
					listPerfiles.add(perfil);
				}
					List<Long> listIds = new ArrayList<Long>();
					for (Perfil prfil : listPerfiles) {
						listIds.add(prfil.getUserId());
					}
					Collections.sort(listIds);
					Long ultimoId = listIds.get(listIds.size() - 1);
					perfil.setUserId(ultimoId + 1);
					if (Objects.nonNull(ultimoId)) {
						respuesta = perfilRespository.save(perfil);
						listIds = null; // Se limpia la memoria
					}
			}
		} catch (ServiceExceptionCO e) {
			throw e;
		}
		return respuesta;
	}

	@Override
	public Perfil findById(Long id) {
		return perfilRespository.findOne(id);
	}

	@Override
	public void delete(Perfil perfil) {
		perfilRespository.delete(perfil);
	}

	private boolean validaciones(Perfil perfil) throws ServiceExceptionCO {

		ServiceExceptionCO exception = null;
		String Error = null;
		/**
		 * Nombre   (sólo letras, obligatorio, longitud máxima 15)
		 */
		try {
			if (Objects.isNull(perfil.getNombre()) || perfil.getNombre().trim().isEmpty()) {
				Error = ERROR001;
				throw new ServiceExceptionCO(ERROR001);
			} else if (perfil.getNombre().length() > 15) {
				Error = ERROR002;
				throw new ServiceExceptionCO(ERROR002);
			} else if (!validarLetras(perfil.getNombre())) {
				Error = ERROR003;
				throw new ServiceExceptionCO(ERROR003);
			}
		} catch (Exception e) {
			exception = manejaExcepciones(exception, Error);
		}

		/**
		 * Apellido Paterno (solo letras, campo obligatorio, longitud máxima 15)
		 */
		try {
			if (Objects.isNull(perfil.getApePaterno())) {
				Error = ERROR004;
				throw new ServiceExceptionCO(ERROR004);
			} else if (perfil.getApePaterno().length() > 15) {
				Error = ERROR005;
				throw new ServiceExceptionCO(ERROR005);
			} else if (!validarLetras(perfil.getApePaterno())) {
				Error = ERROR006;
				throw new ServiceExceptionCO(ERROR006);
			}

		} catch (Exception e) {
			exception = manejaExcepciones(exception, Error);
		}

		/**
		 * Apellido  Materno (solo letras, campo opcional, longitud máxima 15)
		 */
		try {
			if (Objects.nonNull(perfil.getApeMaterno())) {
				if (perfil.getApeMaterno().length() > 15) {
					Error = ERROR007;
					throw new ServiceExceptionCO(ERROR007);
				} else if (!validarLetras(perfil.getApeMaterno())) {
					Error = ERROR008;
					throw new ServiceExceptionCO(ERROR008);
				}
			}
		} catch (Exception e) {
			exception = manejaExcepciones(exception, Error);
		}

		/**
		 * 	Edad (solo números positivos mayores a 12 , longitud máxima 3)
		 */
		try {
			if (Objects.nonNull(perfil.getEdad())) {
				if(perfil.getEdad() < 13){
					Error = ERROR009;
					throw new ServiceExceptionCO(ERROR009);
				}else if(perfil.getEdad() > 999){
					Error = ERROR010;
					throw new ServiceExceptionCO(ERROR010);
				}
			}
		} catch (Exception e) {
			exception = manejaExcepciones(exception, Error);
		}
			

		if (Objects.isNull(exception)) {
			return true;
		}
		throw exception;
	}

	/**
	 * Agrega todos los mensajes de las excepciones a una sola excepcion
	 * 
	 * @param origen
	 * @param nueva
	 */
	private ServiceExceptionCO manejaExcepciones(ServiceExceptionCO e, String codigo) {

		if (e == null) {
			return new ServiceExceptionCO(codigo, codigo);
		}

		if (Objects.nonNull(codigo)) {
			e.getCodigos().add(codigo);
		}

		return e;
	}

	public static boolean validarLetras(String bastidor) {
		Pattern pat = Pattern.compile("[a-zA-Z]+");
		Matcher mat = pat.matcher(bastidor);
		if (mat.matches()) {
			return true;
		} else {
			return false;
		}
	}

}
