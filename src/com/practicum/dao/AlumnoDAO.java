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
	      System.out.println("Log In del Alumno ha fallado! " + ex);
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
	   System.out.println("Cargando datos del Alumno");
	   try{
		   currentCon = ConnectionManager.getConnection();
		   
		   //Carga Curriculum
		   String query = "SELECT a.run, a.email, AES_DECRYPT(pass,'gatin'), a.primer_nombre," +
				   		  "a.segundo_nombre, a.paterno, a.materno, a.estado_civil, a.direccion," +
				   		  "a.nacionalidad, a.nacimiento, a.referencia, a.datos_adicionales " +
				   		  "from alumnos a where a.run = ?";
		   stmt = currentCon.prepareStatement(query);
		   stmt.setInt(1,user.getRut());
		   System.out.println("Your run is " + user.getRut());    
		   System.out.println("Query: "+ query);
		   rs = stmt.executeQuery();		   
		   if(rs.next()){
			   alumno.setRut(rs.getInt(1));
			   alumno.setEmail(rs.getString(2));
			   alumno.setPassword(rs.getString(3));
			   alumno.setPrimerNombre(rs.getString(4));
			   alumno.setSegundoNombre(rs.getString(5));
			   alumno.setApellidoPaterno(rs.getString(6));
			   alumno.setApellidoMaterno(rs.getString(7));
			   alumno.setEstadoCivil(rs.getString(8));
			   alumno.setDireccion(rs.getString(9));
			   alumno.setNacionalidad(rs.getString(10));
			   alumno.setFechaNacimiento(rs.getString(11));
			   alumno.setReferencia(rs.getString(12));
			   alumno.setDatosExtra(rs.getString(13));
			   alumno.setLegit(user.isLegit());
			   alumno.setValid(user.isValid());
			   alumno.setType(user.getType());
			   System.out.println("Nacimiento "+alumno.getFechaNacimiento());
		   } 
	   }catch (Exception ex){
		   System.out.println("Log In failed: An Exception has occurred! " + ex);
		   return null;
	   }

	   return alumno ; //se retorna el alumno con sus datos, pero sin el curriculum
   }
   
   public static List<AlumnoBean> buscarNombreApellido(String nombre, String apellido){
		   
		   List<AlumnoBean> alumnos = new ArrayList<AlumnoBean>();
		   PreparedStatement stmt = null;
		   System.out.println("Preparando Consulta...");
		   try{
			   currentCon = ConnectionManager.getConnection();
			   
			   //Carga Curriculum
			   String query = "SELECT a.run, a.primer_nombre, a.segundo_nombre, a.paterno, a.materno " +
					   		  "from alumnos a " +
					   		  "where a.primer_nombre like ? or a.segundo_nombre like ? or " +
					   		  "paterno like ? or materno ? ";
			   System.out.println("Query :" + query);
			   stmt = currentCon.prepareStatement(query);
			   stmt.setString(1,"%" + nombre + "%");
			   stmt.setString(2,"%" + nombre + "%");
			   stmt.setString(3,"%" + apellido + "%");
			   stmt.setString(4,"%" + apellido + "%");
			   System.out.println("Nombre y Apellido: " + nombre + " " + apellido);    
			   System.out.println("Query: "+ query);
			   rs = stmt.executeQuery();		   
			   while(rs.next()){
				   AlumnoBean alumno = new AlumnoBean();
				   alumno.setRut(rs.getInt(1));
				   alumno.setPrimerNombre(rs.getString(2));
				   alumno.setSegundoNombre(rs.getString(3));
				   alumno.setApellidoPaterno(rs.getString(4));
				   alumno.setApellidoMaterno(rs.getString(5));
				   alumnos.add(alumno);
			   } 
		   }catch (Exception ex){
			   System.out.println("Log In failed: An Exception has occurred! " + ex);
			   return null;
		   }

		   return alumnos ; //se retorna la lista de alumnos solamente con su run y su nombre completo
	   }
   
}

