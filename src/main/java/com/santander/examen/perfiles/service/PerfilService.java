package com.santander.examen.perfiles.service;

import java.util.List;

import com.santander.examen.perfiles.exceptions.ServiceExceptionCO;
import com.santander.examen.perfiles.model.Perfil;

public interface PerfilService {

	List<Perfil> findAll();

	Perfil savePerfil(Perfil perfil) throws ServiceExceptionCO;
	
	Perfil findById(Long id);

	void delete(Perfil perfil);
}
