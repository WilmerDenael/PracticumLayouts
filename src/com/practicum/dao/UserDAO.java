package com.practicum.dao;

import java.sql.*;

import com.practicum.dao.ConnectionManager;
import com.practicum.util.UserBean;

public class UserDAO 	
{
   static Connection currentCon = null;
   static ResultSet rs = null;  
	
	
	
   public static UserBean login(UserBean bean) {
	
      //preparing some objects for connection 
      Statement stmt = null;    
	
      int rut = bean.getRut();    
      //rut=rut.toUpperCase();   
      String searchQuery =
            "select (tipo) from usuarios where id="
                     + rut;
	    
   // "System.out.println" prints in the console; Normally used to trace the process
   System.out.println("Your rut is " + rut);          
   System.out.println("Query in UserBean Login: "+searchQuery);
	    
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
	        
      //if user exists set the isValid variable to true
      else if (more) 
      {
    	 bean.setType(rs.getInt("tipo"));
         bean.setValid(true);
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
   /**
    * Este metodo busca utilizar una abstraccion de tipo update para las columnas de tipo String donde el servlet 
    * ingresa los valores de la tabla y los parametros junto con los datos a actualizar y de identificacion.
    * Un ejemplo de como llamar a esta funcion seria:
    * UserDAO.genericStringUpdate("alumnos", "primer_nombre",user.getPrimerNombre,"run", user.getRut())  
    */
   public static boolean genericStringUpdate(String tableName, String parameter, String updatedValue, String whereIdentifier, int whereValue){
	   Statement stmt = null;
	   String query= "UPDATE "+tableName+" SET "+parameter+"='"+updatedValue+"' WHERE "+whereIdentifier+"="+whereValue;
	   System.out.println(query);
	   try {
	         currentCon = ConnectionManager.getConnection();
	         stmt=currentCon.createStatement();
        	 stmt.executeUpdate(query);
        	 System.out.println("Modificado exitosamente");
        	 return true;
	   }catch (Exception ex) {
	         System.out.println("Log In failed: An Exception has occurred! " + ex);
	         return false;
	      }
   }
   
   /** Este metodo busca utilizar una abstraccion de tipo update para las columnas de tipo int donde el servlet 
   * ingresa los valores de la tabla y los parametros junto con los datos a actualizar y de identificacion.
   * Un ejemplo de como llamar a esta funcion seria:
   * UserDAO.genericStringUpdate("empresas", "numero_trabajadores",user.getTrabajadores(),"rut", user.getRut())  
   */
   public static boolean genericNumberUpdate(String tableName, String parameter, String updatedValue, String whereIdentifier, String whereValue){
	   Statement stmt = null;
	   String query= "UPDATE "+tableName+" SET "+parameter+"="+updatedValue+" WHERE "+whereIdentifier+"="+whereValue;
	   System.out.println(query);
	   try {
	         currentCon = ConnectionManager.getConnection();
	         stmt=currentCon.createStatement();
        	 stmt.executeUpdate(query);
        	 System.out.println("Modificado exitosamente");
        	 return true;
	   }catch (Exception ex) {
	         System.out.println("Log In failed: An Exception has occurred! " + ex);
	         return false;
	      }
   }
   
    /** Este metodo busca utilizar una abstraccion de tipo update para las columnas de tipo string donde el servlet para la password
    * ingresa los valores de la tabla y los parametros junto con los datos a actualizar y de identificacion.
    * Un ejemplo de como llamar a esta funcion seria:
    * UserDAO.genericDateUpdate("alumnos", "nacimiento",user.getNacimiento(),"rut", user.getRut())  
    */
   public static boolean genericPassUpdate(String tableName, String parameter, String updatedValue, String whereIdentifier, int whereValue){
	   Statement stmt = null;
	   String query= "UPDATE "+tableName+" SET `pass`=AES_ENCRYPT('"+updatedValue+"','gatin') WHERE "+whereIdentifier+"="+whereValue;
	   System.out.println(query);
	   try {
	         currentCon = ConnectionManager.getConnection();
	         stmt=currentCon.createStatement();
        	 stmt.executeUpdate(query);
        	 System.out.println("Modificado exitosamente");
        	 return true;
	   }catch (Exception ex) {
	         System.out.println("Log In failed: An Exception has occurred! " + ex);
	         return false;
	      }
   }
	public static boolean genericDateUpdate(String tableName, String parameter, String fechaNacimiento, String whereIdentifier, int whereValue) {
		PreparedStatement ps;
		String query ="UPDATE "+tableName+" SET "+parameter+"=? WHERE "+whereIdentifier+"=?";
		try{
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(query);
			ps.setString(1, fechaNacimiento);
			ps.setInt(2, whereValue);
			ps.executeUpdate();
			System.out.println("Modificado exitosamente");
		}catch(Exception ex) {
	         System.out.println("Log In failed: An Exception has occurred! " + ex);
	         return false;
	    }
		
		return true;
	}
   
}
