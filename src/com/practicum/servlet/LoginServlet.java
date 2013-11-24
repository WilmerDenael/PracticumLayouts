package com.practicum.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.practicum.util.*;
import com.practicum.dao.*;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {

public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {

try
{	    

     UserBean prueba= new UserBean();
     UserBean user; 
     String rutForm =request.getParameter("rut");
     rutForm.replaceAll("\\.", "");
     int rut = Integer.parseInt(rutForm);
     prueba.setRut(rut);
     prueba.setPassword(request.getParameter("pass"));
     prueba = UserDAO.login(prueba);	   		    
     if (prueba.isValid())
     {  
    	 if (prueba.getType()==1) {
    		 System.out.println("Usuario Valido de tipo alumno");
    		 user = new AlumnoBean();
    		 user.parseUserBean(prueba);
    		 user=AlumnoDAO.login(user);
    		 if(user.isLegit()){
    			  HttpSession session = request.getSession(true);
    	          session.setAttribute("currentSessionUser",user);
    	          response.sendRedirect("index.jsp"); //logged-in page      
    		 }else{
    			 response.sendRedirect("invalidLogin.jsp"); //error page 
    		 }
    		 
		}else if(prueba.getType()==2){
			user = new EmpresaBean();
   		 	user.parseUserBean(prueba);
   		 	user=EmpresaDAO.login(user);
   		 	if(user.isLegit()){
   		 		HttpSession session = request.getSession(true);
   		 		session.setAttribute("currentSessionUser",user);
   		 		response.sendRedirect("index.jsp"); //logged-in page      
   		 	}else{
   		 		response.sendRedirect("invalidLogin.jsp"); //error page 
   		 	}
//		}else if(prueba.getType()==3){ Implementar con profesor
		}		
     }
	        
     else 
          response.sendRedirect("invalidLogin.jsp"); //error page 
} 
				
catch (Throwable theException) 	    
{
     System.out.println(theException); 
}
       }
	}