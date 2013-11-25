package com.practicum.servlet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.practicum.util.*;
import com.practicum.dao.*;

public class IngresarInfoPersonalServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	           throws ServletException, java.io.IOException {

		try
		{	    
			Boolean[] flagAlumno = new Boolean[10];//tamaño depende de la cantidad de datos del formulario
			
			HttpSession session = request.getSession(true);
			UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));
			
			System.out.println("RUT SESION: "+currentUser.getRut());
			System.out.println("TIPO SESION: "+currentUser.getType());
			
			UserBean userb = new UserBean();
			AlumnoBean alumnob = new AlumnoBean();
			
			userb.parseUserBean(currentUser);
			
			//ACA CAPTURAR LOS DATOS DEL FORMULARIO
			String primerNombre =request.getParameter("primerNombre");
			String segundoNombre =request.getParameter("segundoNombre");
			String paterno =request.getParameter("paterno");
			String materno =request.getParameter("materno");
			String civil =request.getParameter("civil");
			String direccion =request.getParameter("direccion");
			String nacionalidad =request.getParameter("nacionalidad");
			String nacimiento =request.getParameter("nacimiento"); //FALTA GUARDAR ESTE DATO !
			String referencia =request.getParameter("referencia");
			String datosExtra =request.getParameter("datosExtra");
			

			
			userb.setRut(currentUser.getRut());	
			System.out.println(userb.getRut());
			
			//VALIDACION DE CAMBIOS
				if(!primerNombre.equals("")){
					alumnob.setPrimerNombre(primerNombre);
					flagAlumno[0]=UserDAO.genericStringUpdate("alumnos", "primer_Nombre", alumnob.getPrimerNombre(), "run", userb.getRut());
				}else flagAlumno[0]=false;
				if(!segundoNombre.equals("")){
					alumnob.setSegundoNombre(segundoNombre);
					flagAlumno[1]=UserDAO.genericStringUpdate("alumnos", "segundo_Nombre", alumnob.getSegundoNombre(), "run", userb.getRut());
				}else flagAlumno[1]=false;
				if(!paterno.equals("")){
					alumnob.setApellidoPaterno(paterno);
					flagAlumno[2]=UserDAO.genericStringUpdate("alumnos", "paterno", alumnob.getApellidoPaterno(), "run", userb.getRut());
				}else flagAlumno[2]=false;
				if(!materno.equals("")){
					alumnob.setApellidoMaterno(materno);
					flagAlumno[3]=UserDAO.genericStringUpdate("alumnos", "materno", alumnob.getApellidoMaterno(), "run", userb.getRut());
				}else flagAlumno[3]=false;
				if(!civil.equals("")){
					alumnob.setEstadoCivil(civil);
					flagAlumno[4]=UserDAO.genericStringUpdate("alumnos", "estado_civil", alumnob.getEstadoCivil(), "run", userb.getRut());
				}else flagAlumno[4]=false;
				if(!direccion.equals("")){
					alumnob.setDireccion(direccion);
					flagAlumno[5]=UserDAO.genericStringUpdate("alumnos", "direccion", alumnob.getDireccion(), "run", userb.getRut());
				}else flagAlumno[5]=false;
				if(!nacionalidad.equals("")){
					alumnob.setNacionalidad(nacionalidad);
					flagAlumno[6]=UserDAO.genericStringUpdate("alumnos", "nacionalidad", alumnob.getNacionalidad(), "run", userb.getRut());
				}else flagAlumno[6]=false;
				if(!referencia.equals("")){
					alumnob.setReferencia(referencia);
					flagAlumno[7]=UserDAO.genericStringUpdate("alumnos", "referencia", alumnob.getReferencia(), "run", userb.getRut());
				}else flagAlumno[7]=false;
				if(!datosExtra.equals("")){
					alumnob.setDatosExtra(datosExtra);
					flagAlumno[8]=UserDAO.genericStringUpdate("alumnos", "datos_adicionales", alumnob.getDatosExtra(), "run", userb.getRut());
				}else flagAlumno[8]=false;
				/*if(!nacimiento.equals("")){
					alumnob.setFechaNacimiento(nacimiento);
					flagAlumno[9]=UserDAO.genericDateUpdate("alumnos", "nacimiento", alumnob.getFechaNacimiento(), "run", userb.getRut());
				}else flagAlumno[9]=false;*/
				
				
				if((flagAlumno[0]||flagAlumno[1]||flagAlumno[2]||flagAlumno[3]||flagAlumno[4]||
						flagAlumno[5]||flagAlumno[6]||flagAlumno[7]||flagAlumno[8])==true){
					session.setAttribute("currentSessionUser",userb); 
					response.sendRedirect("modificacionExitoso.jsp");
				}else{
					response.sendRedirect("errorModificacion.jsp");
				}
		} 
		catch (Throwable theException) 	    
		{
			System.out.println(theException); 
		}
	}
	
	
}
