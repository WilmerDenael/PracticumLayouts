package com.practicum.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.practicum.util.*;
import com.practicum.dao.*;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

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
    			  System.out.println("Usuario esta correcto, realizando la carga de datos");
    			  AlumnoBean alumnoUser= new AlumnoBean();
    			  alumnoUser = AlumnoDAO.selectAlumno(user);    			  
    			  HttpSession session = request.getSession(true);
    			  System.out.println("tipo: "+alumnoUser.getType());
    	          session.setAttribute("currentSessionUser",alumnoUser);
    	          response.sendRedirect("index.jsp"); //logged-in page      
    		 }else{
    			 response.sendRedirect("invalidLogin.jsp"); //error page 
    		 }
    		 
		}else if(prueba.getType()==2){
			user = new EmpresaBean();
   		 	user.parseUserBean(prueba);
   		 	user=EmpresaDAO.login(user);
   		 	if(user.isLegit()){
   		 		EmpresaBean empresaUser = new EmpresaBean();
   		 		empresaUser = EmpresaDAO.selectEmpresa(user);
   		 		HttpSession session = request.getSession(true);
   		 		session.setAttribute("currentSessionUser",empresaUser);
   		 		System.out.println("tipo: "+empresaUser.getType());
   		 		response.sendRedirect("index.jsp"); //logged-in page      
   		 	}else{
   		 		response.sendRedirect("invalidLogin.jsp"); //error page 
   		 	}
		//}else if(prueba.getType()==3){ login profesor
			
		}else if(prueba.getType()==4){
			System.out.println("Usuario Valido de tipo administrador");
			user = new AdminBean();
			
			user.parseUserBean(prueba);
   		 	user=AdminDAO.login(user);
   		 	System.out.println("User type: "+user.getType());
   		 	if(user.isLegit()){
		 		AdminBean adminUser = new AdminBean();
		 		adminUser = AdminDAO.selectAdmin(user);
		 		HttpSession session = request.getSession(true);
		 		session.setAttribute("currentSessionUser",adminUser);
		 		System.out.println("tipo: "+adminUser.getType());
		 		response.sendRedirect("index.jsp"); //logged-in page      
		 	}else{
		 		response.sendRedirect("invalidLogin.jsp"); //error page 
		 	}
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