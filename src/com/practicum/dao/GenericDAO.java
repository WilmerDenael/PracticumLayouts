package com.practicum.dao;

import java.sql.*;
import java.util.*;

import com.practicum.util.*;

public class GenericDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;  
	
	public ArrayList<AreaInteres> cargarAreasInteres(){
		
		ArrayList<AreaInteres> intereses = new ArrayList<AreaInteres>();
		PreparedStatement stmt = null;
		
		try{
			String query = "SELECT * FROM areas_interes";
			stmt = currentCon.prepareStatement(query);
		    rs = stmt.executeQuery();
			while(rs.next()){
				AreaInteres interes = new AreaInteres();
				interes.setId(rs.getInt(1));
				interes.setArea(rs.getString(2));
				interes.setDesarrollo(rs.getInt(3));
				interes.setBaseDeDatos(rs.getInt(4));
				interes.setRedes(rs.getInt(5));
				intereses.add(interes);
			}
			
			System.out.println("Carga de Intereses exitosa");
		
		}catch (Exception ex){
			System.out.println("Log In failed: An Exception has occurred! " + ex);
			return null;
		}
		
		return intereses;
	}
	
	 public static List<Idioma> cargarIdiomas(){
		  List<Idioma> idiomas = new ArrayList<Idioma>();
		  Statement stmt = null;
		  String query = "Select id,idioma from idiomas";
		  try{
			  currentCon = ConnectionManager.getConnection();
			  stmt = currentCon.createStatement();
			   rs = stmt.executeQuery(query);
			   Idioma idioma;
			   while(rs.next()){
				   idioma = new Idioma();
				   idioma.setId(rs.getString(1));
				   idioma.setIdioma(rs.getString(2));
				   idiomas.add(idioma);
			   } 
			 System.out.println("Se cargaron: "+idiomas.size()+" idioma(s)"); 
		  }catch(Exception ex){
			System.out.println("Log In failed: An Exception has occurred! " + ex); 
		  }
		  return idiomas;
	  }
}
