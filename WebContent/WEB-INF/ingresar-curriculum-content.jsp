<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>
    

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>

<form action="/Practicum/ingresarCurriculum">
	<h2> Datos academicos </h2>
	<label>Descripción.</label></br>
	<input type="text" name="dato_academico_1"></br>
	<label>Fecha inicio.</label></br>
	<input type="date" name="f_inicio_da_1"></br>
	<label>Fecha Termino.</label></br>
	<input type="date" name="f_inicio_da_1"></br>
	<label>Establecimiento.</label></br>
	<input type="text" name="establecimiento_da_1"></br>
	
	<h2> Historial laboral </h2>
	<label>Descripción.</label></br>
	<input type="text" name="historial_laboral_1"></br>
	<label>Fecha inicio.</label></br>
	<input type="date" name="f_inicio_hl_1"></br>
	<label>Fecha Termino.</label></br>
	<input type="date" name="f_inicio_hl_1"></br>
	<label>Establecimiento.</label></br>
	<input type="text" name="establecimiento_hl_1"></br>
	<label>Cargo.</label></br>
	<input type="text" name="cargo_hl_1"></br>
	</br></br>
      <select multiple class="multiselect" id="idiomas" name="idiomas[]">
      <option value="ingles_b">Ingles - Basico</option>
      <option value="ingles_m">Ingles - Medio</option>
      <option value="ingles_a">Ingles - Avanzado</option>
      <option value="aleman_b">Aleman - Basico</option>
      <option value="aleman_m">Aleman - Medio</option>
      <option value="aleman_a">Aleman - Avanzado</option>
      </br>
      
      <select multiple class="multiselect" id="areas" name="areas[]">
      <option value="ingles_b">Ingles - Basico</option>
      <option value="ingles_m">Ingles - Medio</option>
      <option value="ingles_a">Ingles - Avanzado</option>
      <option value="aleman_b">Aleman - Basico</option>
      <option value="aleman_m">Aleman - Medio</option>
      <option value="aleman_a">Aleman - Avanzado</option>
      </br>
      
      <input type="submit">
      
</form></br>
		
		<a href="index.jsp" target="_self"> <input type="button" name="boton" value="volver" /> </a>


