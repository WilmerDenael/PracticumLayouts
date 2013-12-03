package com.practicum.util;

import java.sql.Date;

public class DatoAcademico {
	
	private int id;
	private int run;
	private int idCurriculum;
	private String establecimiento;
	private String inicio;
	private String fin;
	private String descripcion;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRun() {
		return run;
	}
	public void setRun(int run) {
		this.run = run;
	}
	public int getIdCurriculum() {
		return idCurriculum;
	}
	public void setIdCurriculum(int idCurriculum) {
		this.idCurriculum = idCurriculum;
	}
	public String getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	
	}
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

	
}
