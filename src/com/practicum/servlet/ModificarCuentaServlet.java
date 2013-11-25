package com.practicum.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.practicum.util.*;
import com.practicum.dao.*;

public class ModificarCuentaServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	           throws ServletException, java.io.IOException {

		try
		{	    
			Boolean[] flagAlumno = new Boolean[3];
			Boolean[] flagEmpresa = new Boolean[3];
			
			HttpSession session = request.getSession(true);
			UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));
			
			System.out.println("RUT SESION: "+currentUser.getRut());
			System.out.println("TIPO SESION: "+currentUser.getType());
			
			UserBean user = new UserBean();
			user.parseUserBean(currentUser);
			
			String pass =request.getParameter("pass");
			String email =request.getParameter("email");
								
			user.setRut(currentUser.getRut());	
			
			//VALIDACION DE CAMBIOS
			if(currentUser.getType()==1){
				if(!email.equals("")){
					user.setEmail(email);
					flagAlumno[0]=UserDAO.genericStringUpdate("alumnos", "email", user.getEmail(), "run", user.getRut());
				}else flagAlumno[0]=false;
				if(!pass.equals("")){
					user.setPassword(pass);
					flagAlumno[1]=UserDAO.genericPassUpdate("alumnos", "pass", user.getPassword(), "run", user.getRut());
				}else flagAlumno[1]=false;
				if((flagAlumno[0]||flagAlumno[1])==true){
					session.setAttribute("currentSessionUser",user); 
					response.sendRedirect("modificacionExitoso.jsp");
				}else{
					response.sendRedirect("errorModificacion.jsp");
				}
			}else if(currentUser.getType()==2){
				if(!email.equals("")){
					user.setEmail(email);
					flagEmpresa[0]=UserDAO.genericStringUpdate("empresas", "email", user.getEmail(), "rut", user.getRut());
				}else flagEmpresa[0]=false;
				if(!pass.equals("")){
					user.setPassword(pass);
					flagEmpresa[1]=UserDAO.genericPassUpdate("empresas", "pass", user.getPassword(), "rut", user.getRut());
				}else flagEmpresa[1]=false;
				if((flagEmpresa[0]||flagEmpresa[1])==true){
					session.setAttribute("currentSessionUser",user); 
					response.sendRedirect("modificacionExitoso.jsp");
				}else{
					response.sendRedirect("errorModificacion.jsp");
				}
			}
			
		} 

		catch (Throwable theException) 	    
		{
			System.out.println(theException); 
		}
	}
}
