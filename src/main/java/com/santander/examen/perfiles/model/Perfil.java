package com.santander.examen.perfiles.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Document(collection = "perfiles")
@JsonPropertyOrder({ "userId", "name" })
@ApiModel("Model Perfil")
public class Perfil {

	@Id
	private Long userId;

	@NotNull
	@ApiModelProperty(value = "perfil nombre", required = true)
	private String nombre;

	@NotNull
	@ApiModelProperty(value = "perfil apellido paterno", required = true)
	private String apePaterno;

	@NotNull
	@ApiModelProperty(value = "perfil apellido materno", required = true)
	private String apeMaterno;

	@NotNull
	@ApiModelProperty(value = "perfil fecha de nacimiento", required = true)
	private Date fechaNacimiento;
	
	@NotNull
	@ApiModelProperty(value = "perfil edad", required = true)
	private Integer edad;
	
	private String foto;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApePaterno() {
		return apePaterno;
	}

	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	public String getApeMaterno() {
		return apeMaterno;
	}

	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	
}
