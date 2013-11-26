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
			EmpresaBean empresa = ((EmpresaBean) (session.getAttribute("currentSessionUser")));
			
			System.out.println("RUT SESION: "+empresa.getRut());
			System.out.println("TIPO SESION: "+empresa.getType());
			
			//ACA CAPTURAR LOS DATOS DEL FORMULARIO
			String nombre =request.getParameter("nombre");
			String numTrab =request.getParameter("numTrab");
			String nacionalidad =request.getParameter("nacionalidad");
			String areaLaboral =request.getParameter("areaLaboral");
			String direccion =request.getParameter("direccion");
			String descripcion =request.getParameter("descripcion");
			
			//VALIDACION DE CAMBIOS
				if(!nombre.equals("")){
					empresa.setNombre(nombre);
					flagEmpresa[0]=UserDAO.genericStringUpdate("empresas", "nombre", empresa.getNombre(), "rut", empresa.getRut());
				}else flagEmpresa[0]=false;
				if(!numTrab.equals("")){
					empresa.setNumeroTrabajadores(Integer.parseInt(numTrab));
					flagEmpresa[1]=UserDAO.genericStringUpdate("empresas", "numero_trabajadores", numTrab, "rut", empresa.getRut());
				}else flagEmpresa[1]=false;
				if(!nacionalidad.equals("")){
					empresa.setNacionalidad(nacionalidad);
					flagEmpresa[2]=UserDAO.genericStringUpdate("empresas", "nacionalidad", empresa.getNacionalidad(), "rut", empresa.getRut());
				}else flagEmpresa[2]=false;
				if(!areaLaboral.equals("")){
					empresa.setAreaLaboral(areaLaboral);
					flagEmpresa[3]=UserDAO.genericStringUpdate("empresas", "area_laboral", empresa.getAreaLaboral(), "rut", empresa.getRut());
				}else flagEmpresa[3]=false;
				if(!direccion.equals("")){
					empresa.setDireccion(direccion);
					flagEmpresa[4]=UserDAO.genericStringUpdate("empresas", "direccion", empresa.getDireccion(), "rut", empresa.getRut());
				}else flagEmpresa[4]=false;
				if(!descripcion.equals("")){
					empresa.setDescripcion(descripcion);
					flagEmpresa[5]=UserDAO.genericStringUpdate("empresas", "descripcion", empresa.getNacionalidad(), "rut", empresa.getRut());
				}else flagEmpresa[5]=false;				
				
				if((flagEmpresa[0]||flagEmpresa[1]||flagEmpresa[2]||flagEmpresa[3]||flagEmpresa[4]||flagEmpresa[5])==true){
					session.setAttribute("currentSessionUser",empresa); 
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
