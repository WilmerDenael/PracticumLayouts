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
			
			//ACA CAPTURAR LOS DATOS DEL FORMULARIO
			
			// Captura de datos academicos 
			String contadorDA = request.getParameter("contadorDA");
			String descripcion_da = request.getParameter("descripcion_da");
			String establecimiento_da = request.getParameter("establecimiento_da");
			String fechaInicio_da = request.getParameter("fechaInicio_da");
			String fechaTermino_da = request.getParameter("fechaTermino_da");
			System.out.println(contadorDA + " " + descripcion_da + " " + establecimiento_da + " " + 
					fechaInicio_da + " " + fechaTermino_da);
			
			int cda = Integer.parseInt(contadorDA);
			
			for (int i = 2; i <= cda; i++) {
				descripcion_da = request.getParameter("descripcion_da:"+i);
				establecimiento_da = request.getParameter("establecimiento_da:"+i);
				fechaInicio_da = request.getParameter("fechaInicio_da:"+i);
				fechaTermino_da = request.getParameter("fechaTermino_da:"+i);
				System.out.println(contadorDA + " " + descripcion_da + " " + establecimiento_da + " " + 
						fechaInicio_da + " " + fechaTermino_da);
			}
			
//			// Captura de historial academico 
			
			String contadorHL = request.getParameter("contadorHL");
			String descripcion_hl = request.getParameter("descripcion_hl");
			String establecimiento_hl = request.getParameter("establecimiento_hl");
			String cargo_hl = request.getParameter("cargo_hl");
			String fechaInicio_hl = request.getParameter("fechaInicio_hl");
			String fechaTermino_hl = request.getParameter("fechaTermino_hl");
			System.out.println(contadorHL + " " + descripcion_hl + " " + establecimiento_hl + " " + cargo_hl + " " + 
					fechaInicio_hl + " " + fechaTermino_hl);
			
			int chl = Integer.parseInt(contadorHL);
			
			for (int i = 2; i <= chl; i++) {
				descripcion_hl = request.getParameter("descripcion_hl"+i);
				establecimiento_hl = request.getParameter("establecimiento_hl"+i);
				cargo_hl = request.getParameter("cargo_hl"+i);
				fechaInicio_hl = request.getParameter("fechaInicio_hl"+i);
				fechaTermino_hl = request.getParameter("fechaTermino_hl"+i);
				System.out.println(contadorHL + " " + descripcion_hl + " " + establecimiento_hl + " " + cargo_hl + " " + 
						fechaInicio_hl + " " + fechaTermino_hl);
			}

			String[] areas = request.getParameterValues("areas[]");
			for(int i =0; i < areas.length; i++){
				System.out.println(areas[i]);
				
			}
			
			String[] idiomas = request.getParameterValues("idiomas[]");
			for(int i =0; i < idiomas.length; i++){
				System.out.println(idiomas[i]);
				
			}
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
			
			//PASO DE DATOS
			
//			Curriculum curriculum = new Curriculum();
//			List<AreaInteres> areas = new ArrayList<AreaInteres>();
//			List<Idioma> idiomas = new ArrayList<Idioma>();
//			List<DatoAcademico> datosAcademicos= new ArrayList<DatoAcademico>();
//			List<HistorialLaboral> laborales = new ArrayList<HistorialLaboral>();
			
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
		       
