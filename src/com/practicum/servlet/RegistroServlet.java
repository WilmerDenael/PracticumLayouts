package com.practicum.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practicum.util.*;
import com.practicum.dao.*;

public class RegistroServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

public void doGet(HttpServletRequest request, HttpServletResponse response) 
	           throws ServletException, java.io.IOException {

		try
		{	    

			UserBean user=null;
			String rutForm =request.getParameter("rut");
			rutForm=rutForm.replaceAll("\\.", "");	
			int rut = Integer.parseInt(rutForm);
			int tipo =Integer.parseInt(request.getParameter("type"));
			if(tipo==1){
				user = new AlumnoBean();
				user.setRut(rut);
				user.setPassword(request.getParameter("pass"));
				user.setEmail(request.getParameter("email"));
				user.setType(tipo);
				user = AlumnoDAO.registrar(user); 
			}else if(tipo==2){
				user = new EmpresaBean();
				user.setRut(rut);
				user.setPassword(request.getParameter("pass"));
				user.setEmail(request.getParameter("email"));
				user.setType(tipo);		
				user = EmpresaDAO.registrar(user); 
			}
			if(user!=null){
				if (user.isValid())
				{
					//HttpSession session = request.getSession(true);	    
					//session.setAttribute("currentSessionUser",user); 
					response.sendRedirect("registroExitoso.jsp"); //logged-in page      		
				}
				else 
					response.sendRedirect("errorRegistro.jsp"); //error page 
			}	 			
		}

		catch (Throwable theException) 	    
		{
			System.out.println(theException); 
		}
	}
}
