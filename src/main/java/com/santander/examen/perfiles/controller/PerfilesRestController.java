package com.santander.examen.perfiles.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.santander.examen.perfiles.exceptions.ServiceExceptionCO;
import com.santander.examen.perfiles.model.Perfil;
import com.santander.examen.perfiles.service.PerfilService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/app")
@Api(value = "microservice perfiles", description = "This API has a CRUD for Perfiles")
public class PerfilesRestController {

	@Autowired
	private PerfilService perfilService;

	/**
	 * Obtener todos los perfiles exitentes
	 * 
	 * @return a controller
	 */
	@RequestMapping(value = "/perfil/all", method = RequestMethod.GET)
	@ApiOperation(value = "Obtiene todos los perfiles", notes = "Obtiene todos los perfiles exitentes")
	public ResponseEntity<List<Perfil>> perfilAll() {
		return ResponseEntity.ok(perfilService.findAll());
	}

	@RequestMapping("/perfil/{id}")
	@ApiOperation(value = "Buscar por id", notes = "Busca perfil por Id")
	public ResponseEntity<Perfil> getPerfilById(@PathVariable Long id) {
		return ResponseEntity.ok(this.perfilService.findById(id));
	}

	/**
	 * Guarda un nuevo perfil
	 * 
	 * @param perfil
	 * @return Perfil
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/perfil", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Crea un perfil", notes = "Crea un nuevo perfil")
	public ResponseEntity savePerfil(@RequestBody @Valid Perfil perfil) {
		try {
			return ResponseEntity.ok(perfilService.savePerfil(perfil));
		} catch (ServiceExceptionCO e) {
			StringBuilder sb = new StringBuilder();
			int contador = 0;
			for(String codigo: e.getCodigos()){
				sb.append(codigo);
				contador ++;
				if(e.getCodigos().size() > 1 && e.getCodigos().size() != contador){
					sb.append(" - ");
				}
			}
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(sb);
		}catch (Exception e) {
			  return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Message");
		}
	}

	/**
	 * Update perfil
	 * 
	 * @param perfil
	 * @param id
	 * @return Perfil
	 */
	@RequestMapping(value = "/perfil/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Actualiza un perfil", notes = "Actualiza perfil por id")
	public ResponseEntity<Perfil> updatePerfil(@RequestBody Perfil perfil, @PathVariable Long id) {
		Perfil recurrent = this.perfilService.findById(id);
		recurrent.setNombre(perfil.getNombre());
		recurrent.setApePaterno(perfil.getApePaterno());
		recurrent.setApeMaterno(perfil.getApeMaterno());
		recurrent.setEdad(perfil.getEdad());
		recurrent.setFechaNacimiento(perfil.getFechaNacimiento());
		recurrent.setFoto(perfil.getFoto());
		try {
			this.perfilService.savePerfil(recurrent);
		} catch (ServiceExceptionCO e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(recurrent);
	}

	/**
	 * Elimina un perfil
	 * 
	 * @param id
	 * @return void
	 */
	@RequestMapping(value = "/perfil/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Elimina un perfil", notes = "Elimina un perfil por id")
	public ResponseEntity<Void> deletePerfil(@PathVariable Long id) {
		Perfil recurrent = this.perfilService.findById(id);
		this.perfilService.delete(recurrent);
		return ResponseEntity.noContent().build();
	}

}
