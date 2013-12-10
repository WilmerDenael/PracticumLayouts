package com.practicum.dao;

import java.sql.*;

import com.practicum.dao.ConnectionManager;
import com.practicum.util.*;

public class AdminDAO{
	
   static Connection currentCon = null;
   static ResultSet rs = null;  
   
    public static UserBean login(UserBean bean){
	      //preparing some objects for connection 
	      Statement stmt = null;    	
	      int rut = bean.getRut();    
	      String searchQuery =
	            "select(AES_DECRYPT(pass,'gatin')) from administradores where run="
	                     + rut;
	   try 
	   {
	      //connect to DB 
	      currentCon = ConnectionManager.getConnection();
	      stmt=currentCon.createStatement();
	      rs = stmt.executeQuery(searchQuery);	        
	      boolean more = rs.next();
	      if (!more) 
	      {
	         System.out.println("Sorry, you are not a registered user! Please sign up first");
	         bean.setValid(false);
	      } 
		        
	      else if (more) 
	      {
	    	 System.out.println("Hay resultados!");
	    	 String pass=rs.getString(1); 
	         if(pass.equals(bean.getPassword())){
	        	 bean.setLegit(true);
	         }
	      }
	   } 

	   catch (Exception ex) 
	   {
	      System.out.println("Log In del administrador ha fallado " + ex);
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
    
    public static AdminBean selectAdmin(UserBean user){

 	   AdminBean admin = new AdminBean();
 	   PreparedStatement stmt = null;
 	   System.out.println("Cargando datos del Admin");
 	   try{
 		   currentCon = ConnectionManager.getConnection();
 		   
 		   //Carga Curriculum
 		   String query = "SELECT a.run, a.email, AES_DECRYPT(pass,'gatin'), a.nombre " +
 				   		  "from administradores a where a.run = ?";
 		   stmt = currentCon.prepareStatement(query);
 		   stmt.setInt(1,user.getRut());
 		   rs = stmt.executeQuery();		   
 		   if(rs.next()){
 			  admin.setRut(rs.getInt(1));
 			  admin.setEmail(rs.getString(2));
 			  admin.setPassword(rs.getString(3));
 			  admin.setNombre(rs.getString(4));
 			  admin.setLegit(user.isLegit());
 			  admin.setValid(user.isValid());
 			  admin.setType(user.getType());
 		   } 
 	   }catch (Exception ex){
 		   System.out.println("Error al seleccionar el Administrador " + ex);
 		   return null;
 	   }

 	   return admin ; //se retorna el alumno con sus datos, pero sin el curriculum
    }
    
   
}

