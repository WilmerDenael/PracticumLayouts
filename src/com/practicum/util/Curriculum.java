package com.practicum.util;

import java.util.Date;
import java.util.List;

public class Curriculum {
	
	private int id;
	private int run;
	private boolean active;
	private Date ultimaActualizacion;
	private boolean confidencial;
	private List<DatoAcademico> datosAcademicos;
	private List<HistorialLaboral> laborales;
	private List<Idioma> idiomas;
	private List<AreaInteres> intereses;
	
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
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Date getUltimaActualizacion() {
		return ultimaActualizacion;
	}
	public void setUltimaActualizacion(Date ultimaActualizacion) { 
		this.ultimaActualizacion = ultimaActualizacion;
	}
	public boolean isConfidencial() {
		return confidencial;
	}
	public void setConfidencial(boolean confidencialidad) {
		this.confidencial = confidencialidad;
	}
	public List<DatoAcademico> getDatosAcademicos() {
		return datosAcademicos;
	}
	public void setDatosAcademicos(List<DatoAcademico> datosAcademicos) {
		this.datosAcademicos = datosAcademicos;
	}
	public List<HistorialLaboral> getLaborales() {
		return laborales;
	}
	public void setLaborales(List<HistorialLaboral> laborales) {
		this.laborales = laborales;
	}
	public List<Idioma> getIdiomas() {
		return idiomas;
	}
	public void setIdiomas(List<Idioma> idiomas) {
		this.idiomas = idiomas;
	}
	public List<AreaInteres> getIntereses() {
		return intereses;
	}
	public void setIntereses(List<AreaInteres> intereses) {
		this.intereses = intereses;
	}	
}
