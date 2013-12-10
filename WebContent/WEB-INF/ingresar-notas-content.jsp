<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.*" import="com.practicum.dao.*" import="java.util.*"%>
  

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>

<div id="backContenido">
	<div id="contenido">

		<form id='curriculum' name='curriculum' action="/Practicum/IngresarNotas">
		
		<h2>Ingreso de notas:</h2>
			<fieldset>
				<span class="tab">
					<a href="#" onclick="agregarCantidadNO(); cloneMe1(this); return false;" class="cloneMe" title="Add">+</a>
					<a href="#" onclick="descontarCantidadNO();deleteMe1(this); return false;" class="deleteMe" title="Delete">x</a>
					</span>
						<table cellspacing="10">
							<tr>
								<td>
									<label for="asignatura">
										Asignatura:
									</label>
								</td>
								<td>
									<select name="asignatura">
										<%for( Asignatura asignatura : GenericDAO.cargarAsignaturas()) {%>
										<option value="<%=asignatura.getAsignatura()%>"><%=asignatura.getAsignatura()%></option>
										<% }%>
									</select>
								</td>
								<td>
									<label for="nota">
										Nota:
									</label>
								</td>
								<td>
									<input type="text" id="nota" name="nota" />
								</td>
							</tr>
						</table>
					</fieldset>
					</br>
		
					<input type="hidden" value="1" id="contadorNO" name="contadorNO">    
		      
		      		<input type="submit">
      
		</form></br>
		
	<a href="index.jsp" target="_self"> <input type="button" name="boton" value="volver" /> </a>
	</div>
</div>

<script>
  
function agregarCantidadNO(){
	document.forms["curriculum"].contadorNO.value++;
	console.log("chupala!");
}  
function descontarCantidadNO(){
	document.forms["curriculum"].contadorNO.value--;
}   
  
 
$(document).ready(function() {
    $('#areas').multiSelect()
    $('#idiomas').multiSelect()
  });
  
</script>
