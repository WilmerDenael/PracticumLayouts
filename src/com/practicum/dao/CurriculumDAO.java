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
		         if (!academicos.isEmpty()) {
		        	 for (DatoAcademico datoAcademico : academicos) {
		        		 String query2 = "Insert into datos_academicos (run,id_curriculum,establecimiento,fin,descripcion) values ("+curriculum.getId()+","+bean.getRut()+",'"+datoAcademico.getEstablecimiento()+"',"+datoAcademico.getFin()+")";// OJO CON LOS DATE
		        		 stmt=currentCon.createStatement();
		        		 stmt.executeUpdate(query2);
		        	 }					
				}
		         List<HistorialLaboral> laborales=curriculum.getLaborales();
		         if (!laborales.isEmpty()) {
		        	 for (HistorialLaboral historialLaboral : laborales) {
		        		 String query2 = "Insert into historial_laboral (run,id_curriculum,establecimiento,cargo,inicio,fin,descripcion) values ("+curriculum.getId()+","+bean.getRut()+",'"+historialLaboral.getEstablecimiento()+"',"+historialLaboral.getInicio()+","+historialLaboral.getFin()+")"; // OJO CON LOS DATE
		        		 stmt=currentCon.createStatement();
		        		 stmt.executeUpdate(query2);
		        	 }					
				}
		         List<Idioma> idiomas= curriculum.getIdiomas();
		         if (idiomas.isEmpty()) {
		        	 for (Idioma idioma : idiomas) {
		        		 String query2 = "Insert into manejo_idiomas (run,id_curriculum,idioma,nivel) values ("+curriculum.getId()+","+bean.getRut()+",'"+idioma.getIdioma()+"','"+idioma.getNivel()+"')"; 
		        		 stmt=currentCon.createStatement();
		        		 stmt.executeUpdate(query2);					
		        	 } 
				}
		   }catch (Exception ex) {
		         System.out.println("Log In failed: An Exception has occurred! " + ex);
		      }
		   
	   }
	   
