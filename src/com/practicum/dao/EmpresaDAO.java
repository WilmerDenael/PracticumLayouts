package com.practicum.dao;

import java.sql.*;

import com.practicum.dao.ConnectionManager;
import com.practicum.util.*;

public class EmpresaDAO {
	
	 static Connection currentCon = null;
	 static ResultSet rs = null;
	 
	 public static UserBean login(UserBean bean){
	      //preparing some objects for connection 
	      Statement stmt = null;    	
	      int rut = bean.getRut();    
	      String searchQuery =
	            "select(AES_DECRYPT(pass,'gatin')) from empresas where rut="
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
		              "INSERT INTO empresas(rut,pass,email) values ('"
		                       + rut
		                       + "',"
		                       + "AES_ENCRYPT('"+ password +"','gatin')"
		                       + ",'"
		                       +email
		                       + "')";
		      
  
		      String searchQuery =
		              "select * from empresas where rut='"
		                       + rut
		                       + "'";
		      
		      System.out.println("Your rut is " + rut);          
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
		              "DELETE FROM empresas WHERE empresas.rut="
		                       + rut;
		      String searchQuery =
		              "select * from empresas where rut="+ rut;
		            
		      System.out.println("Your rut is " + rut);    
		      System.out.println("Query: "+Query);

		      try 
		      {
		         currentCon = ConnectionManager.getConnection();
		         stmt=currentCon.createStatement();
		         rs = stmt.executeQuery(searchQuery);	        
		         boolean more = rs.next();
		         if (more) 
		         {
		        	 System.out.println("Empresa encontrada: Eliminando...");
		        	 stmt.executeUpdate(Query);
		        	 System.out.println("Empresa eliminada");
		        	 bean= new EmpresaBean();
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
	   

}
