package com.practicum.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.practicum.dao.ConnectionManager;
import com.practicum.util.*;

public class CurriculumDAO {
	
	   static Connection currentCon = null;
	   static ResultSet rs = null;  
	
	   public static void insertCurriculum(UserBean bean){
		   Statement stmt = null;
		   Curriculum curriculum=((AlumnoBean)bean).getCurriculum(); // OJO CON ESTA LINEA, DEBERIA FUNCIONAR
		   String query= "Insert into curriculums values ("+curriculum.getId()+","+bean.getRut()+","+curriculum.isActive()+"+,NOW(),"+curriculum.isConfidencial()+")";
		   System.out.println(query);
		   try {
		         currentCon = ConnectionManager.getConnection();
		         stmt=currentCon.createStatement();
		         stmt.executeUpdate(query);
		         System.out.println("Curriculum Agregado");
		         List<DatoAcademico> academicos =curriculum.getDatosAcademicos();
		         for (DatoAcademico datoAcademico : academicos) {
		        	 String query2 = "Insert into datos_academicos (run,id_curriculum,establecimiento,fin,descripcion) values ("+curriculum.getId()+","+bean.getRut()+",'"+datoAcademico.getEstablecimiento()+"',"+datoAcademico.getFin()+")";// OJO CON LOS DATE
		        	 stmt=currentCon.createStatement();
			         stmt.executeUpdate(query);
				}
		         List<HistorialLaboral> laborales=curriculum.getLaborales();
		         for (HistorialLaboral historialLaboral : laborales) {
		        	 String query2 = "Insert into historial_laboral (run,id_curriculum,establecimiento,cargo,inicio,fin,descripcion) values ("+curriculum.getId()+","+bean.getRut()+",'"+historialLaboral.getEstablecimiento()+"',"+historialLaboral.getInicio()+","+historialLaboral.getFin()+")"; // OJO CON LOS DATE
		        	 stmt=currentCon.createStatement();
			         stmt.executeUpdate(query);
				}
		         List<Idioma> idiomas= curriculum.getIdiomas();
		         for (Idioma idioma : idiomas) {
		        	 String query2 = "Insert into manejo_idiomas (run,id_curriculum,idioma,nivel) values ("+curriculum.getId()+","+bean.getRut()+",'"+idioma.getIdioma()+"','"+idioma.getNivel()+"')"; 
		        	 stmt=currentCon.createStatement();
			         stmt.executeUpdate(query);
				}
		   }catch (Exception ex) {
		         System.out.println("Log In failed: An Exception has occurred! " + ex);
		      }
		   
	   }
	   
	   public static int getNextCurriculumVersion(UserBean bean){ 
		      Statement stmt = null;    	
		      int rut = bean.getRut();    
		      String searchQuery =
		            "select max(id) from curriculums where run="
		                     + rut;
			    
		   // "System.out.println" prints in the console; Normally used to trace the process
		   System.out.println("Your run is " + rut);          
		   System.out.println("Query: "+searchQuery);
			    
		   try 
		   {
		      //connect to DB 
		      currentCon = ConnectionManager.getConnection();
		      stmt=currentCon.createStatement();
		      rs = stmt.executeQuery(searchQuery);	        
		      boolean more = rs.next();
		      // if user does not exist set the isValid variable to false
		      if (!more){
		         System.out.println("Primer Curriculum");
		         return 1;
		      } else {
		    	  System.out.println("Hay curriculums anteriores");
		    	  int ver = rs.getInt(1)+1;
		    	  return 0;
		      }
		   } 
		   catch (Exception ex) 
		   {
		      System.out.println("Log In failed: An Exception has occurred! " + ex);
		      return -1;
		   } 	   
	   }
	   
