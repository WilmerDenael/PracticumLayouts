package com.practicum.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.practicum.dao.CurriculumDAO;
import com.practicum.util.*;

public class IngresarCurriculumServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	           throws ServletException, java.io.IOException {

		try
		{	
			Boolean[] flag = new Boolean[10];//tamaño depende de la cantidad de datos del formulario
			
			HttpSession session = request.getSession(true);
			AlumnoBean alumno = ((AlumnoBean) (session.getAttribute("currentSessionUser")));
			
			System.out.println("RUT SESION: "+alumno.getRut());
			System.out.println("TIPO SESION: "+alumno.getType());
			
			Curriculum curriculum = new Curriculum();
			List<AreaInteres> areas = new ArrayList<AreaInteres>();
			List<Idioma> idiomas = new ArrayList<Idioma>();
			List<DatoAcademico> datosAcademicos= new ArrayList<DatoAcademico>();
			List<HistorialLaboral> laborales = new ArrayList<HistorialLaboral>();
			
			//ACA CAPTURAR LOS DATOS DEL FORMULARIO
			
//			String primerNombre =request.getParameter("establecimiento");
//			String segundoNombre =request.getParameter("segundoNombre");
//			String paterno =request.getParameter("paterno");
//			String materno =request.getParameter("materno");
//			String civil =request.getParameter("civil");
//			String direccion =request.getParameter("direccion");
//			String nacionalidad =request.getParameter("nacionalidad");
//			String nacimiento =request.getParameter("nacimiento"); //FALTA GUARDAR ESTE DATO !
//			String referencia =request.getParameter("referencia");
//			String datosExtra =request.getParameter("datosExtra");
			
			//PASO DE DATOS DE FORMULARIO A LAS LISTAS
			
			// SE ASOCIAN LOS DATOS AL CURRICULUM
			curriculum.setId(1);
			curriculum.setRun(alumno.getRut());
			curriculum.setActive(true);
			curriculum.setDatosAcademicos(datosAcademicos);
			curriculum.setIdiomas(idiomas);
			curriculum.setIntereses(areas);
			curriculum.setLaborales(laborales);
			curriculum.setConfidencial(true); //ajustar
			curriculum.setUltimaActualizacion(null); //dar fecha
			
			// Se inserta el curriculum al DAO
			alumno.setCurriculum(curriculum);
			CurriculumDAO.insertCurriculum(alumno);
			
			
			
			
		}catch (Throwable theException) 	    
		{
		     System.out.println(theException); 
		}
	}
}
		       
