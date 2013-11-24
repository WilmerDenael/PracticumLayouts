package com.practicum.dao;

import java.sql.*;
import java.util.*;


public class ConnectionManager {

   static Connection con;
         
   public static Connection getConnection()
   {
     
      try
      {
         
         Class.forName("com.mysql.jdbc.Driver");
         
         try
         {            	
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practicum","root","root"); 
             				
              
         }
         
         catch (SQLException ex)
         {
            ex.printStackTrace();
         }
      }

      catch(ClassNotFoundException e)
      {
         System.out.println(e);
      }

   return con;
   }
}