	   public static Curriculum selectCurriculum(int run, int idCurriculum){

		   Curriculum curriculum = new Curriculum();
		   PreparedStatement stmt = null;
		   
		   try{
			   currentCon = ConnectionManager.getConnection();
			   
			   //Carga Curriculum
			   String query = "SELECT * FROM curriculums where run=? and id=?";
			   stmt = currentCon.prepareStatement(query);
			   stmt.setInt(1,run);
			   stmt.setInt(2,idCurriculum);
			   rs = stmt.executeQuery();
			   while(rs.next()){
				   curriculum.setId(rs.getInt(0));
				   curriculum.setRun(rs.getInt(1));
				   curriculum.setActive(rs.getBoolean(2));
				   curriculum.setUltimaActualizacion(rs.getDate(3));
				   curriculum.setConfidencial(rs.getBoolean(4));  
			   }
			  
			   //Carga DatosAcademicos
			   query = "SELECT * FROM datos_academicos where run=? and id_curriculum=?";
			   stmt = currentCon.prepareStatement(query);
			   stmt.setInt(1,run);
			   stmt.setInt(2,idCurriculum);
			   rs = stmt.executeQuery();
			   ArrayList<DatoAcademico> datosAcademicos = new ArrayList<DatoAcademico>();
			   while(rs.next()){
				   DatoAcademico datoAcademico = new DatoAcademico();
				   datoAcademico.setId(rs.getInt(0));
				   datoAcademico.setRun(rs.getInt(1));
				   datoAcademico.setIdCurriculum(rs.getInt(2));
				   datoAcademico.setEstablecimiento(rs.getString(3));
				   datoAcademico.setInicio(rs.getDate(4));
				   datoAcademico.setFin(rs.getDate(5));
				   datoAcademico.setDescripcion(rs.getString(6));
				   datosAcademicos.add(datoAcademico);
			   }
			   curriculum.setDatosAcademicos(datosAcademicos);
			   
			   //Carga HistorialLaboral
			   query = "SELECT * FROM historial_laboral where run=? and id_curriculum=?";
			   stmt = currentCon.prepareStatement(query);
			   stmt.setInt(1,run);
			   stmt.setInt(2,idCurriculum);
			   rs = stmt.executeQuery();
			   ArrayList<HistorialLaboral> laborales = new ArrayList<HistorialLaboral>();
			   while(rs.next()){
				   HistorialLaboral laboral = new HistorialLaboral();
				   laboral.setId(rs.getInt(0));
				   laboral.setRun(rs.getInt(1));
				   laboral.setIdCurriculum(rs.getInt(2));
				   laboral.setEstablecimiento(rs.getString(3));
				   laboral.setCargo(rs.getString(4));
				   laboral.setInicio(rs.getDate(5));
				   laboral.setFin(rs.getDate(6));
				   laboral.setDescripcion(rs.getString(7));
				   laborales.add(laboral);
			   }
			   curriculum.setLaborales(laborales);
			   
			   //Carga Idiomas
			   query = "SELECT i.idioma, mi.run, mi.id_curriculum, mi.nivel " +
					   "FROM idiomas i, manejo_idiomas mi " +
					   "WHERE mi.run = ? AND mi.id_curriculum = ? AND i.id = mi.id";
			   stmt = currentCon.prepareStatement(query);
			   stmt.setInt(1,run);
			   stmt.setInt(2,idCurriculum);
			   rs = stmt.executeQuery();
			   ArrayList<Idioma> idiomas = new ArrayList<Idioma>();
			   while(rs.next()){
				   Idioma idioma = new Idioma();
				   idioma.setIdioma(rs.getString(0));
				   idioma.setRun(rs.getInt(1));
				   idioma.setIdCurriculum(rs.getInt(2));
				   idioma.setNivel(rs.getString(3));
				   idiomas.add(idioma);
			   }
			   curriculum.setIdiomas(idiomas);
			   
			   //Carga Intereses
			   query = "SELECT cai.id, cai.run, cai.id_curriculum, sai.sub_area " +
					   "FROM  curriculums_areas_interes cai, sub_areas_interes sai " +
					   "WHERE cai.run = ? AND cai.id_curriculum = ? AND cai.id = sai.id";
			   stmt = currentCon.prepareStatement(query);
			   stmt.setInt(1,run);
			   stmt.setInt(2,idCurriculum);
			   rs = stmt.executeQuery();
			   ArrayList<AreaInteres> intereses = new ArrayList<AreaInteres>();
			   while(rs.next()){
				   AreaInteres interes = new AreaInteres();
				   interes.setId(rs.getInt(0));
				   interes.setRun(rs.getInt(1));
				   interes.setIdCurriculum(rs.getInt(2));
				   interes.setArea(rs.getString(3));
				   intereses.add(interes);
			   }
			   curriculum.setIntereses(intereses);
			   
		   }catch (Exception ex){
			   System.out.println("Log In failed: An Exception has occurred! " + ex);
			   return null;
		   }

		   return curriculum;//comentar
	   }

}
