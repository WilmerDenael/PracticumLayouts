package com.practicum.dao;

import java.sql.*;
import java.util.*;

import com.practicum.util.*;

public class GenericDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;  
	
	public static List<AreaInteres> cargarAreasInteres(){
		
		List<AreaInteres> intereses = new ArrayList<AreaInteres>();
		Statement stmt = null;
		String query = "SELECT * FROM areas_interes";
		try{
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(query);
			AreaInteres interes;
			while(rs.next()){
				interes = new AreaInteres();
				interes.setId(rs.getInt(1));
				interes.setArea(rs.getString(2));
				interes.setDesarrollo(rs.getInt(3));
				interes.setBaseDeDatos(rs.getInt(4));
				interes.setRedes(rs.getInt(5));
				intereses.add(interes);
			}
			System.out.println("Se cargaron: "+intereses.size()+" interes(es)");
		}catch (Exception ex){
			System.out.println("Error en la carga de Areas " + ex);
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
			System.out.println("Error en la carga de Idiomas " + ex); 
		  }
		  return idiomas;
	  }
}
