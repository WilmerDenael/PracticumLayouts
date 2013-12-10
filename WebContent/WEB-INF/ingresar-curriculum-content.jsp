<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.*" import="com.practicum.dao.*" import="java.util.*"%>
  

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>

<div id="backContenido">
	<div id="contenido">
		<form id='curriculum' name='curriculum' action="/Practicum/ingresarCurriculum">

		<h2>Datos academicos</h2>
			<fieldset>
				<span class="tab">
					<a href="#" onclick="agregarCantidadDA(); cloneMe(this); return false;" class="cloneMe" title="Add">+</a>
					<a href="#" onclick="descontarCantidadDA();deleteMe(this); return false;" class="deleteMe" title="Delete">x</a>
				</span>
				<table cellspacing="10">
					<tr>
						<td>
							<label for="establecimiento_da">
								Establecimiento:
							</label>
						</td>
						<td>
							<input type="text" id="establecimiento_da" name="establecimiento_da" />
						</td>
						<td>
							<label for="descripcion_da">
								Descripción:
							</label>
						</td>
						<td>
							<input type="text" id="descripcion_da" name="descripcion_da" />
						</td>
					</tr>
					<tr>
						<td>
							<label for="fechaInicio_da">
								Fecha Inicio:
							</label>
						</td>
						<td>
							<input type="date" id="fechaInicio_da" name="fechaInicio_da" />
						</td>
						<td>
							<label for="fechaTermino_da">
								Fecha Término:
							</label>
						</td>
						<td>
							<input type="date" id="fechaTermino_da" name="fechaTermino_da" />
						</td>
					</tr>
				</table>
			</fieldset>
			</br>
			<h2>Historial laboral</h2>
			<fieldset>
				<span class="tab">
					<a href="#" onclick="agregarCantidadHL();cloneMe(this); return false;" class="cloneMe" title="Add">+</a>
					<a href="#" onclick="agregarCantidadHL();deleteMe(this); return false;" class="deleteMe" title="Delete">x</a>
				</span>
				<table cellspacing="10">
					<tr>
						<td>
							
							<label for="establecimiento_hl">
								Establecimiento:
							</label>
						</td>
						<td>
							<input type="text" id="establecimiento_hl" name="establecimiento_hl" />
						</td>
						<td>
							<label for="descripcion_hl">
								Descripción
							</label>							
						</td>
						<td>
							<input type="text" id="descripcion_hl" name="descripcion_hl" />
						</td>
					</tr>
					<tr>
						<td>
							<label for="cargo_hl">
								Cargo:
							</label>
						</td>
						<td>
							<input type="text" id="cargo_hl" name="cargo_hl" />
						</td>
					</tr>
					<tr>
						<td>
							<label for="fechaInicio_hl">
								Fecha Inicio:
							</label>
						</td>
						<td>
							<input type="date" id="fechaInicio_hl" name="fechaInicio_hl" />
						</td>
						<td>
							<label for="fechaTermino_hl">
								Fecha Término:
							</label>
						</td>
						<td>
							<input type="date" id="fechaTermino_hl" name="fechaTermino_hl" />
						</td>
					</tr>
				</table>
			</fieldset>
		</br>
	      
	      <h2>Idiomas</h2>
	      <fieldset>
				<span class="tab">
					<a href="#" onclick="agregarCantidadID();cloneMe(this); return false;" class="cloneMe" title="Add">+</a>
					<a href="#" onclick="descontarCantidadID();deleteMe(this); return false;" class="deleteMe" title="Delete">x</a>
				</span>
				<table cellspacing="10">
					<tr>
						<td>
									<label for="idioma">
										Idiomas:
									</label>
						</td>
						<td>
					      <select name="idioma">
					      <%for(Idioma idioma : GenericDAO.cargarIdiomas()) {%>
					      <option value="<%=idioma.getIdioma()%>"><%=idioma.getIdioma()%></option>
					      <% }%>
					      </select>
					      </br>
					    </td>
					    <td>
									<label for="nivel">
										Nivel:
									</label>
						</td>
						<td>
					      <select name="nivel">
					      	<option value="basico">Básico</option>
					      	<option value="medio">Medio</option>
					      	<option value="avanzado">Avanzado</option>
					      </select>
					      </br>
					    </td>
					 </tr>
				</table>
		</fieldset>
		
		<h2>Areas de interes</h2>
	      <select multiple class="multiselect" id="areas" name="areas[]">
	      <%for( AreaInteres interes : GenericDAO.cargarAreasInteres()) {%>
	      <option value="<%=interes.getArea()%>"><%=interes.getArea()%></option>
	      <% }%>
	      </select>
	      </br>
 
		<input type="hidden" value="1" id="contadorDA" name="contadorDA">
		<input type="hidden" value="1" id="contadorHL" name="contadorHL">      
      	<input type="hidden" value="1" id="contadorID" name="contadorID">
      	<input type="submit">
      
		</form></br>
		
		<a href="index.jsp" target="_self"> <input type="button" name="boton" value="volver" /> </a>
		
		</div>
</div>

<script>
  
function agregarCantidadDA(){
	document.forms["curriculum"].contadorDA.value++;
	console.log("raton!");
}  
function descontarCantidadDA(){
	document.forms["curriculum"].contadorDA.value--;
}   
 
function agregarCantidadID(){
	document.forms["curriculum"].contadorID.value++;
	console.log("raton!");
}  
function descontarCantidadID(){
	document.forms["curriculum"].contadorID.value--;
}   
  
  
function agregarCantidadHL(){
	document.forms["curriculum"].contadorHL.value++;
	console.log("raton!");
}  
function descontarCantidadHL(){
	document.forms["curriculum"].contadorHL.value--;
}     
$(document).ready(function() {
    $('#areas').multiSelect()
    $('#idiomas').multiSelect()
  });
  
</script>
