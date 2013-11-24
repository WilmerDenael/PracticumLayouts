package com.practicum.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.practicum.dao.ConnectionManager;
import com.practicum.util.*;

public class AlumnoDAO{
	
   static Connection currentCon = null;
   static ResultSet rs = null;  
   
    public static UserBean login(UserBean bean){
	      //preparing some objects for connection 
	      Statement stmt = null;    	
	      int rut = bean.getRut();    
	      String searchQuery =
	            "select(AES_DECRYPT(pass,'gatin')) from alumnos where run="
	                     + rut;
		    
	   // "System.out.println" prints in the console; Normally used to trace the process
	   System.out.println("Your rut is " + rut);          
	   System.out.println("Query: "+searchQuery);
		    
	   try 
	   {
	      //connect to DB 
	      currentCon = ConnectionManager.getConnection();
	      stmt=currentCon.createStatement();
	      rs = stmt.executeQuery(searchQuery);	        
	      boolean more = rs.next();
	      // if user does not exist set the isValid variable to false
	      if (!more) 
	      {
	         System.out.println("Sorry, you are not a registered user! Please sign up first");
	         bean.setValid(false);
	      } 
		        
	      //if user exists set the isLegit variable to true
	      else if (more) 
	      {
	    	 System.out.println("Hay resultados!");
	    	 String pass=rs.getString(1); 
	         System.out.println("Password: "+pass);
	         if(pass.equals(bean.getPassword())){
	        	 bean.setLegit(true);
	         }
	      }
	   } 

	   catch (Exception ex) 
	   {
	      System.out.println("Log In failed: An Exception has occurred! " + ex);
	   } 
		    
	   //some exception handling
	   finally 
	   {
	      if (rs != null)	{
	         try {
	            rs.close();
	         } catch (Exception e) {}
	            rs = null;
	         }
		
	      if (stmt != null) {
	         try {
	            stmt.close();
	         } catch (Exception e) {}
	            stmt = null;
	         }
		
	      if (currentCon != null) {
	         try {
	            currentCon.close();
	         } catch (Exception e) {
	         }

	         currentCon = null;
	      }
	   }

	return bean;
		
	   }

   public static UserBean registrar(UserBean bean){
		  Statement stmt = null;    
			
	      int rut = bean.getRut();    
	      String password = bean.getPassword();
	      String email = bean.getEmail();
	      //rut=rut.toUpperCase(); // Ojo con esta linea!
	      String Query =
	              "INSERT INTO alumnos(run,pass,email) values ('"
	                       + rut
	                       + "',"
	                       + "AES_ENCRYPT('"+ password +"','gatin')"
	                       + ",'"
	                       +email
	                       + "')";
	      String searchQuery =
	              "select * from alumnos where run='"
	                       + rut
	                       + "'";
	      
	      System.out.println("Your run is " + rut);          
	      System.out.println("Your password is " + password);
	      System.out.println("Your email is " +email);
	      System.out.println("Query: "+Query);

	      try 
	      {
	        
	         currentCon = ConnectionManager.getConnection();
	         stmt=currentCon.createStatement();
	         rs = stmt.executeQuery(searchQuery);	        
	         boolean more = rs.next();
	   	       
	         // if user does not exist set the isValid variable to false
	         if (!more) 
	         {
	        	 System.out.println("Un usuario mas");
	        	 stmt.executeUpdate(Query);
	        	 bean.setValid(true);
	         } 
	   	        
	         //if user exists set the isValid variable to true
	         else if (more) 
	         {
	            bean.setValid(false);
	         } 
	      }
	      catch (Exception ex) 
	      {
	         System.out.println("Log In failed: An Exception has occurred! " + ex);
	      }
	      
	  
	  return bean;
	  }
   
   public static UserBean eliminar(UserBean bean){
		  Statement stmt = null;    	
	      int rut = bean.getRut();
	      //rut=rut.toUpperCase(); // Ojo con esta linea!
	      String Query =
	              "DELETE FROM alumnos WHERE alumnos.run="
	                       + rut;
	      String searchQuery =
	              "select * from alumnos where run="
	                       + rut;
	            
	      System.out.println("Your run is " + rut);    
	      System.out.println("Query: "+Query);

	      try 
	      {
	         currentCon = ConnectionManager.getConnection();
	         stmt=currentCon.createStatement();
	         rs = stmt.executeQuery(searchQuery);	        
	         boolean more = rs.next();
	         if (more) 
	         {
	        	 System.out.println("Usuario encontrado: Eliminando...");
	        	 stmt.executeUpdate(Query);
	        	 System.out.println("Usuario eliminado");
	        	 bean= new AlumnoBean();
	         } 
	   	        
	         //if user exists set the isValid variable to true
	         else if (more) 
	         {
	        	System.out.println("Usuario no encontrado"); 
	            bean.setValid(true);
	         } 
	      }
	      catch (Exception ex) 
	      {
	         System.out.println("Log In failed: An Exception has occurred! " + ex);
	      }
	  return bean;
	  }
   
   public static AlumnoBean selectAlumno(UserBean user){

	   AlumnoBean alumno = new AlumnoBean();
	   PreparedStatement stmt = null;
	   
	   try{
		   currentCon = ConnectionManager.getConnection();
		   
		   //Carga Curriculum
		   String query = "SELECT a.run, a.mail, AES_DECRYPT(pass,'gatin'), a.primer_nombre," +
				   		  "a.segundo_nombre, a.paterno, a.materno, a.estado_civil, a.direccion," +
				   		  "a.nacionalidad, a.nacimiento, a.referencia, a.datos_adicionales" +
				   		  "from alumnos a where a.run = ?";
		   stmt = currentCon.prepareStatement(query);
		   stmt.setInt(1,user.getRut());
		   rs = stmt.executeQuery();
		   
		   System.out.println("Your run is " + user.getRut());    
		   System.out.println("Query: "+ query);
		   
		   if(rs.next()){
			   alumno.setRun(rs.getInt(0));
			   alumno.setEmail(rs.getString(1));
			   alumno.setPass(rs.getString(2));
			   alumno.setPrimerNombre(rs.getString(3));
			   alumno.setSegundoNombre(rs.getString(4));
			   alumno.setApellidoPaterno(rs.getString(5));
			   alumno.setApellidoMaterno(rs.getString(6));
			   alumno.setEstadoCivil(rs.getString(7));
			   alumno.setDireccion(rs.getString(8));
			   alumno.setNacionalidad(rs.getString(9));
			   alumno.setFechaNacimiento(rs.getDate(10));
		   } 
	   }catch (Exception ex){
		   System.out.println("Log In failed: An Exception has occurred! " + ex);
		   return null;
	   }

	   return alumno ; //se retorna el alumno con sus datos, pero sin el curriculum
   }
   
}

