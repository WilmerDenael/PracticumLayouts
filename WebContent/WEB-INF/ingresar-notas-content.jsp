<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.*" import="com.practicum.dao.*" import="java.util.*"%>
  

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>

<form id='curriculum' name='curriculum' action="/Practicum/ingresarNotas">

<h2>Ingreso de notas:</h2>
			<fieldset>
				<span class="tab">
					<a href="#" onclick="agregarCantidadDA(); cloneMe(this); return false;" class="cloneMe" title="Add">+</a>
					<a href="#" onclick="descontarCantidadDA();deleteMe(this); return false;" class="deleteMe" title="Delete">x</a>
				</span>
				<table cellspacing="10">
					<tr>
						<td>
							<label for="asignatura_da">
								Asignatura:
							</label>
						</td>
						<td>
							<select name="asignaturas[]">
							      <%for( Asignatura asignatura : GenericDAO.cargarAsignaturas()) {%>
							      <option value="<%=asignatura.getAsignatura()%>"><%=asignatura.getAsignatura()%></option>
							      <% }%>
						    </select>
						</td>
						<td>
							<label for="notas_da">
								Nota:
							</label>
						</td>
						<td>
							<input type="text" id="nota_da" name="nota_da" />
						</td>
					</tr>
					
				</table>
			</fieldset>
</br>

<input type="hidden" value="1" id="contadorDA" name="contadorDA">
<input type="hidden" value="1" id="contadorHL" name="contadorHL">      
      
      <input type="submit">
      
</form></br>
		
		<a href="index.jsp" target="_self"> <input type="button" name="boton" value="volver" /> </a>

<script>
  
function agregarCantidadDA(){
	document.forms["curriculum"].contadorDA.value++;
	console.log("chupala!");
}  
function descontarCantidadDA(){
	document.forms["curriculum"].contadorDA.value--;
}   
  
function agregarCantidadHL(){
	document.forms["curriculum"].contadorHL.value++;
	console.log("chupala!");
}  
function descontarCantidadHL(){
	document.forms["curriculum"].contadorHL.value--;
}     
$(document).ready(function() {
    $('#areas').multiSelect()
    $('#idiomas').multiSelect()
  });
  
</script>
