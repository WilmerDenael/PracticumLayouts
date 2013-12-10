package com.practicum.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.practicum.util.*;
import com.practicum.dao.*;



public class EliminarCuentaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	           throws ServletException, java.io.IOException {

		try
		{	    
			HttpSession session = request.getSession(true);
			UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));
						
			UserBean user = new UserBean();
		    user.setRut(currentUser.getRut());

		    if(currentUser.getType()==1){
		    	user = AlumnoDAO.eliminar(user);
		    }else{
		    	user = EmpresaDAO.eliminar(user);
		    }
			
	    
			if (!user.isValid())
			{
    
				session.setAttribute("currentSessionUser",user); 
				response.sendRedirect("eliminacionExitoso.jsp"); //logged-in page      		
			}
 
			else 
				response.sendRedirect("errorEliminacion.jsp"); //error page 
		} 


		catch (Throwable theException) 	    
		{
			System.out.println(theException); 
		}
	}
}
