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
/**
 * Servlet implementation class IngresarNotasServlet
 */
public class IngresarNotasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IngresarNotasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
			
//			Asignatura asignatura = new Asignatura();
//			List<Asignatura> listAsignaturas = new ArrayList<Asignatura>();

			
			
			// Captura de datos academicos 
//			As academico = new DatoAcademico();
			String contadorNO = request.getParameter("contadorNO");
			String asignatura = request.getParameter("asignatura");
			String nota = request.getParameter("nota");
			int cno = Integer.parseInt(contadorNO);
			System.out.println(contadorNO + " " + asignatura + " " + nota);
//			academico.setDescripcion(descripcion_da);
//			academico.setEstablecimiento(establecimiento_da);
//			academico.setInicio(fechaInicio_da);
//			academico.setFin(fechaTermino_da);
//			datosAcademicos.add(academico);
			
			for (int i = 2; i <= cno; i++) {
//				academico= new DatoAcademico();
				asignatura = request.getParameter("asignatura:"+i);
				nota = request.getParameter("nota:"+i);
				System.out.println(contadorNO + " " + asignatura + " " + nota);
//				academico.setDescripcion(descripcion_da);
//				academico.setEstablecimiento(establecimiento_da);
//				academico.setInicio(fechaInicio_da);
//				academico.setFin(fechaTermino_da);
//				datosAcademicos.add(academico);
			}
				
//		System.out.println("Lista de datos academicos "+datosAcademicos.size());
		} 
		catch (Throwable theException) 	    
		{
			System.out.println(theException); 
		}
	}


}
