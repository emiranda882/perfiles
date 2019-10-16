package com.santander.examen.perfiles.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.santander.examen.perfiles.model.Perfil;

public interface PerfilRepository  extends MongoRepository<Perfil, Long>{

}