/** Metodo para obtener nueva version del curriculum
 * No utilizado actualmente 
 */
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
		    	  return ver;
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
		   
		   System.out.println("Su run es: " + run);
		   System.out.println("Su idCurriculum es: " + idCurriculum);
		   
		   try{
			   currentCon = ConnectionManager.getConnection();
			   
			   //Carga Curriculum
			   String query = "SELECT * FROM curriculums where run= ? and id= ?";
			   System.out.println("query: " + query);
			   stmt = currentCon.prepareStatement(query);
			   stmt.setInt(1,run);
			   stmt.setInt(2,idCurriculum);
			   rs = stmt.executeQuery();
			   if(rs.next()){
				   curriculum.setId(rs.getInt(1));
				   curriculum.setRun(rs.getInt(2));
				   curriculum.setActive(rs.getBoolean(3));
				   curriculum.setUltimaActualizacion(rs.getString(4));
				   curriculum.setConfidencial(rs.getBoolean(5));  
			   }
			   System.out.println("Carga de Curriculum exitosa");
			   
			   //Carga DatosAcademicos
			   query = "SELECT * FROM datos_academicos where run= ? and id_curriculum= ?";
			   System.out.println("query: " + query);
			   stmt = currentCon.prepareStatement(query);
			   stmt.setInt(1,run);
			   stmt.setInt(2,idCurriculum);
			   rs = stmt.executeQuery();
			   ArrayList<DatoAcademico> datosAcademicos = new ArrayList<DatoAcademico>();
			   while(rs.next()){
				   DatoAcademico datoAcademico = new DatoAcademico();
				   datoAcademico.setId(rs.getInt(1));
				   datoAcademico.setRun(rs.getInt(2));
				   datoAcademico.setIdCurriculum(rs.getInt(3));
				   datoAcademico.setEstablecimiento(rs.getString(4));
				   datoAcademico.setInicio(rs.getString(5));
				   datoAcademico.setFin(rs.getString(6));
				   datoAcademico.setDescripcion(rs.getString(7));
				   datosAcademicos.add(datoAcademico);
			   }
			   curriculum.setDatosAcademicos(datosAcademicos);
			   System.out.println("Carga de Datos Academicos exitosa");
			   
			   //Carga HistorialLaboral
			   query = "SELECT * FROM historial_laboral where run=? and id_curriculum=?";
			   System.out.println("Query: " + query );
			   stmt = currentCon.prepareStatement(query);
			   stmt.setInt(1,run);
			   stmt.setInt(2,idCurriculum);
			   rs = stmt.executeQuery();
			   ArrayList<HistorialLaboral> laborales = new ArrayList<HistorialLaboral>();
			   while(rs.next()){
				   HistorialLaboral laboral = new HistorialLaboral();
				   laboral.setId(rs.getInt(1));
				   laboral.setRun(rs.getInt(2));
				   laboral.setIdCurriculum(rs.getInt(3));
				   laboral.setEstablecimiento(rs.getString(4));
				   laboral.setCargo(rs.getString(5));
				   laboral.setInicio(rs.getString(6));
				   laboral.setFin(rs.getString(7));
				   laboral.setDescripcion(rs.getString(8));
				   laborales.add(laboral);
			   }
			   curriculum.setLaborales(laborales);
			   System.out.println("Carga de Antecedentes Laborales exitosa");
			   
			   //Carga Idiomas
			   query = "SELECT i.idioma, mi.run, mi.id_curriculum, mi.nivel " +
					   "FROM idiomas i, manejo_idiomas mi " +
					   "WHERE mi.run = ? AND mi.id_curriculum = ? AND i.id = mi.id";
			   System.out.println("Query: " + query);
			   stmt = currentCon.prepareStatement(query);
			   stmt.setInt(1,run);
			   stmt.setInt(2,idCurriculum);
			   rs = stmt.executeQuery();
			   ArrayList<Idioma> idiomas = new ArrayList<Idioma>();
			   while(rs.next()){
				   Idioma idioma = new Idioma();
				   idioma.setIdioma(rs.getString(1));
				   idioma.setRun(rs.getInt(2));
				   idioma.setIdCurriculum(rs.getInt(3));
				   idioma.setNivel(rs.getString(4));
				   idiomas.add(idioma);
			   }
			   curriculum.setIdiomas(idiomas);
			   System.out.println("Carga de Idiomas exitosa");
			   
			   //Carga Intereses
			   query = "SELECT ai.id, c.run, c.id, ai.area, ai.desarrollo, ai.base_de_datos, ai.redes " +
					   "FROM  areas_interes ai, curriculums c, curriculums_areas_interes cai " +
					   "WHERE c.run = ? AND c.id = ? AND cai.id = ai.id " + 
					   "AND c.id = cai.id_curriculum AND c.run = cai.run";
			   System.out.println("Query: " + query);
			   stmt = currentCon.prepareStatement(query);
			   stmt.setInt(1,run);
			   stmt.setInt(2,idCurriculum);
			   rs = stmt.executeQuery();
			   ArrayList<AreaInteres> intereses = new ArrayList<AreaInteres>();
			   while(rs.next()){
				   AreaInteres interes = new AreaInteres();
				   interes.setId(rs.getInt(1));
				   interes.setRun(rs.getInt(2));
				   interes.setIdCurriculum(rs.getInt(3));
				   interes.setArea(rs.getString(4));
				   interes.setDesarrollo(rs.getInt(5));
				   interes.setBaseDeDatos(rs.getInt(6));
				   interes.setRedes(rs.getInt(7));
				   intereses.add(interes);
			   }
			   curriculum.setIntereses(intereses);
			   System.out.println("Carga de Intereses exitosa");
			   
		   }catch (Exception ex){
			   System.out.println("Log In failed: An Exception has occurred! " + ex);
			   return null;
		   }

		   return curriculum;
	   }

}
