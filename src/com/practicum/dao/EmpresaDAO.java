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
	   try 
	   {
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
	      System.out.println("Log In de Empresa ha fallado! " + ex);
	   } 
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
		         if (!more) 
		         {
		        	 System.out.println("Un usuario mas");
		        	 
		        	 stmt.executeUpdate(Query);
		        	 
		        	 bean.setValid(true);
		         } 
		         else if (more) 
		         {
		            bean.setValid(false);
		         } 
		      }
		      catch (Exception ex) 
		      {
		         System.out.println("Error al registrar a la empresa! " + ex);
		      }
		      
		  
		  return bean;
		  }
	   
	   public static UserBean eliminar(UserBean bean){
			  Statement stmt = null;    	
		      int rut = bean.getRut();
		      String Query =
		              "DELETE FROM empresas WHERE empresas.rut="
		                       + rut;
		      String searchQuery =
		              "select * from empresas where rut="+ rut;
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
		         System.out.println("Se produjo un error al Eliminar la empresa " + ex);
		      }
		  return bean;
		  }
	   
	   public static EmpresaBean selectEmpresa(UserBean user){

		   EmpresaBean empresa = new EmpresaBean();
		   PreparedStatement stmt = null;
		   
		   try{
			   currentCon = ConnectionManager.getConnection();
			   
			   //Carga Curriculum
			   String query = "SELECT e.rut, e.email, AES_DECRYPT(pass,'gatin'), e.nombre, " +
					   		  "e.numero_trabajadores, e.nacionalidad, e.area_laboral, e.direccion, " +
					   		  "e.descripcion from empresas e where e.rut = ?";
			   stmt = currentCon.prepareStatement(query);
			   stmt.setInt(1,user.getRut());
			   rs = stmt.executeQuery();			   
			   if(rs.next()){
				   empresa.setRut(rs.getInt(1));
				   empresa.setEmail(rs.getString(2));
				   empresa.setPassword(rs.getString(3));
				   empresa.setNombre(rs.getString(4));
				   empresa.setNumeroTrabajadores(rs.getInt(5));
				   empresa.setNacionalidad(rs.getString(6));
				   empresa.setAreaLaboral(rs.getString(7));
				   empresa.setDireccion(rs.getString(8));
				   empresa.setDescripcion(rs.getString(9));
				   empresa.setLegit(user.isLegit());
				   empresa.setValid(user.isValid());
				   empresa.setType(user.getType());
			   } 
		   }catch (Exception ex){
			   System.out.println("Error al seleccionar la empresa! " + ex);
			   return null;
		   }

		   return empresa ;
	   }
	   

}
