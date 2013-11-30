package com.practicum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.practicum.util.AreaInteres;

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
	
}
