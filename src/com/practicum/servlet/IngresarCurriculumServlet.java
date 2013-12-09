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
			
			//Se  Definen las estructuras
			
			Curriculum curriculum = new Curriculum();
			List<AreaInteres> listAreas = new ArrayList<AreaInteres>();
			List<Idioma> listIdiomas = new ArrayList<Idioma>();
			List<DatoAcademico> datosAcademicos= new ArrayList<DatoAcademico>();
			List<HistorialLaboral> laborales = new ArrayList<HistorialLaboral>();
			
			
			// Captura de datos academicos 
			DatoAcademico academico = new DatoAcademico();
			String contadorDA = request.getParameter("contadorDA");
			String descripcion_da = request.getParameter("descripcion_da");
			String establecimiento_da = request.getParameter("establecimiento_da");
			String fechaInicio_da = request.getParameter("fechaInicio_da");
			String fechaTermino_da = request.getParameter("fechaTermino_da");
			int cda = Integer.parseInt(contadorDA);
			System.out.println(contadorDA + " " + descripcion_da + " " + establecimiento_da + " " + 
					fechaInicio_da + " " + fechaTermino_da);
			academico.setDescripcion(descripcion_da);
			academico.setEstablecimiento(establecimiento_da);
			academico.setInicio(fechaInicio_da);
			academico.setFin(fechaTermino_da);
			datosAcademicos.add(academico);
			
			for (int i = 2; i <= cda; i++) {
				academico= new DatoAcademico();
				descripcion_da = request.getParameter("descripcion_da:"+i);
				establecimiento_da = request.getParameter("establecimiento_da:"+i);
				fechaInicio_da = request.getParameter("fechaInicio_da:"+i);
				fechaTermino_da = request.getParameter("fechaTermino_da:"+i);
				System.out.println(contadorDA + " " + descripcion_da + " " + establecimiento_da + " " + 
						fechaInicio_da + " " + fechaTermino_da);
				academico.setDescripcion(descripcion_da);
				academico.setEstablecimiento(establecimiento_da);
				academico.setInicio(fechaInicio_da);
				academico.setFin(fechaTermino_da);
				datosAcademicos.add(academico);
			}
				
		System.out.println("Lista de datos academicos "+datosAcademicos.size());
//			// Captura de historial academico 
			HistorialLaboral laboral = new HistorialLaboral();
			String contadorHL = request.getParameter("contadorHL");
			String descripcion_hl = request.getParameter("descripcion_hl");
			String establecimiento_hl = request.getParameter("establecimiento_hl");
			String cargo_hl = request.getParameter("cargo_hl");
			String fechaInicio_hl = request.getParameter("fechaInicio_hl");
			String fechaTermino_hl = request.getParameter("fechaTermino_hl");
			System.out.println(contadorHL + " " + descripcion_hl + " " + establecimiento_hl + " " + cargo_hl + " " + 
					fechaInicio_hl + " " + fechaTermino_hl);
			laboral.setDescripcion(descripcion_hl);
			laboral.setEstablecimiento(establecimiento_hl);
			laboral.setCargo(cargo_hl);
			laboral.setInicio(fechaInicio_hl);
			laboral.setFin(fechaTermino_hl);
			laborales.add(laboral);
			int chl = Integer.parseInt(contadorHL);
			
			for (int i = 2; i <= chl; i++) {
				laboral = new HistorialLaboral();
				descripcion_hl = request.getParameter("descripcion_hl"+i);
				establecimiento_hl = request.getParameter("establecimiento_hl"+i);
				cargo_hl = request.getParameter("cargo_hl"+i);
				fechaInicio_hl = request.getParameter("fechaInicio_hl"+i);
				fechaTermino_hl = request.getParameter("fechaTermino_hl"+i);
				System.out.println(contadorHL + " " + descripcion_hl + " " + establecimiento_hl + " " + cargo_hl + " " + 
						fechaInicio_hl + " " + fechaTermino_hl);
				laboral.setDescripcion(descripcion_hl);
				laboral.setEstablecimiento(establecimiento_hl);
				laboral.setCargo(cargo_hl);
				laboral.setInicio(fechaInicio_hl);
				laboral.setFin(fechaTermino_hl);
				laborales.add(laboral);
			}
			System.out.println("Laboral: "+laborales.size());
			String[] areas = request.getParameterValues("areas[]");
			AreaInteres interes;
			for(int i =0; i < areas.length; i++){
				interes= new AreaInteres();
				interes.setArea(areas[i]);
				listAreas.add(interes);
			}
			System.out.println("Areas de Interes: "+listAreas.size());
//			Idioma idioma;
//			String[] idiomas = request.getParameterValues("idiomas[]");
//			for(int i =0; i < idiomas.length; i++){
//				idioma= new Idioma();
//				idioma.setIdioma(idiomas[i]);
//				listIdiomas.add(idioma);
//			}
//			System.out.println("Lista de Idiomas: " +listIdiomas.size());
			//System.out.println(areas.toString());
			
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
					
//			// SE ASOCIAN LOS DATOS AL CURRICULUM
//			curriculum.setId(1);
//			curriculum.setRun(alumno.getRut());
//			curriculum.setActive(true);
//			curriculum.setDatosAcademicos(datosAcademicos);
//			curriculum.setIdiomas(idiomas);
//			curriculum.setIntereses(areas);
//			curriculum.setLaborales(laborales);
//			curriculum.setConfidencial(true); //ajustar
//			curriculum.setUltimaActualizacion(null); //dar fecha
			
//			// Se inserta el curriculum al DAO
//			alumno.setCurriculum(curriculum);
//			CurriculumDAO.insertCurriculum(alumno);
			
			
			
			
		}catch (Throwable theException) 	    
		{
		     System.out.println(theException); 
		}
	}
}
		       
