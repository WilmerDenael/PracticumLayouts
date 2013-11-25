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

public class IngresarInfoEmpresaServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	           throws ServletException, java.io.IOException {

		try
		{	    
			Boolean[] flagEmpresa = new Boolean[10];//tamaño depende de la cantidad de datos del formulario
			
			HttpSession session = request.getSession(true);
			UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));
			
			System.out.println("RUT SESION: "+currentUser.getRut());
			System.out.println("TIPO SESION: "+currentUser.getType());
			
			UserBean userb = new UserBean();
			EmpresaBean empresab = new EmpresaBean();
			
			userb.parseUserBean(currentUser);
			
			//ACA CAPTURAR LOS DATOS DEL FORMULARIO
			String nombre =request.getParameter("nombre");
			String numTrab =request.getParameter("numTrab");
			String nacionalidad =request.getParameter("nacionalidad");
			String areaLaboral =request.getParameter("areaLaboral");
			String direccion =request.getParameter("direccion");
			String descripcion =request.getParameter("descripcion");

						
			userb.setRut(currentUser.getRut());	
			System.out.println(userb.getRut());
			
			//VALIDACION DE CAMBIOS
				if(!nombre.equals("")){
					empresab.setNombre(nombre);
					flagEmpresa[0]=UserDAO.genericStringUpdate("empresas", "nombre", empresab.getNombre(), "rut", userb.getRut());
				}else flagEmpresa[0]=false;
				if(!numTrab.equals("")){
					empresab.setNumeroTrabajadores(Integer.parseInt(numTrab));
					flagEmpresa[1]=UserDAO.genericStringUpdate("empresas", "numero_trabajadores", numTrab, "rut", userb.getRut());
				}else flagEmpresa[1]=false;
				if(!nacionalidad.equals("")){
					empresab.setNacionalidad(nacionalidad);
					flagEmpresa[2]=UserDAO.genericStringUpdate("empresas", "nacionalidad", empresab.getNacionalidad(), "rut", userb.getRut());
				}else flagEmpresa[2]=false;
				if(!areaLaboral.equals("")){
					empresab.setAreaLaboral(areaLaboral);
					flagEmpresa[3]=UserDAO.genericStringUpdate("empresas", "area_laboral", empresab.getAreaLaboral(), "rut", userb.getRut());
				}else flagEmpresa[3]=false;
				if(!direccion.equals("")){
					empresab.setDireccion(direccion);
					flagEmpresa[4]=UserDAO.genericStringUpdate("empresas", "direccion", empresab.getDireccion(), "rut", userb.getRut());
				}else flagEmpresa[4]=false;
				if(!descripcion.equals("")){
					empresab.setDescripcion(descripcion);
					flagEmpresa[5]=UserDAO.genericStringUpdate("empresas", "descripcion", empresab.getNacionalidad(), "rut", userb.getRut());
				}else flagEmpresa[5]=false;				
				
				if((flagEmpresa[0]||flagEmpresa[1]||flagEmpresa[2]||flagEmpresa[3]||flagEmpresa[4]||flagEmpresa[5])==true){
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
